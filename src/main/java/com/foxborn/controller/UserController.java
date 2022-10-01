package com.foxborn.controller;

import com.foxborn.dto.UserDTO;
import com.foxborn.service.RoleService;
import com.foxborn.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    RoleService roleService;
    UserService userService;

    @GetMapping("/create")
    public String createUser(Model model) {

        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.findAll());  //inject services
        model.addAttribute("users", userService.findAll());
        return "/user/create";
    }


    // @ModelAttribute("user") UserDTO user, -- to pass from the loaded form into the post
    @PostMapping ("/create")
//    public String insertUser(UserDTO user, Model model){
    public String insertUser(@ModelAttribute("user") UserDTO user, Model model){
//        model.addAttribute("user", new UserDTO());
//        model.addAttribute("roles", roleService.findAll());  //inject services
        userService.save(user);
//        model.addAttribute("users", userService.findAll());
//        return "/user/create";  // attributes needed for this html: users, roles, user
        return "redirect:/user/create";  // attributes needed for this html: users, roles, user
    }
}
