package com.librarySystem.demo.controller;

import com.librarySystem.demo.model.User;
import com.librarySystem.demo.service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Operation(
            summary = "根據id取得使用者資料",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get User info successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@Parameter(description = "User id") @PathVariable Long id) {
        User user = userService.getUser(id);

        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "列出所有使用者",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get All User info successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
                            content = @Content
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Iterable<User>> listUser() {
        Iterable<User> users = userService.getUsers();

        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "新增使用者",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Save new user info successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "User Email exist.",
                            content = @Content
                    )
            }
    )
    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "更新使用者資料",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Update user info successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
                            content = @Content
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Parameter(description = "User Id") @PathVariable Long id,
                                           @Parameter(description = "New user info") @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "刪除使用者",
            responses = {
                    @ApiResponse(
                            responseCode = "203",
                            description = "delete user info successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "User Id") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
