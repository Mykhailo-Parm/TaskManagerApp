package com.github.mykhailo_parm.taskmanager.service.impl;

import com.github.mykhailo_parm.taskmanager.repository.TaskRepository;
import com.github.mykhailo_parm.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
