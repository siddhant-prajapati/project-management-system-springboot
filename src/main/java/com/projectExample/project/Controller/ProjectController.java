package com.projectExample.project.Controller;

import com.projectExample.project.DTO.ProjectDTO;
import com.projectExample.project.Entity.Project;
import com.projectExample.project.Service.ProjectServiceImpli;
import com.projectExample.project.util.ProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private ProjectServiceImpli projectServiceImpli;

    @Autowired
    ProjectUtil projectUtil;

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDTO>> getAllProject(){
        return this.projectServiceImpli.getAllProject();
    }

    @GetMapping("/get/{pid}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("pid") int id){
        return this.projectServiceImpli.getProjectById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<ProjectDTO> addProject(@RequestBody Project project){
        return this.projectServiceImpli.createProject(project);
    }


    @DeleteMapping("/delete/{pid}")
    public ResponseEntity<ProjectDTO> deleteProjectById(@PathVariable("pid") int id){
        return this.projectServiceImpli.deleteProject(id);
    }

    @PutMapping("/update/{pid}")
    public ResponseEntity<ProjectDTO> updateProjectById( @PathVariable("pid") int id, @RequestBody ProjectDTO project){
        return this.projectServiceImpli.updateProject(id, project);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<ProjectDTO> deleteAllProject(){
         return this.projectServiceImpli.deleteAllProjects();
    }
}
