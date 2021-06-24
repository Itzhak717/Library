package com.librarysystem.demo.controller;

import com.librarysystem.demo.model.User;
import com.librarysystem.demo.repository.UserRepository;
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

        return "user/show";
    }

    @GetMapping("/list")
    public String listUser(Model model){
        model.addAttribute("users", userRepository.findAll());

        return "user/list";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user/addUser";
    }

    @PostMapping("")
    public String saveUser(User user){
        userRepository.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model){
        userRepository.findById(id).ifPresent(user -> model.addAttribute("user", user));
        return "user/userEdit";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id, User user){
        user.setId(id);
        userRepository.save(user);
        return "redirect:/user/list";
    }

    @PostMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return "redirect:/user/list";
    }
}
