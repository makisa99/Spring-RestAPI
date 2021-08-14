package com.metropolitan.it355junb.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDtoRes {

    private long id;
    private LocalDate dateCreated;
    private long userId;
    private long productId;

}
