package com.librarysystem.demo.controller;

import com.librarysystem.demo.model.User;
import com.librarysystem.demo.repository.UserRepository;
import com.librarysystem.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository ;

    @GetMapping("/{id}")
    public String showById(@PathVariable Long id, Model model){
        userRepository.findById(id).ifPresent(user -> model.addAttribute("user", user));

        return "show";
    }

    @GetMapping("/list")
    public String listUser(Model model){
        model.addAttribute("users", userRepository.findAll());

        return "list";
    }

    @GetMapping("/register")
    public String getRegisterForm(){
        return "addUser";
    }

    @PostMapping("")
    public String saveUser(User user){
        userRepository.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model){
        userRepository.findById(id).ifPresent(user -> model.addAttribute("user", user));
        return "userEdit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, User user){
        user.setId(id);
        userRepository.save(user);
        return "redirect:/user/" + user.getId();
    }
}
