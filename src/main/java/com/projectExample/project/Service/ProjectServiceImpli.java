package com.projectExample.project.Service;

import com.projectExample.project.DTO.ProjectDTO;
import com.projectExample.project.Entity.Project;
import com.projectExample.project.Helper.UpdateHelper;
import com.projectExample.project.repository.ProjectRepository;
import com.projectExample.project.util.ProjectUtil;
import com.projectExample.project.util.ProjectValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpli implements ProjectService {

    @Autowired
    private UpdateHelper updateHelper;

    @Autowired
    private ProjectRepository projectRepository;


    @Autowired
    private ProjectUtil projectUtil;

    @Autowired
    private ProjectValidation projectValidation;
    // Service method for getting all projects
    @Override
    public ResponseEntity<List<ProjectDTO>> getAllProject(){
        List<Project> list = (List<Project>) this.projectRepository.findAll();

        //Handle no data exception
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.of(Optional.of(projectUtil.projectDaoToDtoConvertion(list)));
    }

    // Service method for get Project using Id
    @Override
    public ResponseEntity<ProjectDTO> getProjectById(int id) {

        ProjectDTO projectDTO;
        try {
            projectDTO = projectUtil.projectDaoToDtoConvertion(Arrays.asList(this.projectRepository.findById(id))).get(0);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.of(Optional.of(projectDTO));
    }

    // Service method for creating new project
    @Override
    public ResponseEntity<ProjectDTO> createProject(Project data){
        if(projectValidation.validation(data)){
            ProjectDTO result =
                    projectUtil.projectDaoToDtoConvertion(List.of(this.projectRepository.save(data))).get(0);
            System.out.println(result);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Service method for update existing project
    @Override
    public ResponseEntity<ProjectDTO> updateProject(int id, ProjectDTO project){
        try{
            List<Project> updatedProject=projectUtil.projectDtoToDaoConvertion(List.of(project));
            //System.out.println("DTO data: "+ project);
            //System.out.println("UpdatedProject data: "+ updatedProject.get(0));
            if(projectValidation.validation(updatedProject.get(0))){
                updatedProject.get(0).setPId(id);
                projectUtil.projectDaoToDtoConvertion(List.of(this.projectRepository.save(updatedProject.get(0))));
                //System.out.println("This is executed 2");
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Service method for deleting existing project
    @Override
    public ResponseEntity<ProjectDTO> deleteProject(int id){

        try{
            projectRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<ProjectDTO> deleteAllProjects() {
        try{
            projectRepository.deleteAll();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
