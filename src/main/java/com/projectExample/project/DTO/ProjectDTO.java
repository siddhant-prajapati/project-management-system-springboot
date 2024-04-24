package com.projectExample.project.DTO;

import com.projectExample.project.Entity.LinkDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private int pId;
    private String projectName;
    private String projectStartDate;
    private String projectEndDate;
    private double Cost;
    private boolean completed;
    private String description;
    private List<LinkDTO> link;
    private List<String> technology;
}
