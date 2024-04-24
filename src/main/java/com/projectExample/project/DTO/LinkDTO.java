package com.projectExample.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkDTO {
    private int linkId;
    private String address;
    private int port;
    private String type;
    private int projectId;
}
