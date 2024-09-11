package com.github.mykhailo_parm.taskmanager.service;

import com.github.mykhailo_parm.taskmanager.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task save(Long userId, Task task);
    Optional<Task> findById(Long userId, Long taskId);
    List<Task> findAllByUser(Long userId);
    void delete(Long userId, Long taskId);
    boolean isExists(Long userId, Long taskId);
    Task  partialUpdate(Long userId, Long taskId, Task task);

}
