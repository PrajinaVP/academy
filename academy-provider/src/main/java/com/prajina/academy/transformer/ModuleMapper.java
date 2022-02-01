package com.prajina.academy.transformer;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prajina.academy.model.ModuleView;

@Component
public class ModuleMapper {
	
	private ModelMapper mapper = new ModelMapper();
	
	public ModuleView toModel(com.prajina.academy.entity.ModuleImpl entity) {
		return mapper.map(entity, ModuleView.class);
	}
	
	public List<ModuleView> toModel(List<com.prajina.academy.entity.ModuleImpl> entityList) {
		return entityList.stream()
				.map(item -> toModel(item))
				.collect(Collectors.toList());
	}
	
	public com.prajina.academy.entity.ModuleImpl toEntity(ModuleView model) {
		return mapper.map(model, com.prajina.academy.entity.ModuleImpl.class);
	}

	public Set<com.prajina.academy.entity.ModuleImpl> toEntity(Set<ModuleView> moduleSet) {
		return moduleSet.stream()
				.map(item -> toEntity(item))
				.collect(Collectors.toSet());
	}
}