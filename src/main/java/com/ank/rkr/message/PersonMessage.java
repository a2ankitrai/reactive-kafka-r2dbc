package com.ank.rkr.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PersonMessage {

    private String firstName;
    private String lastName;
    private String contactNumber;
    private List<AddressMessage> addresses;
}
