package com.prajina.academy.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.prajina.academy")
/*
 * WebMvcConfigurerAdapter @deprecated as of 5.0 {@link WebMvcConfigurer} has
 * default methods (made possible by a Java 8 baseline) and can be implemented
 * directly without the need for this adapter
 */
public class AcademyConfiguration implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		System.out.println("Rest addResourceHandlers");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("Rest addResourceHandlers");
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
}
