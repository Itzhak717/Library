package com.librarySystem.demo.controller;

import com.librarySystem.demo.model.User;
import com.librarySystem.demo.service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Operation(
            summary = "根據id取得使用者資料"
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUser(id);

        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "列出所有使用者"
    )
    @GetMapping
    public ResponseEntity<Iterable<User>> listUser(){
        Iterable<User> users = userService.getUsers();

        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "新增使用者"
    )
    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        userService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(user);
    }

    @Operation(
            summary = "更新使用者資料"
    )
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user){
        userService.updateUser(id,user);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "刪除使用者"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
