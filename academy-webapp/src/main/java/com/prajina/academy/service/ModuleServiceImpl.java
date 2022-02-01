package com.prajina.academy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.prajina.academy.model.ModuleView;

@Service
public class ModuleServiceImpl implements ModuleService {

	Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);
	private final String RESOURCE_URI = "/module";
	
	@Value("${academy.service.url}")
	private String serviceBaseUrl; 
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<ModuleView[]> findAll(Integer pageNum, Integer pageSize, String sortBy) {
		String requestUri = getRequestUri(pageNum, pageSize, sortBy);
		
		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("pageNum", Integer.toString(pageNum));
		urlParameters.put("pageSize", Integer.toString(pageSize));
		urlParameters.put("sortBy", sortBy);
		  
		ResponseEntity<ModuleView[]> responseEntity = restTemplate.getForEntity(requestUri,
		                                                                ModuleView[].class,
		                                                                urlParameters);
	
		return responseEntity;
	}

	private String getRequestUri(Integer pageNum, Integer pageSize, String sortBy) {
		StringBuilder urlBuilder = new StringBuilder(serviceBaseUrl + RESOURCE_URI);
		boolean isFirstParam = true;
		if (pageNum != null) {
			if (isFirstParam) {
				urlBuilder.append("?");
			}
			urlBuilder.append("pageNum=" + pageNum);
			isFirstParam=false;
		}
		if (pageSize != null) {
			if (isFirstParam) {
				urlBuilder.append("?");
			} else {
				urlBuilder.append("&");
			}
			urlBuilder.append("pageSize=" + pageSize);
			isFirstParam=false;
		}
		if (sortBy != null) {
			if (isFirstParam) {
				urlBuilder.append("?");
			} else {
				urlBuilder.append("&");
			}
			urlBuilder.append("sortBy=" + sortBy);
			isFirstParam=false;
		}
		String requestUri = urlBuilder.toString();
		return requestUri;
	}

	@Override
	public ResponseEntity<ModuleView> findById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
        ResponseEntity<ModuleView> responseEntity = restTemplate.getForEntity(requestUri+"/{id}", ModuleView.class, Long.toString(id));
        
        return responseEntity;
	}

	@Override
	public ResponseEntity<ModuleView> findByName(String name) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
        ResponseEntity<ModuleView> responseEntity = restTemplate.getForEntity(requestUri+"?name={name}", ModuleView.class, name);
        
        return responseEntity;
	}

	@Override
	public ResponseEntity<ModuleView> findByDesc(String description) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
        ResponseEntity<ModuleView> responseEntity = restTemplate.getForEntity(requestUri+"?description={description}", ModuleView.class, description);
        
        return responseEntity;
	}

	@Override
	public ResponseEntity<ModuleView> save(ModuleView module) {
		if (module == null) {
			throw new RuntimeException("No module provided!");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		
		return restTemplate.postForEntity(requestUri, module, ModuleView.class);
	}

	// TODO Use saveAll
	@Override
	public void save(List<ModuleView> moduleList) {
		if (moduleList == null) {
			throw new RuntimeException("No module provided for save!");
		}

		for (ModuleView module : moduleList) {
			save(module);
		}
	}
	
	@Override
	public ResponseEntity<ModuleView> update(Long id, ModuleView module) {
		if (module == null || module.getId() == null) {
			throw new RuntimeException("No module provided for update!");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		return restTemplate.exchange(requestUri + "/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(module),
                ModuleView.class,
                Long.toString(id));
	}
	
	@Override
	public ResponseEntity<ModuleView> patch(Long id, ModuleView module) {
		if (module == null || module.getId() == null) {
			throw new RuntimeException("No module provided for update!");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		return restTemplate.exchange(requestUri + "/{id}",
                HttpMethod.PATCH,
                new HttpEntity<>(module),
                ModuleView.class,
                Long.toString(id));
	}

	@Override
	public void deleteById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		restTemplate.delete(requestUri + "/{id}", Long.toString(id));
	}

	@Override
	public void deleteModule(List<ModuleView> moduleList) {
		if (moduleList == null) {
			throw new RuntimeException("No course provided!");
		}

		for (ModuleView module : moduleList) {
			deleteById(module.getId());
		}
	}

	@Override
	public ResponseEntity<Void> deleteWithExchange(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		return restTemplate.exchange(requestUri + "/{id}",
	                               HttpMethod.DELETE,
	                               null,
	                               Void.class,
	                               Long.toString(id));
	}
	// TODO Remove as not used?
	@Override
	public boolean isModuleExist(ModuleView module) {

		return module != null && module.getId() != null && findById(module.getId()) != null;
	}

}
