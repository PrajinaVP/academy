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
public class ModuleView implements Module {
	Long id;
	String name;
	String description;
	String contact;
	String version;
	String status;


	private Set<CourseView> courses = new HashSet<>();
	
	ModuleView() {	}

	ModuleView(Module module){
		BeanUtils.copyProperties(module,  null, ModuleView.class);
	}
	
	public static ModuleView convert(Module module) {
		if (module == null) {
			return null;
		}
		if(module instanceof ModuleView) {
			return (ModuleView) module;
		}
		return new ModuleView(module);	
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
	@JsonDeserialize(contentAs = CourseView.class)
	public void setCourses(Set<Course> courses) {
		//TODO conversion method to cast courses with a stream collecting to a Set of Views
		this.courses = courses.stream().map(CourseView::convert).collect(Collectors.toSet()); 
		
	}
	
	@Override
	@JsonProperty
	@JsonSerialize(contentAs = CourseView.class)
	public Set<Course> getCourses() {
		return ImmutableSet.copyOf(courses);		
	}
	
	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", description=" + description + ", contact=" + contact
				+ ", version=" + version + ", status=" + status + "]";
	}
	
}
