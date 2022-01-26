package com.prajina.academy.service;

import java.util.List;

import com.prajina.academy.model.Module;

public interface ModuleService {

	List<Module> findAll();

	Module findById(Long id);

	Module findByName(String name);

	List<Module> findByDesc(String desc);

	void save(Module module);

	void save(List<Module> module);
	
	void deleteById(Long id);

	void deleteModule(List<Module> moduleList);

	boolean isModuleExist(Module module);

}
