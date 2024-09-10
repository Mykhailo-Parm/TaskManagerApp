package com.github.mykhailo_parm.taskmanager;

import com.github.mykhailo_parm.taskmanager.dto.TaskDTO;
import com.github.mykhailo_parm.taskmanager.mapper.impl.TaskMapper;
import com.github.mykhailo_parm.taskmanager.model.Task;
import com.github.mykhailo_parm.taskmanager.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    private TaskMapper taskMapper;
    private String baseUrl = "http://localhost:8080";

    @BeforeEach
    public void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        taskMapper = new TaskMapper(modelMapper);
        taskMapper.setBaseUrl(baseUrl);  // Set the base URL manually for testing
    }

    @Test
    public void testMapToDTO() {
        // Prepare test data
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password", "USER", null);
        Task task = new Task(1L, "Test Task", "Description", "OPEN", LocalDateTime.now(), LocalDateTime.now(), user);

        // Perform mapping
        TaskDTO taskDTO = taskMapper.mapTo(task);

        // Assert the results
        assertEquals(task.getId(), taskDTO.getId());
        assertEquals(task.getTitle(), taskDTO.getTitle());
        assertEquals(task.getUser().getId(), Long.parseLong(taskDTO.getUserUrl().replace(baseUrl + "/api/users/", "")));
    }

    @Test
    public void testMapFromDTO() {
        // Prepare test data
        TaskDTO taskDTO = new TaskDTO(1L, "Test Task", "Description", "OPEN", LocalDateTime.now(), LocalDateTime.now(), baseUrl + "/api/users/1");

        // Perform mapping
        Task task = taskMapper.mapFrom(taskDTO);

        // Assert the results
        assertEquals(taskDTO.getId(), task.getId());
        assertEquals(taskDTO.getTitle(), task.getTitle());
    }
}
