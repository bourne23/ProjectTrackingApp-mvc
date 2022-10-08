package com.foxborn.bootstrap;

import com.foxborn.dto.ProjectDTO;
import com.foxborn.dto.RoleDTO;
import com.foxborn.dto.TaskDTO;
import com.foxborn.dto.UserDTO;
import com.foxborn.enums.Gender;
import com.foxborn.enums.Status;
import com.foxborn.service.ProjectService;
import com.foxborn.service.RoleService;
import com.foxborn.service.TaskService;
import com.foxborn.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements CommandLineRunner {
//CommandLineRunner will initiate , load and execute the app
   RoleService roleService;
   UserService userService;
   ProjectService projectService;

    TaskService taskService;

    public DataGenerator(RoleService roleService, UserService userService, ProjectService projectService, TaskService taskService) {
        this.roleService = roleService;
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {

        // 1. create roles
        RoleDTO adminRole = new RoleDTO(1L,"Admin");
        RoleDTO managerRole = new RoleDTO(2L,"Manager");
        RoleDTO employeeRole = new RoleDTO(3L,"Employee");

        // 2. add roles to map - database - .save
        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);

        //3.
        UserDTO user1 = new UserDTO("John", "Kissy",
                "john@foxborn.com", "Abc1", true, "7459684532", managerRole, Gender.MALE);
        UserDTO user5 = new UserDTO("Mike", "Smith",
                "mike@foxborn.com", "Abc2", true, "7459684532", adminRole, Gender.MALE);
        UserDTO user2 = new UserDTO("Delisa",
                "Norre", "delisa@foxborn.com", "123", true, "8567412358", managerRole, Gender.FEMALE);
        UserDTO user3 = new UserDTO("Craig", "Jark",
                "craig@foxborn.com", "Abc3", true, "7777775566", employeeRole, Gender.MALE);
        UserDTO user4 = new UserDTO("Shaun",
                "Hayns", "shaun@foxborn.com", "Abc4", true, "3256987412", managerRole, Gender.MALE);
        UserDTO user6 = new UserDTO("Elizebeth",
                "Loren", "elizebeth@foxborn.com", "Abc4", true, "5306987412", employeeRole, Gender.FEMALE);
        UserDTO user7 = new UserDTO("Maria",
                "Ada", "maria@foxborn.com", "Abc4", true, "9996987412", employeeRole, Gender.FEMALE);
        UserDTO user8 = new UserDTO("Bill",
                "Matt", "bill@foxborn.com", "Abc4", true, "8881239846", employeeRole, Gender.MALE);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);


        //4.
        ProjectDTO project1 = new ProjectDTO("Spring MVC","PR001",user1, LocalDate.now(),LocalDate.now().plusDays(25),"Creating Controllers", Status.OPEN);
        ProjectDTO project2 = new ProjectDTO("Spring ORM","PR002",user2, LocalDate.now(),LocalDate.now().plusDays(10),"Creating Database", Status.IN_PROGRESS);
        ProjectDTO project3 = new ProjectDTO("Spring Container","PR003",user1, LocalDate.now(),LocalDate.now().plusDays(32),"Creating Container", Status.IN_PROGRESS);

        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);




        TaskDTO task1 = new TaskDTO(project1, user8, "Controller", "Request Mapping", Status.IN_PROGRESS, LocalDate.now().minusDays(4));
        TaskDTO task2 = new TaskDTO(project3, user3, "Configuration", "Database Connection", Status.COMPLETE, LocalDate.now().minusDays(12));
        TaskDTO task3 = new TaskDTO(project3, user6, "Mapping", "One-To-Many", Status.COMPLETE, LocalDate.now().minusDays(8));
        TaskDTO task4 = new TaskDTO(project2, user7, "Dependency Injection", "Autowired", Status.IN_PROGRESS, LocalDate.now().minusDays(20));

        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);

    }
}
