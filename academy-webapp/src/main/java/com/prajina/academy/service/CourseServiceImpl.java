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

import com.prajina.academy.model.Course;

@Service
public class CourseServiceImpl implements CourseService {

	Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	private final String RESOURCE_URI = "/course";
	
	@Value("${academy.service.url}")
	private String serviceBaseUrl; 
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<Course[]> findAll(Integer pageNum, Integer pageSize, String sortBy) {
		String requestUri = getRequestUri(pageNum, pageSize, sortBy);
		Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("pageNum", Integer.toString(pageNum));
		urlParameters.put("pageSize", Integer.toString(pageSize));
		urlParameters.put("sortBy", sortBy);
		  
		ResponseEntity<Course[]> responseEntity = restTemplate.getForEntity(requestUri,
		                                                                Course[].class,
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
	public ResponseEntity<Course> findById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
        ResponseEntity<Course> responseEntity = restTemplate.getForEntity(requestUri+"/{id}", Course.class, Long.toString(id));
        
        return responseEntity;
	}

	@Override
	public ResponseEntity<Course> findByName(String name) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
        ResponseEntity<Course> responseEntity = restTemplate.getForEntity(requestUri+"?name={name}", Course.class, name);
        
        return responseEntity;
	}

	@Override
	public ResponseEntity<Course> findByDesc(String description) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
        ResponseEntity<Course> responseEntity = restTemplate.getForEntity(requestUri+"?description={description}", Course.class, description);
        
        return responseEntity;
	}

	@Override
	public ResponseEntity<Course> save(Course course) {
		if (course == null) {
			throw new RuntimeException("No course provided!");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		
		return restTemplate.postForEntity(requestUri, course, Course.class);
	}

	// TODO Use saveAll
	@Override
	public void save(List<Course> courseList) {
		if (courseList == null) {
			throw new RuntimeException("No course provided for save!");
		}

		for (Course course : courseList) {
			save(course);
		}
	}
	
	@Override
	public ResponseEntity<Course> update(Long id, Course course) {
		logger.debug("Updating Course id :: " + id + "  course :: " +course.toString());
		if (course == null || course.getId() == null) {
			throw new RuntimeException("No course provided for update!");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		logger.debug("Updating Course requestUri " + requestUri);
		
		return restTemplate.exchange(requestUri + "/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(course),
                Course.class,
                Long.toString(id));
	}
	
	@Override
	public ResponseEntity<Course> patch(Long id, Course course) {
		if (course == null || course.getId() == null) {
			throw new RuntimeException("No course provided for update!");
		}
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		return restTemplate.exchange(requestUri + "/{id}",
                HttpMethod.PATCH,
                new HttpEntity<>(course),
                Course.class,
                Long.toString(id));
	}

	@Override
	public void deleteById(Long id) {
		String requestUri = serviceBaseUrl + RESOURCE_URI;
		restTemplate.delete(requestUri + "/{id}", Long.toString(id));
	}

	@Override
	public void deleteCourse(List<Course> courseList) {
		if (courseList == null) {
			throw new RuntimeException("No course provided!");
		}

		for (Course course : courseList) {
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
	public boolean isCourseExist(Course course) {

		return course != null && course.getId() != null && findById(course.getId()) != null;
	}

}
