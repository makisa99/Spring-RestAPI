package com.metropolitan.it355junb.service;

import com.metropolitan.it355junb.exception.ResourceNotFoundException;
import com.metropolitan.it355junb.model.User;
import com.metropolitan.it355junb.payload.request.UserDtoReq;
import com.metropolitan.it355junb.payload.response.UserDtoRes;
import com.metropolitan.it355junb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDtoRes createUser(UserDtoReq userDtoReq) {
        User user = mapToEntity(userDtoReq);

        User newUser = userRepository.save(user);

        return mapToDTO(newUser);
    }

    public List<UserDtoRes> getAll() {
        return userRepository.findAll().stream().map(user -> mapToDTO(user)).collect(Collectors.toList());
    }

    public UserDtoRes getById(long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", String.valueOf(id)));

        return mapToDTO(user);
    }

    public UserDtoRes updateUser(long id, UserDtoReq userDtoReq) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", String.valueOf(id)));

        user.setUsername(userDtoReq.getUsername());
        user.setPassword(userDtoReq.getPassword());
        user.setFullName(userDtoReq.getFullName());

        User updatedUser = userRepository.save(user);

        return mapToDTO(updatedUser);
    }

    public void deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", String.valueOf(id)));

        userRepository.delete(user);
    }

    private User mapToEntity(UserDtoReq userDtoReq) {
        User user = new User();

        user.setUsername(userDtoReq.getUsername());
        user.setPassword(userDtoReq.getPassword());
        user.setFullName(userDtoReq.getFullName());
        user.setDateOfRegistration(LocalDate.now());

        return user;
    }

    private UserDtoRes mapToDTO(User user) {
        UserDtoRes userDtoRes = new UserDtoRes();

        userDtoRes.setId(user.getId());
        userDtoRes.setUsername(user.getUsername());
        userDtoRes.setFullName(user.getFullName());
        userDtoRes.setDateOfRegistration(user.getDateOfRegistration());

        return userDtoRes;
    }

}
