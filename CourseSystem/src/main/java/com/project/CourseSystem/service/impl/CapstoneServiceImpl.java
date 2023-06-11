package com.project.CourseSystem.service.impl;

import com.project.CourseSystem.converter.CapstoneConverter;
import com.project.CourseSystem.entity.Capstone;
import com.project.CourseSystem.repository.CapstoneRepository;
import com.project.CourseSystem.service.CapstoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapstoneServiceImpl implements CapstoneService {

    CapstoneRepository capstoneRepository;

    CapstoneConverter capstoneConverter;

    @Autowired
    public CapstoneServiceImpl(CapstoneRepository capstoneRepository, CapstoneConverter capstoneConverter) {
        this.capstoneRepository = capstoneRepository;
        this.capstoneConverter = capstoneConverter;
    }

    @Override
    public List<Capstone> getAllCapstones() {
        List<Capstone> capstoneList = capstoneRepository.findAll();
        return capstoneList;
    }
}
