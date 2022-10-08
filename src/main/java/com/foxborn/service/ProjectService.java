package com.foxborn.service;

import com.foxborn.dto.ProjectDTO;
import com.foxborn.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO, String> {

    void complete(ProjectDTO projectDTO);

    List<ProjectDTO> findAllNonCompletedProjects();

    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager);

}
