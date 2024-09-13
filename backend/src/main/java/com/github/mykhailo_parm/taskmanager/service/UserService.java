package com.github.mykhailo_parm.taskmanager.service;


import com.github.mykhailo_parm.taskmanager.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    boolean isExists(Long id);
    User partialUpdate(User user, Long id);
}
