package com.github.mykhailo_parm.taskmanager.controller;

import com.github.mykhailo_parm.taskmanager.dto.TaskDTO;
import com.github.mykhailo_parm.taskmanager.mapper.Mapper;
import com.github.mykhailo_parm.taskmanager.model.Task;
import com.github.mykhailo_parm.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TaskController {
    private TaskService taskService;
    private Mapper<Task, TaskDTO> taskMapper;

    public TaskController(TaskService taskService, Mapper<Task, TaskDTO> taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }
}
