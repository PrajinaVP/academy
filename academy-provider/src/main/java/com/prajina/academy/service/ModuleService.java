package com.prajina.academy.service;

import java.util.List;

import com.prajina.academy.model.Module;

public interface ModuleService {
	
	public List<Module> findAll(Integer pageNum, Integer size, String sortBy);
	
	public Module create(Module module);
	
	public Module update(Long id, Module module);
	
	public void deleteById(Long id);
}
