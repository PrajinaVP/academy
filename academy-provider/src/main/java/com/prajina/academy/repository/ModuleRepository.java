package com.prajina.academy.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prajina.academy.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {
	
	List<Module> findByName(String name, Pageable pageable);
	List<Module> findByNameContaining(String name, Pageable pageable);
	
}
