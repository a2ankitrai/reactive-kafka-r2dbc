package com.ank.rkr.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AddressMessage {

    private String houseNumber;
    private String street;
    private String city;
    private String zipCode;
}
