package com.ank.rkr.repository;

import com.ank.rkr.model.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends ReactiveCrudRepository<Address, Long> {
}
