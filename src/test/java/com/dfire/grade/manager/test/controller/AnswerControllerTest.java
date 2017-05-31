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
public class AnswerControllerTest extends BaseControllerTestHelper {
    @BeforeMethod
    public void init() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(answerController);
        standaloneMockMvcBuilder.addInterceptors(securityIntercept);
        this.mockMvc = standaloneMockMvcBuilder.build();
    }

    @Test
    public void testCreateAnswer() throws Exception {
        String jsonStr = "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈";
        mockMvc.perform(post("/answer/create").param("job_id", jobId)
                .content(jsonStr)
                .header("UID", studentUid)
                .contentType("application/json")
                .characterEncoding("UTF-8")
                .accept("application/json; charset=UTF-8"))
                .andExpect(jsonPath("$.code", is("1")));
    }

    @Test
    public void testFindAnswer() throws Exception {
        mockMvc.perform(post("/answer/find").param("answer_id", answerId)
                .header("UID", studentUid))
                .andExpect(jsonPath("$.code", is("1")));
    }
}
