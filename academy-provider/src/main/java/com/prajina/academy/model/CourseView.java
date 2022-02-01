package com.prajina.academy.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableSet;
import com.prajina.academy.api.Course;
import com.prajina.academy.api.Module;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CourseView implements Course {
	Long id;
	String name;
	String description;
	String contact;
	String version;
	String status;
    private Set<ModuleView> modules = new HashSet<>();

	CourseView() {	}

	CourseView(Course course){
		BeanUtils.copyProperties(course,  null, CourseView.class);
	}
	
	public static CourseView convert(Course course) {
		if (course == null) {
			return null;
		}
		if(course instanceof CourseView) {
			return (CourseView) course;
		}
		return new CourseView(course);
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	@JsonProperty
	@JsonSerialize(contentAs = ModuleView.class)
	public Set<Module> getModules() {
		return ImmutableSet.copyOf(modules);		
	}
	
	@Override
	@JsonDeserialize(contentAs = ModuleView.class)
	public void setModules(Set<Module> modules) {
		// conversion method to cast courses with a stream collecting to a Set of Views
		this.modules = modules.stream().map(ModuleView::convert).collect(Collectors.toSet()); 	
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + ", contact=" + contact
				+ ", version=" + version + ", status=" + status + ", modules=" + modules + "]";
	}

}
