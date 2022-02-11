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

import com.prajina.academy.model.CourseView;

@Service
public class CourseServiceImpl implements CourseService {

	Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	private final String RESOURCE_URI = "/course";
	
	@Value("${academy.service.url}")
	private String serviceBaseUrl; 
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<CourseView[]> findAll(Integer pageNum, Integer pageSize, String sortBy) {
		String requestUri = getRequestUri(pageNum, pageSize, sortBy);
		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("pageNum", Integer.toString(pageNum));
		urlParameters.put("pageSize", Integer.toString(pageSize));
		urlParameters.put("sortBy", sortBy);
		  
		ResponseEntity<CourseView[]> responseEntity = restTemplate.getForEntity(requestUri,
		                                                                CourseView[].class,
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
	public ResponseEntity<CourseView> findById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
        ResponseEntity<CourseView> responseEntity = restTemplate.getForEntity(requestUri+"/{id}", CourseView.class, Long.toString(id));
        
        return responseEntity;
	}

	@Override
	public ResponseEntity<CourseView> findByName(String name) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
        ResponseEntity<CourseView> responseEntity = restTemplate.getForEntity(requestUri+"?name={name}", CourseView.class, name);
        
        return responseEntity;
	}

	@Override
	public ResponseEntity<CourseView> findByDesc(String description) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
        ResponseEntity<CourseView> responseEntity = restTemplate.getForEntity(requestUri+"?description={description}", CourseView.class, description);
        
        return responseEntity;
	}

	@Override
	public ResponseEntity<CourseView> save(CourseView course) {
		if (course == null) {
			throw new RuntimeException("No course provided!");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		
		return restTemplate.postForEntity(requestUri, course, CourseView.class);
	}

	// TODO Use saveAll
	@Override
	public void save(List<CourseView> courseList) {
		if (courseList == null) {
			throw new RuntimeException("No course provided for save!");
		}

		for (CourseView course : courseList) {
			save(course);
		}
	}
	
	@Override
	public ResponseEntity<CourseView> update(Long id, CourseView course) {
		logger.debug("Updating Course id :: " + id + "  course :: " +course.toString());
		if (course == null || course.getId() == null) {
			throw new RuntimeException("No course provided for update!");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		logger.debug("Updating Course requestUri " + requestUri);
		
		return restTemplate.exchange(requestUri + "/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(course),
                CourseView.class,
                Long.toString(id));
	}
	
	@Override
	public ResponseEntity<CourseView> patch(Long id, CourseView course) {
		if (course == null || course.getId() == null) {
			throw new RuntimeException("No course provided for update!");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		return restTemplate.exchange(requestUri + "/{id}",
                HttpMethod.PATCH,
                new HttpEntity<>(course),
                CourseView.class,
                Long.toString(id));
	}

	@Override
	public void deleteById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		restTemplate.delete(requestUri + "/{id}", Long.toString(id));
	}

	@Override
	public void deleteCourse(List<CourseView> courseList) {
		if (courseList == null) {
			throw new RuntimeException("No course provided!");
		}

		for (CourseView course : courseList) {
			deleteById(course.getId());
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
	public boolean isCourseExist(CourseView course) {

		return course != null && course.getId() != null && findById(course.getId()) != null;
	}

}
