package com.prajina.academy.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prajina.academy.dao.ModuleDAO;
import com.prajina.academy.model.Course;
import com.prajina.academy.model.Module;
import com.prajina.academy.transformer.ModuleMapper;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleDAO dao;
	
	@Autowired
	private ModuleMapper mapper;

	@Override
	@Transactional
	public List<Module> findAll() {
		return mapper.toModel(dao.findAll());
	}

	@Override
	@Transactional
	public Module findById(Long id) {
		return mapper.toModel(dao.findById(id));
	}

	@Override
	@Transactional
	public Module findByName(String name) {
		return mapper.toModel(dao.findByName(name));
	}

	@Override
	@Transactional
	public List<Module> findByDesc(String desc) {
		return mapper.toModel(dao.findByDesc(desc));
	}

	@Override
	@Transactional
	public void save(Module module) {
		if (module == null) {
			throw new RuntimeException("No module provided!");
		}
		dao.save(mapper.toEntity(module));
	}

	@Override
	@Transactional
	public void save(List<Module> moduleList) {
		if (moduleList == null) {
			throw new RuntimeException("No module provided!");
		}

		for (Module module : moduleList) {
			save(module);
		}
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		dao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteModule(List<Module> moduleList) {
		if (moduleList == null) {
			throw new RuntimeException("No course provided!");
		}

		for (Module module : moduleList) {
			deleteById(module.getId());
		}
	}

	@Override
	@Transactional
	public boolean isModuleExist(Module module) {

		return module != null && module.getId() != null && findById(module.getId()) != null;
	}

}
