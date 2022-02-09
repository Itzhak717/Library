package com.librarySystem.demo.controller;

import com.librarySystem.demo.model.AuthRequest;
import com.librarySystem.demo.model.User;
import com.librarySystem.demo.security.JWTService;
import com.librarySystem.demo.service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

//@CrossOrigin
@RestController
@RequestMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserServiceImpl userService;

    @Operation(
            summary = "根據Email和密碼登入帳號",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "login successfully",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "wrong username or password",
                            content = @Content
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Map<String ,String>> issueToken(@Valid @RequestBody AuthRequest request){
        User user = userService.getUserByEmail(request.getEmail());
        String token = jwtService.generateToken(request);
        Map<String ,String> response = new HashMap<>();
        response.put("token", token);
        response.put("userId",user.getId().toString());

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "解析JWT",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "parse token successfully",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "JWT Unauthorized",
                            content = @Content
                    )
            }
    )
    @PostMapping("/parse")
    public ResponseEntity<Map<String ,Object>> parseToken(@Parameter(description = "JSON Web Token") @RequestBody String token){
        Map<String , Object> response = jwtService.parseToken(token);

        return ResponseEntity.ok(response);
    }
}


