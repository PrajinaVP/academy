package com.prajina.academy.api;

import java.util.Set;

public interface Module {
	
	
	 Long getId();
	 void setId(Long id);
	 String getName();
	 void setName(String name);
	 String getDescription();
	 void setDescription(String description);
	 String getContact();
	 void setContact(String contact);
	 String getVersion();
	 void setVersion(String version);
	 Set<Course> getCourses();
	 void setCourses(Set<Course> courses);
	 String getStatus();
	 void setStatus(String status);

	
}
