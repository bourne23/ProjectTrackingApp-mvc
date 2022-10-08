package com.foxborn.service;

import com.foxborn.dto.ProjectDTO;

public interface ProjectService extends CrudService<ProjectDTO, String> {

    void complete(ProjectDTO projectDTO);
}
