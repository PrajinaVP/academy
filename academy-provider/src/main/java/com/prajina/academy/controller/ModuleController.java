package com.prajina.academy.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prajina.academy.model.Module;
import com.prajina.academy.service.ModuleService;

@RestController
@RequestMapping("/module")
public class ModuleController {
	
	@Autowired
	private ModuleService service;
	
	@GetMapping
	ResponseEntity<?> findAll(@RequestParam(value="pageNum", defaultValue="0") Integer pageNum,
			@RequestParam(value="pageSize", defaultValue="100") Integer pageSize,
			@RequestParam(value="sortBy", defaultValue="name") String sortBy) {
		
		return ResponseEntity.ok(service.findAll(pageNum, pageSize, sortBy));
	}
	
	@PostMapping
	public ResponseEntity<?> addModule(@RequestBody Module module) {
		Module savedModule = service.save(module);
		
		return new ResponseEntity<>(savedModule, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateModule(@Valid @PathVariable(value="id", required=true) Long id,
			@RequestBody Module module) {
		Module updatedModule = service.update(id, module);
		
		return new ResponseEntity<>(updatedModule, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@Valid @PathVariable(value="id", required=true) Long id) {
		service.deleteById(id);
	}

}
