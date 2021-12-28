package com.prajina.academy.service;

import java.util.List;

import com.prajina.academy.model.Module;

public interface ModuleService {

	Module findById(Integer id);

	Module findByName(String name);

	List<Module> findAllModules();

	Module createModule(Module Module);

	Module createModule(List<Module> Module);

	Module updateModule(Module Module);

	Module updateModule(List<Module> Module);

	void deleteModule(List<Module> ModuleList);

	boolean isModuleExist(Module Module);

}
