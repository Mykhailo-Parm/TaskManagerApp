package com.github.mykhailo_parm.taskmanager.service.impl;

import com.github.mykhailo_parm.taskmanager.mapper.Mapper;
import com.github.mykhailo_parm.taskmanager.model.Task;
import com.github.mykhailo_parm.taskmanager.model.User;
import com.github.mykhailo_parm.taskmanager.repository.TaskRepository;
import com.github.mykhailo_parm.taskmanager.repository.UserRepository;
import com.github.mykhailo_parm.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Task save(Long userId, Task task) {
        // Find the user by id
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        task.setUser(user);  // Associate task with user

        // Set the createdAt field to the current time
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(LocalDateTime.now());
        }

        Task savedTask = taskRepository.save(task);

        return savedTask;
    }

    @Override
    public Optional<Task> findById(Long userId, Long taskId) {
        Optional<Task> task = Optional.ofNullable(taskRepository.findById(taskId)
                .filter(t -> t.getUser().getId().equals(userId))
                .orElseThrow(() -> new IllegalArgumentException("Task not found or does not belong to user")));
        return task;
    }

    @Override
    public List<Task> findAllByUser(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return new ArrayList<>(tasks);
    }

    @Override
    public void delete(Long userId, Long taskId) {
        Task task = taskRepository.findById(taskId)
                .filter(t -> t.getUser().getId().equals(userId))
                .orElseThrow(() -> new IllegalArgumentException("Task not found or does not belong to user"));

        // Delete the task
        taskRepository.delete(task);
    }

    @Override
    public boolean isExists(Long userId, Long taskId) {
        return taskRepository.findById(taskId).filter(t -> t.getUser().getId().equals(userId)).isPresent();
    }

    @Override
    public Task partialUpdate(Long userId, Long taskId, Task task) {
        task.setId(taskId);

        return taskRepository.findById(taskId)
                .filter(t -> t.getUser().getId().equals(userId))
                .map(existingTask -> {
                    Optional.ofNullable(task.getTitle()).ifPresent(existingTask::setTitle);
                    Optional.ofNullable(task.getDescription()).ifPresent(existingTask::setDescription);
                    Optional.ofNullable(task.getDue()).ifPresent(existingTask::setDue);
                    Optional.ofNullable(task.getCreatedAt()).ifPresent(existingTask::setCreatedAt);
                    Optional.ofNullable(task.getStatus()).ifPresent(existingTask::setStatus);
                    Optional.ofNullable(task.getUser()).ifPresent(existingTask::setUser);
                    return taskRepository.save(existingTask);
        }).orElseThrow(() -> new RuntimeException("Task does not exist"));
    }
}
