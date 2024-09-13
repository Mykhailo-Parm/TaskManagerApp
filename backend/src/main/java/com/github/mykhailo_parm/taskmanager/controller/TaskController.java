package com.github.mykhailo_parm.taskmanager.controller;

import com.github.mykhailo_parm.taskmanager.dto.TaskDTO;
import com.github.mykhailo_parm.taskmanager.dto.UserDTO;
import com.github.mykhailo_parm.taskmanager.mapper.Mapper;
import com.github.mykhailo_parm.taskmanager.model.Task;
import com.github.mykhailo_parm.taskmanager.model.User;
import com.github.mykhailo_parm.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<TaskDTO> getAllTasksOfUser(@PathVariable("userId") Long userId) {
        List<Task> tasks = taskService.findAllByUser(userId);
        return tasks
                .stream()
                .map(taskMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) {
        return taskService.findById(userId, taskId)
                .map(task -> new ResponseEntity<>(taskMapper.mapTo(task), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@PathVariable("userId") Long userId, @RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.mapFrom(taskDTO);

        Task savedTask = taskService.save(userId, task);

        TaskDTO responseDTO = taskMapper.mapTo(savedTask);

        responseDTO.setUserUrl("http://localhost:8080/api/users/" + userId + "/tasks/" + savedTask.getId());

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) {
        taskService.delete(userId, taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(path = "/{taskId}")
    public ResponseEntity<TaskDTO> partialUpdate(
            @PathVariable("userId") Long userId,
            @PathVariable("taskId") Long taskId,
            @RequestBody TaskDTO taskDTO) {
                if(!taskService.isExists(userId, taskId)) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

                Task task = taskMapper.mapFrom(taskDTO);
                Task updatedTask = taskService.partialUpdate(userId, taskId, task);
                return new ResponseEntity<>(taskMapper.mapTo(updatedTask), HttpStatus.OK);
    }

}
