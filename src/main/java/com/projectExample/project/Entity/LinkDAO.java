package com.projectExample.project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Link")
public class LinkDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int linkId;

    @Column(name = "Web Address")
    private String address;

    @Column(name = "Port")
    private int port;

    @Column(name = "Link Type")
    private String type;

    @Column(name = "Project_id")
    private int projectId;
}
