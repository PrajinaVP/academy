package com.prajina.academy.service;

import java.util.List;

import com.prajina.academy.model.Module;

public interface ModuleService {

	List<Module> findAll();

	Module findById(Long id);

	Module findByName(String name);

	List<Module> findByDesc(String desc);

	Module create(Module module);

	List<Module> create(List<Module> module);

	Module update(Module module);

	void updateModule(List<Module> module);

	void deleteModule(List<Module> moduleList);

	void deleteAll();

	void deleteById(Long id);

	boolean isModuleExist(Module module);

}
