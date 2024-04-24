package com.projectExample.project.Service;

import com.projectExample.project.DTO.LinkDTO;
import com.projectExample.project.DTO.ProjectDTO;
import com.projectExample.project.Entity.LinkDAO;
import com.projectExample.project.repository.LinkRepository;
import com.projectExample.project.util.LinkUtil;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LinkServiceImpl implements LinkService{

    @Autowired
    LinkRepository linkRepository;


    @Autowired
    LinkUtil linkUtil;
    @Override
    public ResponseEntity<List<LinkDTO>> getAllLinks() {
        List<LinkDAO> links = (List<LinkDAO>)this.linkRepository.findAll();
        if(links.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(linkUtil.linkDaoToDtoConvertion(links)));
    }

    @Override
    public ResponseEntity<LinkDTO> getLinkById(int id) {
        LinkDTO linkDTO = null;
        try{
            linkDTO  = linkUtil.linkDaoToDtoConvertion(Arrays.asList(this.linkRepository.findById(id))).get(0);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.of(Optional.of(linkDTO));
    }

    @Override
    public ResponseEntity<LinkDTO> createLink(LinkDAO link) {
        LinkDTO result =
                linkUtil.linkDaoToDtoConvertion(List.of(this.linkRepository.save(link))).get(0);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<LinkDTO> updateLink(int id, LinkDAO link) {
        link.setLinkId(id);
        linkUtil.linkDaoToDtoConvertion(List.of(this.linkRepository.save(link)));

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<LinkDTO> deleteLink(int id) {
        try{
            linkUtil.linkDaoToDtoConvertion(List.of(linkRepository.deleteById(id)));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
