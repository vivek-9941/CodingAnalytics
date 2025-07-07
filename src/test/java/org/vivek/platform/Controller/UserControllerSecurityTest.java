package org.vivek.platform.Controller;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.vivek.platform.Security.JwtService;

import java.net.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc//this is intergration testing
class UserControllerSecurityTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JwtService jwtService;
    @Test
    void whenNoToken() throws Exception {
        mvc.perform(get("/auth/user/fetchuser")
                .header("X-Client-Id", "test-client-id")) // Add required header
                .andExpect(status().isUnauthorized());

    }

    @Test
    void whenVaildToken() throws Exception {
        //user with email is already present in DB
        String token = jwtService.generateToken("vivek.v12.developer@gmail.com");
        mvc.perform(get("/auth/user/fetchuser")
                .header("X-Client-Id", "test-client-id" )
                 .header("Authorization", "Bearer " + token)

        ).andExpect(status().isOk());
    }


}