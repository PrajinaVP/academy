package com.prajina.academy.service;

import static org.mockito.Mockito.mock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.prajina.academy.repository.CourseRepository;
import com.prajina.academy.repository.ModuleRepository;
import com.prajina.academy.transformer.CourseMapper;
import com.prajina.academy.transformer.ModuleMapper;

@Profile("test")
@Configuration
public class AcademyMocks {
	
	@Bean
	@Primary
	public CourseRepository courseRepository() {
		return mock(CourseRepository.class);
	}
	
	@Bean
	@Primary
	public ModuleRepository moduleRepository() {
		return mock(ModuleRepository.class);
	}
	
//	@Bean
//	@Primary
//	public CourseMapper courseMapper() {
//		return mock(CourseMapper.class);
//	}
//	

}
