package com.prajina.academy.transformer;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.prajina.academy.model.CourseView;

@Component
public class CourseMapper {
	
	private ModelMapper mapper = new ModelMapper();
	
	public CourseView toModel(com.prajina.academy.entity.CourseImpl entity) {
		
		return mapper.map(entity, CourseView.class);
	}
	
	public List<CourseView> toModel(List<com.prajina.academy.entity.CourseImpl> entityList) {
		return entityList.stream()
				.map(item -> toModel(item))
				.collect(Collectors.toList());
	}
	
	public com.prajina.academy.entity.CourseImpl toEntity(CourseView model) {
		
		return mapper.map(model, com.prajina.academy.entity.CourseImpl.class);
	}
	
	public com.prajina.academy.entity.CourseImpl copyFromModelToEntity(CourseView model, com.prajina.academy.entity.CourseImpl entity ) {
		
		if ( entity == null) {
			throw new RuntimeException("modeltoEntity :: Empty entity");
		}
		
		if (!model.getName().isEmpty()) {
			entity.setName(model.getName());
		}
		if (!model.getDescription().isEmpty()) {
			entity.setDescription(model.getDescription());
		}
		if (!model.getContact().isEmpty()) {
			entity.setContact(model.getContact());
		}
		if (!model.getVersion().isEmpty()) {
			entity.setVersion(model.getVersion());
		}
		
		return entity;
	}
	
}