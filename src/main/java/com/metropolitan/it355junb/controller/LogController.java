package com.metropolitan.it355junb.controller;

import com.metropolitan.it355junb.payload.request.LogDtoReq;
import com.metropolitan.it355junb.payload.response.LogDtoRes;
import com.metropolitan.it355junb.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<LogDtoRes> getAll() {
        return logService.getAll();
    }

    @PostMapping
    public ResponseEntity<LogDtoRes> create(@RequestBody LogDtoReq logDtoReq) {
        return new ResponseEntity<>(logService.createLog(logDtoReq), HttpStatus.CREATED);
    }

}