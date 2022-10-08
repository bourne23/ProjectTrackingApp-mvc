package com.foxborn.service.impl;

import com.foxborn.dto.ProjectDTO;
import com.foxborn.enums.Status;
import com.foxborn.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    @Override
    public ProjectDTO save(ProjectDTO object) {

        if(object.getProjectStatus() == null){
            object.setProjectStatus(Status.OPEN);
        }

        return super.save(object.getProjectCode(),object);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {

        ProjectDTO newproject = findById(object.getProjectCode());

        if(object.getProjectStatus() == null)
            object.setProjectStatus(newproject.getProjectStatus());
        super.update(object.getProjectCode(),object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public ProjectDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public void complete(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.COMPLETE);
        //save object in db
        super.save(projectDTO.getProjectCode(),projectDTO);
    }

}

