package com.projectExample.project.util;
import com.projectExample.project.DTO.LinkDTO;
import com.projectExample.project.Entity.LinkDAO;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class LinkUtil {
    public List<LinkDTO> linkDaoToDtoConvertion(List<LinkDAO> linkDaos){
        List<LinkDTO> linkDTOs = new ArrayList<>();
        for(LinkDAO linkDAO : linkDaos){
            LinkDTO linkDTO = new LinkDTO();
            linkDTO.setAddress(linkDAO.getAddress());
            linkDTO.setPort(linkDAO.getPort());
            linkDTO.setType(linkDAO.getType());
            linkDTO.setProjectId(linkDAO.getProjectId());
            linkDTOs.add(linkDTO);
        }
        return linkDTOs;
    }

    public List<LinkDAO> linkDtoToDaoConvertion(List<LinkDTO> linkDtos){
        List<LinkDAO> linkDAOs = new ArrayList<>();
        for(LinkDTO linkDTO : linkDtos){
            LinkDAO linkDAO = new LinkDAO();
            linkDAO.setAddress(linkDTO.getAddress());
            linkDAO.setPort(linkDTO.getPort());
            linkDAO.setType(linkDTO.getType());
            linkDAO.setProjectId(linkDTO.getProjectId());
            linkDAOs.add(linkDAO);
        }
        return linkDAOs;
    }
}
