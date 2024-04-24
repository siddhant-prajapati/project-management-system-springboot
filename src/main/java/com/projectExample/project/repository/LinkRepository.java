package com.projectExample.project.repository;

import com.projectExample.project.Entity.LinkDAO;
import org.springframework.data.repository.CrudRepository;

public interface LinkRepository extends CrudRepository<LinkDAO, Integer> {
    public LinkDAO findById(int id);
    public LinkDAO deleteById(int id);
}
