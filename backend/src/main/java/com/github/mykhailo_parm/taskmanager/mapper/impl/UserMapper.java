package com.github.mykhailo_parm.taskmanager.mapper.impl;

import com.github.mykhailo_parm.taskmanager.dto.UserDTO;
import com.github.mykhailo_parm.taskmanager.mapper.Mapper;
import com.github.mykhailo_parm.taskmanager.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO mapTo(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User mapFrom(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
