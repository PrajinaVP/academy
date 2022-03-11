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

import com.prajina.academy.model.Module;
import com.prajina.academy.repository.ModuleRepository;
import com.prajina.academy.transformer.ModuleMapper;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService{
	
	@Autowired
	private ModuleRepository repository;
	
	@Autowired
	private ModuleMapper mapper;
	
	@Override
	public List<Module> findAll(Integer pageNum, Integer size, String sortBy) {
		if (pageNum == null || pageNum < 0 || size == null ||size < 1 || sortBy == null) {
			throw new RuntimeException("Invalid input!");
		}
		Pageable page = PageRequest.of(pageNum, size, Sort.by(sortBy));
		Page<com.prajina.academy.entity.ModuleEntity> pagedModule = repository.findAll(page);
		
		return mapper.toModel(pagedModule.getContent());
	}
	
	@Override
	public Module create(Module module) {
		if (module == null) {
			throw new RuntimeException("No module provided to save!");
		}
		
		return mapper.toModel(repository.save(mapper.toEntity(module)));
	}
	
	@Override
	public Module update(Long id, Module module) {
		if (id == null) {
			throw new RuntimeException("No module id provided to update!");
		}
		if (module == null) {
			throw new RuntimeException("No module provided to update!");
		}
		
		Optional<com.prajina.academy.entity.ModuleEntity> moduleFromDB = Optional.ofNullable(repository.findById(id))
				.orElseThrow(() -> new RuntimeException("Module with id " + id + " not found!" ));
		
		com.prajina.academy.entity.ModuleEntity moduleToUpdate = moduleFromDB.get();
		moduleToUpdate.setName(module.getName());
		moduleToUpdate.setDescription(module.getDescription());
		moduleToUpdate.setContact(module.getContact());
		moduleToUpdate.setVersion(module.getVersion());
		moduleToUpdate.setStatus(module.getStatus());
		
		return mapper.toModel(repository.save(moduleToUpdate));
	}
	
	@Override
	public void deleteById(Long id) {
		if (id == null) {
			throw new RuntimeException("No module id provided to delete!");
		}
		repository.deleteById(id);
	}
}
