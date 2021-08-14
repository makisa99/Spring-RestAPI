package com.metropolitan.it355junb.service;

import com.metropolitan.it355junb.exception.ResourceNotFoundException;
import com.metropolitan.it355junb.model.Log;
import com.metropolitan.it355junb.model.Product;
import com.metropolitan.it355junb.model.User;
import com.metropolitan.it355junb.payload.request.LogDtoReq;
import com.metropolitan.it355junb.payload.response.LogDtoRes;
import com.metropolitan.it355junb.repository.LogRepository;
import com.metropolitan.it355junb.repository.ProductRepository;
import com.metropolitan.it355junb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {

    private final LogRepository logRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public LogService(LogRepository logRepository,
                      UserRepository userRepository,
                      ProductRepository productRepository) {
        this.logRepository = logRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public LogDtoRes createLog(LogDtoReq logDtoReq) {
        Log log = mapToEntity(logDtoReq);

        Log newLog = logRepository.save(log);

        return mapToDTO(newLog);
    }

    public List<LogDtoRes> getAll() {
        return logRepository.findAll().stream().map(log -> mapToDTO(log)).collect(Collectors.toList());
    }

    private Log mapToEntity(LogDtoReq logDtoReq) {
        Log log = new Log();

        log.setDateCreated(logDtoReq.getDateCreated());

        User user = userRepository.findById(logDtoReq.getUserId()).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", String.valueOf(logDtoReq.getUserId())));

        Product product = productRepository.findById(logDtoReq.getProductId()).orElseThrow(() ->
                new ResourceNotFoundException("Product", "id", String.valueOf(logDtoReq.getProductId())));

        log.setUser(user);
        log.setProduct(product);

        return log;
    }

    private LogDtoRes mapToDTO(Log log) {
        LogDtoRes logDtoRes = new LogDtoRes();

        logDtoRes.setId(log.getId());
        logDtoRes.setDateCreated(log.getDateCreated());
        logDtoRes.setUserId(log.getUser().getId());
        logDtoRes.setProductId(log.getProduct().getId());

        return logDtoRes;
    }

}