package com.metropolitan.it355junb.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDtoReq {

    private LocalDate dateCreated;
    private long userId;
    private long productId;

}