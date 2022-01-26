package com.prajina.academy.dao;

import java.util.List;

import com.prajina.academy.entity.Module;

public interface ModuleDAO {
	
	public List<Module> findAll();
	
	public Module findById(Long id);
	
	public Module findByName(String name);
	
	public List<Module> findByDesc(String desc);
	
	public Module getModule(Module course);
	
	public void save(Module course);
	
	public void delete(Long courseId);

}
