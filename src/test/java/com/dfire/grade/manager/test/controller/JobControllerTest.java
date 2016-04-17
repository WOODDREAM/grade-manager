package com.dfire.grade.manager.test.controller;

import com.dfire.grade.manager.test.BaseControllerTestHelper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
public class JobControllerTest extends BaseControllerTestHelper {
    @BeforeMethod
    public void init() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(jobController);
        standaloneMockMvcBuilder.addInterceptors(securityIntercept);
        this.mockMvc = standaloneMockMvcBuilder.build();
    }

    @Test
    public void testCreateJob() throws Exception {
        mockMvc.perform(post("/job/create").param("name", "测试").param("class_id", classId)//.param("job_id", jobId)
                .param("is_need_answer", String.valueOf(true))
                .param("type", String.valueOf(type1)).param("detail", "就是测试一下")
                .header("UID", teacherUid))
                .andExpect(jsonPath("$.code", is("1")));
    }

    @Test
    public void testFindJob() throws Exception {
        mockMvc.perform(post("/job/find").param("job_id", jobId).param("class_id", classId).param("teacher_id", teacherUid)
                .header("UID", studentUid))
                .andExpect(jsonPath("$.code", is("1")));
    }

}
