package com.prajina.academy.controller;

import java.util.List;

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

	@Autowired
	private ModuleService moduleService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<?> listAllModules() {
		System.out.println("Rest GET");
		List<Module> modules = moduleService.findAll();

		if (modules.isEmpty()) {
			return new ResponseEntity<>("No Modules Found", HttpStatus.OK);
		}

		System.out.println("modules not empty :: " + modules);
		System.out.println("response entity :: " + new ResponseEntity<List<Module>>(modules, HttpStatus.OK));
		return new ResponseEntity<>(modules, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getModule(@PathVariable("id") Long id) {
		System.out.println("Fetching Module with id " + id);
		Module module = moduleService.findById(id);
		if (module == null) {
			System.out.println("Module with id " + id + " not found");
			return new ResponseEntity<Module>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Module>(module, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> createModule(@RequestBody Module module, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Module " + module.getName());

		if (moduleService.isModuleExist(module)) {
			System.out.println("A Module with name " + module.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		moduleService.create(module);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/module/{id}").buildAndExpand(module.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateModule(@PathVariable("id") Long id, @RequestBody Module module) {
		System.out.println("Updating Module " + id);

		Module currentModule = moduleService.findById(id);
		System.out.println("Module with id " + currentModule);
		if (currentModule == null) {
			System.out.println("Module with id " + id + " not found");
			//TODO Replace with 200s OK or 500
			return new ResponseEntity<Module>(HttpStatus.NO_CONTENT);
		}
		
		Module updatedModule = moduleService.update(module);
		System.out.println("Updating Module RESENT" + new ResponseEntity<Module>(updatedModule, HttpStatus.OK));

		return new ResponseEntity<Module>(updatedModule, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> partialUpdateModule(@PathVariable("id") Long id, @RequestBody Module module) {
		System.out.println("Updating Partial Module " + id + "\n module :: " + module);

		Module currentModule = moduleService.findById(id);
		System.out.println("Module with id " + currentModule);
		if (currentModule == null) {
			System.out.println("Module with id " + id + " not found");
			//TODO Replace with 200s OK or 500
			return new ResponseEntity<Module>(HttpStatus.NO_CONTENT);
		}
		
		Module updatedModule = moduleService.update(module);
		System.out.println("updatedModule " + updatedModule);
		return new ResponseEntity<Module>(updatedModule, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteModuleById(@PathVariable("id") Long id) {
		System.out.println("Fetching & Deleting Module with id " + id);

		Module module = moduleService.findById(id);
		if (module == null) {
			System.out.println("Unable to delete. Module with id " + id + " not found");
			return new ResponseEntity<Module>(HttpStatus.NOT_FOUND);
		}

		moduleService.deleteById(id);
		return new ResponseEntity<Module>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllModules() {
		System.out.println("Deleting All Modules");

		moduleService.deleteAll();
		List<Module> modules = moduleService.findAll();
		if (modules == null || modules.isEmpty()) {
			return new ResponseEntity<Module>(HttpStatus.NO_CONTENT);
		}

		// TODO Throw custom exception
		return new ResponseEntity<Module>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}