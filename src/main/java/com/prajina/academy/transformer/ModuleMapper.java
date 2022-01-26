package com.prajina.academy.transformer;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.prajina.academy.model.Module;

@Component
public class ModuleMapper {
	
	private ModelMapper mapper = new ModelMapper();
	
	public Module toModel(com.prajina.academy.entity.Module entity) {
		return mapper.map(entity, Module.class);
	}
	
	public List<Module> toModel(List<com.prajina.academy.entity.Module> entityList) {
		return entityList.stream()
				.map(item -> toModel(item))
				.collect(Collectors.toList());
	}
	
	public com.prajina.academy.entity.Module toEntity(Module model) {
		return mapper.map(model, com.prajina.academy.entity.Module.class);
	}

}
