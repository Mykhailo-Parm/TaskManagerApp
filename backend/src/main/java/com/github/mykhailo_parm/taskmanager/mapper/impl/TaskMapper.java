package com.github.mykhailo_parm.taskmanager.mapper.impl;

import com.github.mykhailo_parm.taskmanager.dto.TaskDTO;
import com.github.mykhailo_parm.taskmanager.mapper.Mapper;
import com.github.mykhailo_parm.taskmanager.model.Task;
import lombok.Data;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class TaskMapper implements Mapper<Task, TaskDTO> {

    private final ModelMapper modelMapper;

    @Value("${app.baseUrl}")
    private String baseUrl;  // Base URL for generating user links

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // Custom converter to generate the user URL from the user's ID
        Converter<Long, String> userToUrlConverter = ctx -> baseUrl + "/api/users/" + ctx.getSource();

        // Define a custom mapping between Task and TaskDTO
        this.modelMapper.addMappings(new PropertyMap<Task, TaskDTO>() {
            @Override
            protected void configure() {
                using(userToUrlConverter).map(source.getUser().getId(), destination.getUserUrl());
            }
        });
    }

    @Override
    public TaskDTO mapTo(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public Task mapFrom(TaskDTO taskDTO) {
         return modelMapper.map(taskDTO, Task.class);
    }
}
