package com.foxborn.service.impl;

import com.foxborn.dto.ProjectDTO;
import com.foxborn.dto.TaskDTO;
import com.foxborn.dto.UserDTO;
import com.foxborn.enums.Status;
import com.foxborn.service.ProjectService;
import com.foxborn.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {
    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

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
    @Override
    public List<ProjectDTO> findAllNonCompletedProjects() {
        return findAll().stream().filter(project -> !project.getProjectStatus().equals(Status.COMPLETE)).collect(Collectors.toList());
    }

    /**
     *
     * @param manager
     * @return
     */
    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList =
                findAll()
                        .stream()
                        .filter(project -> project.getAssignedManager().equals(manager))  // get projects by manager from DB
                        .map( project -> {                                                // then map each project to Java ProjectDTO with all args constructor

                            // get all tasks that belong to the manager
                            List<TaskDTO> taskList = taskService.findTasksByManager(manager);

                            // filter tasks that belong to the project and are 'completed'
                            int completeTaskCounts = (int)taskList.stream().filter(t ->  t.getProject().equals(project) && t.getTaskStatus() == Status.COMPLETE).count();

                            // filter tasks that belong to the project and are 'not completed'
                            int unfinishedTaskCounts = (int)taskList.stream().filter(t ->  t.getProject().equals(project) && t.getTaskStatus() != Status.COMPLETE).count();

                            project.setCompleteTaskCounts(completeTaskCounts);
                            project.setUnfinishedTaskCounts(unfinishedTaskCounts);

                            return project;

                        }).collect(Collectors.toList());


        return projectList;
    }

}


