package com.projectExample.project.Service;

import com.projectExample.project.DTO.LinkDTO;
import com.projectExample.project.Entity.LinkDAO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LinkService {
    public ResponseEntity<List<LinkDTO>> getAllLinks();
    public ResponseEntity<LinkDTO> getLinkById(int id);
    public ResponseEntity<LinkDTO> createLink(LinkDAO link);
    public ResponseEntity<LinkDTO> updateLink(int id, LinkDAO link);
    public ResponseEntity<LinkDTO> deleteLink(int id);

}
