package com.prajina.academy.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Module {
	Long id;
	String name;
	String description;
	String contact;
	String version;
	String status;
	@JsonIgnore
	private Set<Course> courses = new HashSet<>();
	
	public Module() {
		super();
	}
	
	public Module(Long id, String name, String description, String contact, String version, String status,
			Set<Course> courses) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.contact = contact;
		this.version = version;
		this.status = status;
		this.courses = courses;
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
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", description=" + description + ", contact=" + contact
				+ ", version=" + version + ", status=" + status + "]";
	}
	
}
