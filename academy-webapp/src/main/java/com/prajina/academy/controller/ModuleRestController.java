package com.prajina.academy.controller;

import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<?> listAllModules(
			@RequestParam(required = false, defaultValue="0") Integer pageNum,
			@RequestParam(required = false, defaultValue="100") Integer pageSize,
			@RequestParam(required = false, defaultValue = "name") String sortBy) {
		logger.debug("Fetching all modules... pageNum :: " + pageNum +", pageSize :: " + pageSize + ", sortBy :: " + sortBy);
		
		ResponseEntity<Module[]> responseEntity = moduleService.findAll(pageNum, pageSize, sortBy);
		return responseEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getModule(@PathVariable("id") Long id) {
		logger.debug("Fetching Module with id " + id);
		ResponseEntity<Module> response = moduleService.findById(id);
		if (response == null || response.getBody() == null) {
			logger.debug("Module with id " + id + " not found");
			return new ResponseEntity<>("No Module Found with id " + id, HttpStatus.NO_CONTENT);
		}
		
		return response;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> createModule(@RequestBody Module module, UriComponentsBuilder ucBuilder) {
		logger.debug("Creating Module " + module.getName());

		return moduleService.save(module);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateModule(@PathVariable("id") Long id, @RequestBody Module module) {
		logger.debug("Updating Module " + id);
		
		return moduleService.update(id, module);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> partialUpdateModule(@PathVariable("id") Long id, @RequestBody Module module) {
		logger.debug("Updating Partial Module " + id + "\n module :: " + module);
		return moduleService.patch(id, module);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteModuleById(@PathVariable("id") Long id) {
		logger.debug("Deleting Module with id " + id);

		return moduleService.deleteWithExchange(id);
	}

}