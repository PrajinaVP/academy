package com.prajina.academy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.prajina.academy.model.Module;
import com.prajina.academy.service.ModuleService;

@RestController
@RequestMapping("/module")
public class ModuleRestController {
	
	Logger logger = LoggerFactory.getLogger(ModuleRestController.class);

	@Autowired
	private ModuleService moduleService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<?> listAllModules() {
		logger.debug("Listing all modules...");
		List<Module> modules = moduleService.findAll();

		if (modules.isEmpty()) {
			return new ResponseEntity<>("No Modules Found", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(modules, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getModule(@PathVariable("id") Long id) {
		logger.debug("Fetching Module with id " + id);
		Module module = moduleService.findById(id);
		if (module == null) {
			logger.debug("Module with id " + id + " not found");
			return new ResponseEntity<>("No Module Found with id " + id, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Module>(module, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> createModule(@RequestBody Module module, UriComponentsBuilder ucBuilder) {
		logger.debug("Creating Module " + module.getName());

		if (module.getId() != null && moduleService.isModuleExist(module)) {
			logger.debug("A Module with name " + module.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		moduleService.save(module);

//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/module/{id}").buildAndExpand(module.getId()).toUri());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateModule(@PathVariable("id") Long id, @RequestBody Module module) {
		logger.debug("Updating Module " + id);

		Module currentModule = moduleService.findById(id);
		logger.debug("Module with id " + currentModule);
		if (currentModule == null) {
			logger.debug("Module with id " + id + " not found");
			return new ResponseEntity<>("No Module Found with id " + id, HttpStatus.NO_CONTENT);
		}
		
		moduleService.save(module);
		Module updatedModule = moduleService.findById(id);

		return new ResponseEntity<Module>(updatedModule, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> partialUpdateModule(@PathVariable("id") Long id, @RequestBody Module module) {
		logger.debug("Updating Partial Module " + id + "\n module :: " + module);

		Module currentModule = moduleService.findById(id);
		logger.debug("Module with id " + currentModule);
		if (currentModule == null) {
			logger.debug("Module with id " + id + " not found");
			return new ResponseEntity<>("No Module Found with id " + id, HttpStatus.NO_CONTENT);
		}
		
		moduleService.save(module);
		Module updatedModule = moduleService.findById(id);
	
		return new ResponseEntity<Module>(updatedModule, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteModuleById(@PathVariable("id") Long id) {
		logger.debug("Fetching & Deleting Module with id " + id);

		Module module = moduleService.findById(id);
		if (module == null) {
			logger.debug("Unable to delete. Module with id " + id + " not found");
			return new ResponseEntity<>("No Module Found with id " + id, HttpStatus.NO_CONTENT);
		}

		moduleService.deleteById(id);
		return new ResponseEntity<Module>(HttpStatus.NO_CONTENT);
	}

}