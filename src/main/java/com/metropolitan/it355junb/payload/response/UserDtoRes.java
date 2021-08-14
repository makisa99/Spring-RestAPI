package com.metropolitan.it355junb.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoRes {

    private Long id;
    private String username;
    private String fullName;
    private LocalDate dateOfRegistration;

}