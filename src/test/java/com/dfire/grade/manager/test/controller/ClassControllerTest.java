package com.dfire.grade.manager.test.controller;

import com.alibaba.fastjson.JSON;
import com.dfire.grade.manager.test.BaseControllerTestHelper;
import com.dfire.grade.manager.vo.form.ClassForm;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * User:huangtao
 * Date:2016-04-12
 * description：
 */
public class ClassControllerTest extends BaseControllerTestHelper {
    @BeforeMethod
    public void init() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(classController);
        standaloneMockMvcBuilder.addInterceptors(securityIntercept);
        this.mockMvc = standaloneMockMvcBuilder.build();
    }

    @Test
    public void insertClass() throws Exception {
//        List<ClassForm> classFormList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
            ClassForm classForm = new ClassForm();
            classForm.setCredit(1);
            classForm.setName("科学");
            classForm.setPeriod(3);
            classForm.setFrequencyUnit("as");
            Map<String, Integer> schoolTimer = new HashMap<>();
            schoolTimer.put("1", 1);
            schoolTimer.put("2", 2);
            schoolTimer.put("3", 3);
//            classForm.setSchoolTimes(schoolTimer);
//            classFormList.add(classForm);
//        }
        String jsonStr = JSON.toJSONString(classForm);
        System.out.print(jsonStr);
//        mockMvc.perform(post("/class/create")
////                .header("class_list", jsonStr)
//                .header("UID", teacherUid)
//                .content(jsonStr)
//                .contentType("application/json")
//                .accept("application/json; charset=UTF-8")
//                .characterEncoding("UTF-8"))
//                .andExpect(jsonPath("code").value("1"));
    }

    @Test
    public void testGetDetail() throws Exception {
        mockMvc.perform(post("/class/detail").param("class_id", classId)
                .header("UID", studentUid)
                .contentType("application/json")
                .accept("application/json; charset=UTF-8")
                .characterEncoding("UTF-8"))
                .andExpect(jsonPath("code").value("1"));
    }

    @Test
    public void testGetTeacherClass() throws Exception {
        mockMvc.perform(post("/class/teacher").param("index", "0").param("page_size", "6")
                .header("UID", teacherUid)
                .contentType("application/json")
                .accept("application/json; charset=UTF-8")
                .characterEncoding("UTF-8"))
                .andExpect(jsonPath("code").value("1"));
    }

    @Test
    public void testGetStudentClass() throws Exception {
        mockMvc.perform(post("/class/student").param("index", "0").param("page_size", "6")
                .header("UID", studentUid)
                .contentType("application/json")
                .accept("application/json; charset=UTF-8")
                .characterEncoding("UTF-8"))
                .andExpect(jsonPath("code").value("1"));
    }
}
