package com.prajina.academy.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prajina.academy.entity.ModuleEntity;

public interface ModuleRepository extends JpaRepository<ModuleEntity, Long> {
	
	List<ModuleEntity> findByName(String name, Pageable pageable);
	List<ModuleEntity> findByNameContaining(String name, Pageable pageable);
	
}
