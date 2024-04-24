package com.projectExample.project.util;

import com.projectExample.project.DTO.LinkDTO;
import com.projectExample.project.DTO.ProjectDTO;
import com.projectExample.project.Entity.LinkDAO;
import com.projectExample.project.Entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectUtil {

    @Autowired
    private LinkUtil linkUtil;
    public List<ProjectDTO> projectDaoToDtoConvertion(List<Project> projectDaoes){
        System.out.println(projectDaoes);
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for(Project projectDao : projectDaoes){
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setPId(projectDao.getPId());
            projectDTO.setProjectName(projectDao.getProjectName());
            projectDTO.setDescription(projectDao.getDescription());
            projectDTO.setProjectEndDate(projectDao.getProjectEndDate());
            projectDTO.setProjectStartDate(projectDao.getProjectStartDate());
            projectDTO.setCost(projectDao.getCost());

            List<LinkDTO> linkDTOS=new ArrayList<>();
            for(LinkDAO  linkDAO : projectDao.getLink()){
                LinkDTO linkDTO = new LinkDTO();
                linkDTO.setProjectId(projectDao.getPId()); //set ProjectId of project to Link.projectId
                linkDTO.setPort(linkDAO.getPort());
                linkDTO.setAddress(linkDAO.getAddress());
                linkDTO.setType(linkDAO.getType());
                linkDTOS.add(linkDTO);
            }
            linkDTOS.addAll(linkUtil.linkDaoToDtoConvertion(projectDao.getLink()));
            projectDTO.setLink(linkDTOS);

            projectDTO.setCompleted(projectDao.isCompleted());
            projectDTO.setTechnology(projectDao.getTechnology());
            System.out.println(projectDTO.getLink());
            projectDTOs.add(projectDTO);
        }
        System.out.println(projectDTOs);
        return projectDTOs;
    }

    public List<Project> projectDtoToDaoConvertion(List<ProjectDTO> projectDTOs){
        List<Project> projectDAOs = new ArrayList<>();
        System.out.println(projectDTOs);
        for(ProjectDTO projectDto : projectDTOs){
            Project projectDAO = new Project();
            projectDAO.setPId(projectDto.getPId());
            projectDAO.setProjectName(projectDto.getProjectName());
            projectDAO.setDescription(projectDto.getDescription());
            projectDAO.setProjectEndDate(projectDto.getProjectEndDate());
            projectDAO.setProjectStartDate(projectDto.getProjectStartDate());
            projectDAO.setCost(projectDto.getCost());

            List<LinkDAO> linkDAOS = new ArrayList<>();
            for(LinkDTO linkDTO: projectDto.getLink()) {
                LinkDAO linkDAO = new LinkDAO();
                linkDAO.setPort(linkDTO.getPort());
                linkDAO.setProjectId(projectDto.getPId());
                linkDAO.setType(linkDTO.getType());
                linkDAO.setAddress(linkDTO.getAddress());
                linkDAOS.add(linkDAO);
            }
            projectDAO.setLink(linkDAOS);

            projectDAO.setCompleted(projectDto.isCompleted());
            projectDAO.setTechnology(projectDto.getTechnology());
            projectDAOs.add(projectDAO);
        }
        return projectDAOs;
    }
}
