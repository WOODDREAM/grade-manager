package com.dfire.grade.manager.test;


import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * User:huangtao
 * Date:2016-02-27
 * description：
 */
public class HelloTest extends BaseControllerTestHelper {
    @BeforeMethod
    public void init() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(hello);
        this.mockMvc = standaloneMockMvcBuilder.build();
    }

    @Test
    public void insertStudent() throws Exception {
        mockMvc.perform(get("/student")).andExpect(status().is2xxSuccessful());
    }


    @Test
    public void testHello() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testSendSms() throws Exception {
        mockMvc.perform(post("/sms").param("mobile", "15757115785"));
    }
}
