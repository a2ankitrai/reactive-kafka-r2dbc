package com.ank.rkr.service;

import com.ank.rkr.exception.NonTransientException;
import com.ank.rkr.exception.TransientException;
import com.ank.rkr.message.PersonMessage;
import com.ank.rkr.model.Address;
import com.ank.rkr.model.Person;
import com.ank.rkr.repository.AddressRepository;
import com.ank.rkr.repository.PersonRepository;
import com.ank.rkr.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReactiveProcessorService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public Mono<Void> processMessage(PersonMessage personMessage) {
        Mono<Person> personMono = this.savePerson(personMessage);

        return personMono
                .flatMapMany(person -> this.saveAddress(personMessage, person.getId()))
                .then();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<Person> savePerson(PersonMessage personMessage) {
        log.info(this.getClass().getSimpleName()+": savePerson: start");
        Person person = Mapper.mapPersonMessageToPersonModel(personMessage);
        return personRepository.save(person)
                .onErrorMap(DataIntegrityViolationException.class, NonTransientException::new)
                .onErrorMap(DataRetrievalFailureException.class, NonTransientException::new)
                .onErrorMap(RuntimeException.class, TransientException::new);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Flux<Address> saveAddress(PersonMessage personMessage, final Long personId) {
        log.info(this.getClass().getSimpleName()+": saveAddress: start");

        List<Address> addressList =
                personMessage.getAddresses()
                        .stream()
                        .map(addressMessage ->
                                Mapper.mapPersonMessageToAddressModel(addressMessage, personId))
                        .collect(Collectors.toList());

        return addressRepository.saveAll(addressList)
                .onErrorMap(DataIntegrityViolationException.class, NonTransientException::new)
                .onErrorMap(DataRetrievalFailureException.class, NonTransientException::new)
                .onErrorMap(RuntimeException.class, TransientException::new);

    }
}
