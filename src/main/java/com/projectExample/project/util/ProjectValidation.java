package com.projectExample.project.util;
import com.projectExample.project.Entity.Project;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ProjectValidation {
    private boolean validateName(String projectName){
        try{
            if(projectName.isEmpty()){
                throw new Exception("Project Name must not empty");
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }

    private boolean validateDates(String projectStartDate, String projectEndDate){
        try{
            String DATE_PATTERN = "^\\d{2}/\\d{2}/\\d{4}$";
            if(Pattern.matches(DATE_PATTERN, projectStartDate) && Pattern.matches(DATE_PATTERN, projectEndDate)){
                Date startDate = new SimpleDateFormat("dd/mm/yyyy").parse(projectStartDate);
                Date endDate = new SimpleDateFormat("dd/mm/yyyy").parse(projectEndDate);
                if(startDate.getTime() > endDate.getTime()){
                    throw new Exception("Start date should not more than End date Patter 'dd/mm/yyyy' ");
                }
            }
        } catch (Exception e){
            System.out.println("validate date failed");
            return false;
        }
        System.out.println("validate date completed");
        return true;
    }


    private boolean validateCost(double cost){
        try{
            if(cost < 0){
                throw new Exception("Cost must not negative value");
            }
        } catch (Exception e){
            System.out.println("validate cost failed");
            return false;
        }
        System.out.println("validate cost completed");
        return true;
    }


    private boolean validateDescription(String description){
        System.out.println(description + ": "+ description.length());
        try{
            if(!(!description.isEmpty() && description.length() < 500)){
                throw new Exception("Description must not empty or more than 500 characters!");
            }
        } catch (Exception e){
            System.out.println("validate description failed");
            return false;
        }
        System.out.println("validate description completed");
        return true;
    }

    private boolean validateTechnology(List<String> technology){
        try{
            if(technology.isEmpty()){
                throw new Exception("Technology is required!");
            }
        } catch (Exception e){
            System.out.println("validate technology failed");
            return false;
        }
        System.out.println("validate technology completed");
        return true;
    }

    public boolean validation(Project project){
        ProjectValidation projectValidation = new ProjectValidation();
        System.out.println(project.getProjectName());
        int counter = 0;
        if(projectValidation.validateCost(project.getCost())){counter++;}
        if(projectValidation.validateDates(project.getProjectStartDate(), project.getProjectEndDate())){counter++;}
        if(projectValidation.validateDescription(project.getDescription())){counter++;}
        if(projectValidation.validateName(project.getProjectName())){counter++;}
        if(projectValidation.validateTechnology(project.getTechnology())){counter++;}
        return counter == 5;
    }

}
