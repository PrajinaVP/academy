package com.prajina.academy.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import com.prajina.academy.data.TestData;
import com.prajina.academy.service.AcademyMocks;
import com.prajina.academy.service.CourseService;

@ActiveProfiles("test")
//@WebMvcTest(controllers = CourseController.class)
@SpringBootTest(classes = { CourseController.class, CourseService.class, AcademyMocks.class })
@AutoConfigureMockMvc()
//@WebAppConfiguration
//@Import(SecurityConfig.class)
public class CourseControllerTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CourseService mockedCourseService;
	
	private TestData testData = new TestData();
	
	@Test
	public void test_findAll_validInput_shouldReturnStatusOK() throws Exception {
		when(mockedCourseService.findAll(any(Integer.class), any(Integer.class), any(String.class)))
		.thenReturn(testData.mockCourseList());
		
		mockMvc.perform(get("/course"))
				//.apply(springSecurity())	
				.andDo(print())
				.andExpect(status().isOk());
		
		
//		.perform(get("/course"))
//			.andDo(findAll(any(Integer.class), any(Integer.class), any(String.class)))
//			.andExpect(status().isOk());

		
	}

}

