package com.prajina.academy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.prajina.academy.model.ModuleView;
import com.prajina.academy.service.ModuleService;

@RestController
@RequestMapping("/module")
public class ModuleController {
	
	Logger logger = LoggerFactory.getLogger(ModuleController.class);

	@Autowired
	private ModuleService moduleService;
	
	@GetMapping("/view")
	public ModelAndView getModules(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(required = false, defaultValue = "30") Integer pageSize,
			@RequestParam(required = false, defaultValue = "name") String sortBy) {
		logger.debug("Fetching all modules... pageNum :: " + pageNum + ", pageSize :: " + pageSize + ", sortBy :: "
				+ sortBy);
		ResponseEntity<ModuleView[]> responseEntity = moduleService.findAll(pageNum, pageSize, sortBy);
		ModelAndView mav = new ModelAndView("module");
		mav.addObject("moduleList", responseEntity.getBody());

		return mav;
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<?> listAllModules(
			@RequestParam(required = false, defaultValue="0") Integer pageNum,
			@RequestParam(required = false, defaultValue="100") Integer pageSize,
			@RequestParam(required = false, defaultValue = "name") String sortBy) {
		logger.debug("Fetching all modules... pageNum :: " + pageNum +", pageSize :: " + pageSize + ", sortBy :: " + sortBy);
		
		ResponseEntity<ModuleView[]> responseEntity = moduleService.findAll(pageNum, pageSize, sortBy);
		return responseEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getModule(@PathVariable("id") Long id) {
		logger.debug("Fetching Module with id " + id);
		ResponseEntity<ModuleView> response = moduleService.findById(id);
		if (response == null || response.getBody() == null) {
			logger.debug("Module with id " + id + " not found");
			return new ResponseEntity<>("No Module Found with id " + id, HttpStatus.NO_CONTENT);
		}
		
		return response;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> createModule(@RequestBody ModuleView module, UriComponentsBuilder ucBuilder) {
		logger.debug("Creating Module " + module.getName());

		return moduleService.save(module);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateModule(@PathVariable("id") Long id, @RequestBody ModuleView module) {
		logger.debug("Updating Module " + id);
		
		return moduleService.update(id, module);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> partialUpdateModule(@PathVariable("id") Long id, @RequestBody ModuleView module) {
		logger.debug("Updating Partial Module " + id + "\n module :: " + module);
		return moduleService.patch(id, module);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteModuleById(@PathVariable("id") Long id) {
		logger.debug("Deleting Module with id " + id);

		return moduleService.deleteWithExchange(id);
	}

}