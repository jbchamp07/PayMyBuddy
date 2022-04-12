package com.openclassrooms.PayMyBuddy.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    @Transactional
    public void loginTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login").secure(true)
                        //.contentType( MediaType.parseMediaType("application/x-www-form-urlencoded"))
                        .param("username","jb.champetier@gmail.com").param("password","a"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
