package com.prajina.academy.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ImmutableSet;
import com.prajina.academy.api.Course;
import com.prajina.academy.api.Module;
import com.prajina.academy.model.CourseView;
import com.prajina.academy.model.ModuleView;

@Entity
@Table(
		name="MODULE",
		uniqueConstraints= @UniqueConstraint(name="UK_MODULE_name_version", columnNames= {"name", "version"}))
@DynamicUpdate //generated SQL includes only the changed columns
public class ModuleImpl implements Module, Serializable {

	private static final long serialVersionUID = -3959312241427277951L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(name="NAME", nullable=false, length=30)
	String name;
	@Column(name="DESCRIPTION", length=100)
	String description;
	@Column(name="CONTACT", length=50)
	String contact;
	@Column(name="VERSION", nullable=false, length=5)
	String version;
	@Column(columnDefinition = "varchar(15) not null default 'active'")
	String status;
	
	//@JsonBackReference
	@ManyToMany(mappedBy = "modules", fetch = FetchType.LAZY)
	private Set<CourseImpl> courses = new HashSet<>();
	
	ModuleImpl() {	}

	ModuleImpl(Module module){
		BeanUtils.copyProperties(module,  this, ModuleImpl.class);
	}
	
	public static ModuleImpl convert(Module module) {
		if (module == null) {
			return null;
		}
		if(module instanceof ModuleImpl) {
			return (ModuleImpl) module;
		}
		
		return new ModuleImpl(module);	
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
	
	public Set<Course> getCourses() {
		return ImmutableSet.copyOf(courses);
	}

	public void setCourses(Set<Course> modules) {
		this.courses = courses.stream().map(CourseImpl::convert).collect(Collectors.toSet()); 
	}
	
	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", description=" + description + ", contact=" + contact
				+ ", version=" + version + ", status=" + status + "]";
	}
	
}
