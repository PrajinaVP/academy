package com.prajina.academy.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prajina.academy.entity.ModuleImpl;

public interface ModuleRepository extends JpaRepository<ModuleImpl, Long> {
	
	List<ModuleImpl> findByName(String name, Pageable pageable);
	List<ModuleImpl> findByNameContaining(String name, Pageable pageable);
	
}
