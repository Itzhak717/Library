package com.librarySystem.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarySystem.demo.model.User;
import com.librarySystem.demo.model.UserAuthority;
import com.librarySystem.demo.security.AuthRequest;
import com.librarySystem.demo.service.Impl.BorrowedServiceImpl;
import com.librarySystem.demo.service.Impl.UserServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BorrowedControllerTest {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    BorrowedServiceImpl borrowedService;

    @Autowired
    MockMvc mockMvc;

    protected HttpHeaders httpHeaders;
    protected final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public void initHttpHeader() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void borrowedBook() throws Exception {
        String password = "1234";
        User user = new User();
        user.setUsername("asdf");
        user.setEmail("asd@mail.com");
        user.setName("n");
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setSex("male");
        user.setTelephone("123456789");
        user.setAuthorities(Collections.singletonList(UserAuthority.ADMIN));
        userService.createUser(user);

        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(user.getUsername());
        authRequest.setPassword(password);
        MvcResult result = mockMvc.perform(post("/auth")
                .headers(httpHeaders)
                .content(mapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk())
                .andReturn();

        JSONObject tokenRes = new JSONObject(result.getResponse().getContentAsString());
        String accessToken = tokenRes.getString("token");

        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        JSONObject borrowedReq = new JSONObject();
        borrowedReq.put("book_id" ,"1");

        mockMvc.perform(post("/borrowed")
        .headers(httpHeaders)
        .content(borrowedReq.toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.book_id").value(borrowedReq.getString("book_id")));
    }
}