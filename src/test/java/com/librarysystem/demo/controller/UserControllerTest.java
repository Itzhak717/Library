package com.librarySystem.demo.controller;

import com.librarySystem.demo.model.User;
import com.librarySystem.demo.service.Impl.UserServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    private HttpHeaders httpHeaders;
    private User user;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserServiceImpl userService;

    @BeforeAll
    public void setUp() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    private User createUser(String name,String password,String sex,String email,String telephone,String username){
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setSex(sex);
        user.setTelephone(telephone);
        user.setUsername(username);

        return user;
    }

    @Test
    void saveUser() throws Exception{
        JSONObject request = new JSONObject()
                .put("email","test@mail.com")
                .put("name","z")
                .put("password","1234")
                .put("sex","male")
                .put("telephone","0123456789")
                .put("username","abc");

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post("/user")
                        .headers(httpHeaders)
                        .content(request.toString());

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.email").value(request.getString("email")))
                .andExpect(jsonPath("$.name").value(request.getString("name")))
                //.andExpect(jsonPath("$.password").value(passwordEncoder.encode(request.getString("password"))))
                .andExpect(jsonPath("$.sex").value(request.getString("sex")))
                .andExpect(jsonPath("$.telephone").value(request.getString("telephone")))
                .andExpect(jsonPath("$.username").value(request.getString("username")))
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void getUser() throws Exception {
        User user = createUser("a","12345676","female","gettest@mail.com","0123456789","zxc");
        userService.createUser(user);

        mockMvc.perform(get("/user/" + user.getId()).headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                //.andExpect(jsonPath("$.password").value(passwordEncoder.encode(request.getString("password"))))
                .andExpect(jsonPath("$.sex").value(user.getSex()))
                .andExpect(jsonPath("$.telephone").value(user.getTelephone()))
                .andExpect(jsonPath("$.username").value(user.getUsername()));
    }

    @Test
    void listUser() {
    }

    @Test
    void updateUser() throws Exception {
        User user = createUser("a","12345676","female","gettest@mail.com","0123456789","bgyti");
        userService.createUser(user);

        JSONObject request = new JSONObject()
                .put("email","replace@mail.com")
                .put("name","a")
                .put("password","1234")
                .put("sex","male")
                .put("telephone","0123456789")
                .put("username","replace");

        mockMvc.perform(put("/user/"+ user.getId())
        .headers(httpHeaders)
        .content(request.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.email").value(request.getString("email")))
                .andExpect(jsonPath("$.name").value(request.getString("name")))
                //.andExpect(jsonPath("$.password").value(passwordEncoder.encode(request.getString("password"))))
                .andExpect(jsonPath("$.sex").value(request.getString("sex")))
                .andExpect(jsonPath("$.telephone").value(request.getString("telephone")))
                .andExpect(jsonPath("$.username").value(request.getString("username")));
    }

    @Test
    void deleteUser() throws Exception {
        User user = createUser("a","12345676","female","test@mail.com","0123456789","gpoe");
        userService.createUser(user);

        mockMvc.perform(delete("/user/" + user.getId())
        .headers(httpHeaders))
                .andExpect(status().isNoContent());
    }
}