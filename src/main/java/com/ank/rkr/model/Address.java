package com.ank.rkr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private Long id;
    private Long personId;
    private String houseNumber;
    private String street;
    private String city;
    private String zipCode;

}
