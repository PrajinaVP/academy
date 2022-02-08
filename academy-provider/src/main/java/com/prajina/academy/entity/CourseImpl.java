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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.collect.ImmutableSet;
import com.prajina.academy.api.Course;
import com.prajina.academy.api.Module;
import com.prajina.academy.model.ModuleView;


@Entity
@Table(
		name="COURSE",
		uniqueConstraints= @UniqueConstraint(name="UK_COURSE_name_version", columnNames= {"name", "version"}))
@DynamicUpdate
public class CourseImpl implements Course, Serializable {
	
	private static final long serialVersionUID = -3502398350753306290L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
    // NOTE: THIS STILL CREATES COLUMNS WITH LOWER CASE NAME IN MYSQL DB
	@Column(name="NAME", nullable=false, length=30)
	String name;
	@Column(name="DESCRIPTION", length=100)
	String description;
	@Column(name="CONTACT", length=50)
	String contact;
	@Column(name="VERSION", nullable=false, length=5)
	String version;
	@Column(name="STATUS", columnDefinition = "varchar(15) not null default 'draft'")
	String status;
	
	//@JsonManagedReference
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "COURSE_MODULE",
        joinColumns = {
                @JoinColumn(name = "course_id", referencedColumnName = "id")},
        		inverseJoinColumns = {
					@JoinColumn(name = "module_id", referencedColumnName = "id")})
    private Set<ModuleImpl> modules = new HashSet<>();

	CourseImpl() {	}

    public CourseImpl(Course course){
		BeanUtils.copyProperties(course, this, CourseImpl.class);
	}
	
	public static CourseImpl convert(Course course) {
		if (course == null) {
			return null;
		}
		if(course instanceof CourseImpl) {
			CourseImpl courseImpl = (CourseImpl) course;
			
			return courseImpl;
		}
		
		return new CourseImpl(course);
		
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

	public Set<Module> getModules() {
		return ImmutableSet.copyOf(modules);
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules.stream().map(ModuleImpl::convert).collect(Collectors.toSet()); 	
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + ", contact=" + contact
				+ ", version=" + version + ", status=" + status + ", modules=" + modules + "]";
	}

}
