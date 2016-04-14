package com.dfire.grade.manager.test.controller;

import com.dfire.grade.manager.test.BaseControllerTestHelper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


/**
 * User:huangtao
 * Date:2016-04-14
 * description：
 */
public class StudentClassControllerTest extends BaseControllerTestHelper {
    @BeforeMethod
    public void init() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(studentClassController);
        standaloneMockMvcBuilder.addInterceptors(securityIntercept);
        this.mockMvc = standaloneMockMvcBuilder.build();
    }

    @Test
    public void testJoinClass() throws Exception {
        mockMvc.perform(post("/stu_cla/join").param("class_id", classId)
                .header("UID", studentUid))
                .andExpect(jsonPath("code").value("1"));
    }
}
