package com.prajina.academy.service;

import java.util.List;

import com.prajina.academy.api.Module;

public interface ModuleService {
	
	 List<Module> findAll(Integer pageNum, Integer size, String sortBy);
	
	 Module save(Module module);
	
	 Module update(Long id, Module module);
	
	 void deleteById(Long id);
}
