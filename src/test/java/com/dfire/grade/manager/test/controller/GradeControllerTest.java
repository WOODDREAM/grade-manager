package com.dfire.grade.manager.test.controller;

import com.dfire.grade.manager.test.BaseControllerTestHelper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * User:huangtao
 * Date:2016-04-17
 * description：
 */
public class GradeControllerTest extends BaseControllerTestHelper {
    @BeforeMethod
    public void init() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(gradeController);
        standaloneMockMvcBuilder.addInterceptors(securityIntercept);
        mockMvc = standaloneMockMvcBuilder.build();
    }

    @Test
    public void testAddGrade() throws Exception {
        double grade = 99;
        mockMvc.perform(post("/grade/create").param("grade", String.valueOf(grade)).param("student_id", studentUid)
                .param("class_id", classId).param("job_id", "  ").param("type", String.valueOf(type1))
                .header("UID", teacherUid))
                .andExpect(jsonPath("$.code", is("1")));
    }

    @Test
    public void testSelectGradeById() throws Exception {
        mockMvc.perform(get("/grade/find").param("grade_id", gradeId)
                .header("UID", teacherUid))
                .andExpect(jsonPath("code").value("1"));
    }
}
