package com.librarySystem.demo.controller;

import com.librarySystem.demo.security.AuthRequest;
import com.librarySystem.demo.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/auth",produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public ResponseEntity<Map<String ,String>> issueToken(@Valid @RequestBody AuthRequest request){
        String token = jwtService.generateToken(request);
        Map<String ,String> response = Collections.singletonMap("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/parse")
    public ResponseEntity<Map<String ,Object>> parseToken(@RequestBody Map<String , String> request){
        String token = request.get("token");
        Map<String , Object> response = jwtService.parseToken(token);

        return ResponseEntity.ok(response);
    }
}


