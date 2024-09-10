package com.github.mykhailo_parm.taskmanager.service.impl;

import com.github.mykhailo_parm.taskmanager.model.User;
import com.github.mykhailo_parm.taskmanager.repository.UserRepository;
import com.github.mykhailo_parm.taskmanager.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
