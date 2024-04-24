package com.projectExample.project.repository;

import com.projectExample.project.Entity.UserDAO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDAO, Integer> {
    public UserDAO findById(int id);

    public UserDAO deleteById(int id);

    public UserDAO save(UserDAO user);

    public UserDAO findByEmail(String email);
}
