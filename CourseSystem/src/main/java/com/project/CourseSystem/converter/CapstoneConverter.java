package com.project.CourseSystem.converter;

import com.project.CourseSystem.dto.CapstoneDTO;
import com.project.CourseSystem.entity.Capstone;
import org.springframework.stereotype.Component;

@Component
public class CapstoneConverter {

    public Capstone convertDtoToEntity(CapstoneDTO capstoneDTO) {
        Capstone capstone = new Capstone();
        capstoneDTO.setCapstoneID(capstone.getCapstoneID());
        capstoneDTO.setCapstoneName(capstone.getCapstoneName());
        capstoneDTO.setCapstoneDes(capstone.getCapstoneDes());
        capstoneDTO.setCreatedDate(capstone.getCreatedDate());
        capstoneDTO.setCategoryID(capstone.getCategoryID());
        capstoneDTO.setCapstoneImage(capstone.getCapstoneImage());
        return capstone;

    }

    public CapstoneDTO convertEntityToDTO(Capstone capstone){
        CapstoneDTO capstoneDTO = new CapstoneDTO();
        capstoneDTO.setCapstoneID(capstone.getCapstoneID());
        capstoneDTO.setCapstoneName(capstone.getCapstoneName());
        capstoneDTO.setCapstoneDes(capstone.getCapstoneDes());
        capstoneDTO.setCreatedDate(capstone.getCreatedDate());
        capstoneDTO.setCategoryID(capstone.getCategoryID());
        capstoneDTO.setCapstoneImage(capstone.getCapstoneImage());
        return capstoneDTO;
    }
}
