package com.metropolitan.it355junb.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoReq {

    private String username;
    private String password;
    private String fullName;
    private LocalDate dateOfRegistration;

}