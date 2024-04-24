package com.projectExample.project.Controller;

import com.projectExample.project.DTO.LinkDTO;
import com.projectExample.project.Entity.LinkDAO;
import com.projectExample.project.Service.LinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/links")
public class LinkController {
    @Autowired
    LinkServiceImpl linkServiceImpl;

    @GetMapping("/")
    public ResponseEntity<List<LinkDTO>> getAllLinks(){
        System.out.println("Hello");
        return this.linkServiceImpl.getAllLinks();
    }

    @GetMapping("/{lid}")
    public ResponseEntity<LinkDTO> getLinkById(@PathVariable("lid") int id){
        return this.linkServiceImpl.getLinkById(id);
    }

    @PostMapping("/")
    public ResponseEntity<LinkDTO> addLink(@RequestBody LinkDAO link){
        return this.linkServiceImpl.createLink(link);
    }

    @DeleteMapping("/{lid}")
    public ResponseEntity<LinkDTO> deleteLinkById(@PathVariable("lid") int id){
        return this.linkServiceImpl.deleteLink(id);
    }

    @PutMapping("/{lid}")
    public ResponseEntity<LinkDTO> updateLinkById( @PathVariable("lid") int id, @RequestBody LinkDAO link){
        return this.linkServiceImpl.updateLink(id, link);
    }
}
