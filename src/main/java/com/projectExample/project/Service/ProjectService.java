package com.projectExample.project.Service;

import com.projectExample.project.DTO.ProjectDTO;
import com.projectExample.project.Entity.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {
    public ResponseEntity<List<ProjectDTO>> getAllProject();
    public ResponseEntity<ProjectDTO> getProjectById(int id);
    public ResponseEntity<ProjectDTO> createProject(Project data);
    public  ResponseEntity<ProjectDTO> updateProject(int id, ProjectDTO data);

    public ResponseEntity<ProjectDTO> deleteProject(int id);

    public ResponseEntity<ProjectDTO> deleteAllProjects();
}
