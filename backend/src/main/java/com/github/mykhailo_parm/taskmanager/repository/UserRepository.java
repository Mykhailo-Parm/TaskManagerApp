package com.github.mykhailo_parm.taskmanager.repository;

import com.github.mykhailo_parm.taskmanager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
