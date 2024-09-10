package com.github.mykhailo_parm.taskmanager.controller;

import com.github.mykhailo_parm.taskmanager.dto.UserDTO;
import com.github.mykhailo_parm.taskmanager.mapper.Mapper;
import com.github.mykhailo_parm.taskmanager.model.User;
import com.github.mykhailo_parm.taskmanager.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private UserService userService;
    private Mapper<User, UserDTO> userMapper;

    public UserController(UserService userService, Mapper<User, UserDTO> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.mapFrom(userDTO);
        User savedUser = userService.save(user);

        return new ResponseEntity<>(userMapper.mapTo(savedUser), HttpStatus.CREATED);
    }
}
