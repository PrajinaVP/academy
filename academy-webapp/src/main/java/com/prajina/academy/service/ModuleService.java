package com.prajina.academy.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prajina.academy.model.Module;

public interface ModuleService {

	ResponseEntity<Module[]> findAll(Integer pageNum, Integer pageSize, String sortBy);
	
	ResponseEntity<Module> findById(Long id);

	ResponseEntity<Module> findByName(String name);

	ResponseEntity<Module> findByDesc(String description);

	ResponseEntity<Module> save(Module module);

	void save(List<Module> module);
	
	ResponseEntity<Module> update(Long id, Module module);
	
	ResponseEntity<Module> patch(Long id, Module module);
	
	void deleteById(Long id);

	void deleteModule(List<Module> moduleList);
	
	ResponseEntity<Void> deleteWithExchange(Long id);

	boolean isModuleExist(Module module);

	

}
