package com.ank.rkr.listener;

import com.ank.rkr.exception.NonTransientException;
import com.ank.rkr.exception.TransientException;
import com.ank.rkr.service.ReactiveProcessorService;
import com.ank.rkr.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Log4j2
public class ReactiveKafkaListener {

    private final KafkaReceiver<String, String> kafkaReceiver;
    private final ReactiveProcessorService processorService;

    @EventListener(ApplicationReadyEvent.class)
    public void inboundFlux() {
        kafkaReceiver.receive()
                .doOnNext(r -> processorService
                        .processMessage(Mapper.mapRecordToPersonMessage(r))
                        .doOnError(NonTransientException.class,
                                e -> log.error("NonTransientException Error message: " + e.getMessage()))
                        .doOnError(TransientException.class, e -> {
                            log.error("TransientException Error message: " + e.getMessage());
                            throw e;
                        })
                        .retryWhen(RetryBackoffSpec
                                .backoff(10, Duration.ofMillis(500))
                                .jitter(0.8D)
                                .filter(e -> e instanceof TransientException))
                        .subscribe())
                .subscribe(r -> r.receiverOffset().acknowledge());
    }

}
