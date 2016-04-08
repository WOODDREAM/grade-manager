package com.dfire.grade.manager.test.controller;

import com.dfire.grade.manager.test.BaseControllerTestHelper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * User:huangtao
 * Date:2016-04-08
 * description：
 */
public class UserControllerTest extends BaseControllerTestHelper {
    @BeforeMethod
    public void init() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(userController);
        this.mockMvc = standaloneMockMvcBuilder.build();
    }

    @Test
    public void testInsertUser() throws Exception {
        mockMvc.perform(post("/user/sign_up").param("name", name).param("email", email)
                .param("school", school).param("mobile", mobile).param("pass_word", passWord)
                .param("type", String.valueOf(type1)).param("sex", String.valueOf(sex))
                .characterEncoding("utf-8"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.code", is("1")));
    }

    @Test
    public void testSignIn() throws Exception {
        mockMvc.perform(post("/user/sign_in").param("mobile", mobile).param("pass_word", passWord)
                .param("type", String.valueOf(type1)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.code", is("1")))
                .andExpect(jsonPath("code").value("1"));
    }


}
