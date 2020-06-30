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
public class Person {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String contactNumber;
}
