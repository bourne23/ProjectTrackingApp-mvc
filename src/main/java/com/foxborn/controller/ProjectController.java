package com.foxborn.controller;

import com.foxborn.dto.ProjectDTO;
import com.foxborn.dto.UserDTO;
import com.foxborn.service.ProjectService;
import com.foxborn.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *     <a th:href="@{/project/update/{id}(id=${project.getProjectCode()})}"><button type="button" class="btn btn-warning rounded-0">Update</button></a>
 *     <a th:href="@{/project/delete/{id}(id=${project.getProjectCode()})}"><button type="button" class="btn btn-danger rounded-0">Delete</button></a>
 *     <a th:href="@{/project/complete/{id}(id=${project.getProjectCode()})}"><button type="button" class="btn btn-info rounded-0">Complete</button></a>
 *     <form th:action="@{/project/create}" method="post" th:object="${project}">
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    ProjectService projectService;
    UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createProject(Model model){
        model.addAttribute("project",new ProjectDTO());
        model.addAttribute("projectList",projectService.findAll());
        model.addAttribute("managers",userService.findManagers());
        return "/project/create";
    }

    @PostMapping("/create")
    public String insertProject(ProjectDTO project){
        projectService.save(project);
        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectcode}")
    public String deleteProject(@PathVariable("projectcode") String projectcode){
        projectService.deleteById(projectcode);
        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectcode}")
    public String completeProject(@PathVariable("projectcode") String projectcode){
        projectService.complete(projectService.findById(projectcode));
        return "redirect:/project/create";
    }

    /**
     * Update action button uses two methods: Get to fill the form and Post to save
     */
    @GetMapping("/update/{projectcode}")
    public String editProject(@PathVariable("projectcode") String projectcode,Model model){
        model.addAttribute("project",projectService.findById(projectcode));//pass an object that's not new/empty
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers",userService.findManagers());
        return "/project/update";
    }

    @PostMapping("/update")
    public String updateProject(ProjectDTO project){
        projectService.update(project);
        return "redirect:/project/create";
    }


@GetMapping("/manager/project-status")
    public String getProjectsbyManager(Model model){

    UserDTO manager = userService.findById("john@foxborn.com"); // this is the manager who is currently logged in the portal. Security will implement this.

    // service returns a list of projects that belong to the current manager only
    List<ProjectDTO> projects = projectService.getCountedListOfProjectDTO(manager);

    model.addAttribute("projects",projects);

        return "/manager/project-status";
}


}
