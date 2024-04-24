package com.projectExample.project.Helper;

import com.projectExample.project.Entity.Project;
import org.springframework.stereotype.Component;

@Component
public class UpdateHelper {

    public Project updatedDate(Project oldData, Project updates){
        //check that old projectname and new projectName are same or not
        if(!oldData.getProjectName().equals(updates.getProjectName()) && updates.getProjectName()!=null){
            oldData.setProjectName(updates.getProjectName());
        }

        if(!(oldData.getCost() == updates.getCost()) && updates.getCost()!=0){
            oldData.setCost(updates.getCost());
        }

        if(!(oldData.getDescription().equals(updates.getDescription())) && updates.getDescription()==null){
            oldData.setDescription(updates.getDescription());
        }

        if(oldData.getTechnology().equals(updates.getTechnology()) && updates.getTechnology()!=null){
            oldData.setTechnology(updates.getTechnology());
        }
        return updates;
    }
}
