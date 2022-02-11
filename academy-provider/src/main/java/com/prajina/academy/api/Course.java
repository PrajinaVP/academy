package com.prajina.academy.api;

import java.util.Set;

public interface Course {

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

	 Set<Module> getModules();

	 void setModules(Set<Module> modules);

	 String getStatus();

	 void setStatus(String status);

}
