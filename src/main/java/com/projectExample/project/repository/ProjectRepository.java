package com.projectExample.project.repository;

import com.projectExample.project.DTO.ProjectDTO;
import com.projectExample.project.Entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer > {
    public Project findById(int id);
    public Project deleteById(int id);

    public Project save(Project project);
}
