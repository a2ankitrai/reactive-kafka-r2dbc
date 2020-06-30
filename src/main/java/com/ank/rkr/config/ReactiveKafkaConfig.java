package com.ank.rkr.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.internals.ConsumerFactory;
import reactor.kafka.receiver.internals.DefaultKafkaReceiver;

import java.util.Collections;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableKafka
@EnableConfigurationProperties(KafkaProperties.class)
public class ReactiveKafkaConfig {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Bean
    DefaultKafkaReceiver<String, String> kafkaReceiver(KafkaProperties kafkaProperties) {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaReceiver(
                ConsumerFactory.INSTANCE,
                ReceiverOptions
                        .create(props)
                        .subscription(Collections.singleton(topicName)));
    }
}
