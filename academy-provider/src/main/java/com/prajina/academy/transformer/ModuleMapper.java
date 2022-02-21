package com.prajina.academy.transformer;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.prajina.academy.model.Module;

@Component
public class ModuleMapper {
	
	private ModelMapper mapper = new ModelMapper();
	
	public Module toModel(com.prajina.academy.entity.ModuleEntity entity) {
		return mapper.map(entity, Module.class);
	}
	
	public List<Module> toModel(List<com.prajina.academy.entity.ModuleEntity> entityList) {
		
		if (entityList == null) {
			return null;
		}
		return entityList.stream()
				.map(item -> toModel(item))
				.collect(Collectors.toList());
	}
	
	public com.prajina.academy.entity.ModuleEntity toEntity(Module model) {
		return mapper.map(model, com.prajina.academy.entity.ModuleEntity.class);
	}

	public Set<com.prajina.academy.entity.ModuleEntity> toEntity(Set<Module> moduleSet) {
		if (moduleSet == null) {
			return null;
		}
		
		return moduleSet.stream()
				.map(item -> toEntity(item))
				.collect(Collectors.toSet());
	}
}