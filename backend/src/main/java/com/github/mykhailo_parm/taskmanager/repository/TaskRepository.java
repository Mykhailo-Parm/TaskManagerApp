package com.github.mykhailo_parm.taskmanager.repository;

import com.github.mykhailo_parm.taskmanager.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
