package com.metropolitan.it355junb.controller;

import com.metropolitan.it355junb.payload.request.UserDtoReq;
import com.metropolitan.it355junb.payload.response.UserDtoRes;
import com.metropolitan.it355junb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDtoRes> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoRes> getById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDtoRes> create(@RequestBody UserDtoReq userDtoReq) {
        return new ResponseEntity<>(userService.createUser(userDtoReq), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDtoRes> update(@PathVariable(name = "id") long id, @RequestBody UserDtoReq userDtoReq) {
        return new ResponseEntity<>(userService.updateUser(id, userDtoReq), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") long id) {
        userService.deleteUser(id);

        return new ResponseEntity<>("Korisnik je obrisan", HttpStatus.OK);
    }

}