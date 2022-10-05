package com.foxborn.controller;

import com.foxborn.dto.UserDTO;
import com.foxborn.service.RoleService;
import com.foxborn.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    // 1. injecting services
    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }
    RoleService roleService;
    UserService userService;

    //2. get the form create
    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.findAll());  //inject services
        model.addAttribute("users", userService.findAll());
        return "/user/create";
    }


    //3. post the form create
    // @ModelAttribute("user") UserDTO user, -- to pass from the loaded form into the post
    @PostMapping ("/create")
    public String insertUser(UserDTO user){
 /*   public String insertUser(@ModelAttribute("user") UserDTO user, Model model){
        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.findAll());  //inject services
   */
        userService.save(user);  // -- save in the map

//        model.addAttribute("users", userService.findAll());
//        return "/user/create";  // attributes needed for this html: users, roles, user

        return "redirect:/user/create";  // redirect to endpoint, not html .... endpoint that already sets attributes needed for this html: users, roles, user
    }


    //  click update button on a user - @get the form prefilled to update
    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username, Model model){

        model.addAttribute("user", userService.findById(username));  // now its not New user empty form, but populated, so use service to find user by id
        model.addAttribute("roles", roleService.findAll());  //inject services
        model.addAttribute("users", userService.findAll());

        return "/user/update";
    }

    // post/re-create updated object back to db
    @PostMapping("/update")
    public String updateUser(UserDTO userDTO){

        userService.update(userDTO);
        return "redirect:/user/create";
        //return "/user/create"; // go  to this html and look for which model attributes to add
    }


    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username){

    userService.deleteById(username);

        return "redirect:/user/create";
    }

}
