package com.patika.vet.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    private String name;
    private String phone;
    @Email
    private String mail;
    private String address;
    private String city;
}
