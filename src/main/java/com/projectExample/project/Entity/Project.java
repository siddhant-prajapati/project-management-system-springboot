package com.projectExample.project.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.lang.annotation.RequiredTypes;

import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pId;

    @Column(name = "Project Name")
    //@NonNull
    private String projectName;

    @Column(name = "Project Start Date")
    private String projectStartDate;

    @Column(name = "Project End Date")
    private String projectEndDate;

    @Column(name = "Total Cost")
    private double Cost;

    @Column(name = "Is Completed")
    private boolean completed;

    @Column(name = "Description")
    //@NonNull
    private String description;

    //@Column(name = "Link")
    @OneToMany(cascade = CascadeType.ALL)
    private List<LinkDAO> link;

    @Column(name = "Technology")
    //@NonNull
    private List<String> technology;

}
