package com.prajina.academy.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prajina.academy.model.ModuleView;

public interface ModuleService {

	ResponseEntity<ModuleView[]> findAll(Integer pageNum, Integer pageSize, String sortBy);
	
	ResponseEntity<ModuleView> findById(Long id);

	ResponseEntity<ModuleView> findByName(String name);

	ResponseEntity<ModuleView> findByDesc(String description);

	ResponseEntity<ModuleView> save(ModuleView module);

	void save(List<ModuleView> module);
	
	ResponseEntity<ModuleView> update(Long id, ModuleView module);
	
	ResponseEntity<ModuleView> patch(Long id, ModuleView module);
	
	void deleteById(Long id);

	void deleteModule(List<ModuleView> moduleList);
	
	ResponseEntity<Void> deleteWithExchange(Long id);

	boolean isModuleExist(ModuleView module);

	

}
