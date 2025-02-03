package com.crio.stayEase.controller;


import com.crio.stayEase.exchanges.request.LoginRequest;
import com.crio.stayEase.exchanges.request.UserRequest;
import com.crio.stayEase.service.AuthService;
import com.crio.stayEase.exchanges.response.AuthResonse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class AuthControllerTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void registerTest() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1M0BtYWlsLmNvbSIsImlhdCI6MTczODU5NDY3NCwiZXhwIjoxNzM4NjgxMDc0fQ.v-S0B6757TG5v0NCnFdXVfmal_DEuQlZOsnV66ACanw";
        when(authService.register(ArgumentMatchers.any()))
                .thenReturn(new AuthResonse("User registered successfully", token));



        URI uri = new URI("/auth/register");


        MockHttpServletResponse resp = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new UserRequest("v1@mail.com", "v@123", "vivek", "boss")))
        ).andReturn().getResponse();


        assertEquals(201, resp.getStatus());
        String expected = objectMapper.writeValueAsString(new AuthResonse("User registered successfully", token));
        assertEquals(expected, resp.getContentAsString());

    }

    @Test
    void loginTest() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1MUBtYWlsLmNvbSIsImlhdCI6MTczODU5NDM1NCwiZXhwIjoxNzM4NjgwNzU0fQ.xKbrTEN-o9VSauPIk7_ffVCOAD2v-bsFSpndnzrgNjk";

        when(authService.login(ArgumentMatchers.any()))
                .thenReturn(new AuthResonse("User loggedIn successfully", token));

        URI uri = new URI("/auth/login");

        MockHttpServletResponse resp = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new LoginRequest("v1@mail.com", "v@123")))
        ).andReturn().getResponse();

        assertEquals(200, resp.getStatus());
        String expected = objectMapper.writeValueAsString(new AuthResonse("User loggedIn successfully", token));
        assertEquals(expected, resp.getContentAsString());
    }

}
