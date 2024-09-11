package com.github.mykhailo_parm.taskmanager.controller;

import com.github.mykhailo_parm.taskmanager.dto.TaskDTO;
import com.github.mykhailo_parm.taskmanager.mapper.Mapper;
import com.github.mykhailo_parm.taskmanager.model.Task;
import com.github.mykhailo_parm.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/users/{userId}/tasks")
public class TaskController {
    private TaskService taskService;
    private Mapper<Task, TaskDTO> taskMapper;

    public TaskController(TaskService taskService, Mapper<Task, TaskDTO> taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

//    @GetMapping
//    public List<TaskDTO> getAllTasksOfUser(@PathVariable("userId") Long id) {
//        List<Task> tasks = taskService.findAll();
//        return tasks
//                .stream()
//                .map(taskMapper::mapTo)
//                .collect(Collectors.toList());
//    }

//    @GetMapping(path = "/{taskId}")
//    public ResponseEntity<TaskDTO> getTaskOfUser(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) {
//        return taskService.findById()
//    }
}
