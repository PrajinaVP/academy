package com.prajina.academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;
import com.prajina.academy.api.Module;
import com.prajina.academy.entity.ModuleImpl;
import com.prajina.academy.repository.ModuleRepository;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService{
	
	@Autowired
	private ModuleRepository repository;
	
	@Override
	public List<Module> findAll(Integer pageNum, Integer size, String sortBy) {
		Pageable page = PageRequest.of(pageNum, size, Sort.by(sortBy));
		Page<ModuleImpl> pagedModule = repository.findAll(page);
		
		return ImmutableList.copyOf(pagedModule.getContent());
	}
	
	@Override
	public Module save(Module module) {
		if (module == null) {
			throw new RuntimeException("No module provided to save!");
		}
		
		return repository.save(ModuleImpl.convert(module));
	}
	
	@Override
	public Module update(Long id, Module module) {
		if (id == null) {
			throw new RuntimeException("No module id provided to update!");
		}
		if (module == null) {
			throw new RuntimeException("No module provided to update!");
		}
		
		Optional<ModuleImpl> moduleFromDB = Optional.ofNullable(repository.findById(id))
				.orElseThrow(() -> new RuntimeException("Module with id " + id + " not found!" ));
		
		ModuleImpl moduleToUpdate = moduleFromDB.get();
		moduleToUpdate.setName(module.getName());
		moduleToUpdate.setDescription(module.getDescription());
		moduleToUpdate.setContact(module.getContact());
		moduleToUpdate.setVersion(module.getVersion());
		moduleToUpdate.setStatus(module.getStatus());
		
		return repository.save(moduleToUpdate);
	}
	
	@Override
	public void deleteById(Long id) {
		if (id == null) {
			throw new RuntimeException("No module id provided to delete!");
		}
		repository.deleteById(id);
	}
}
