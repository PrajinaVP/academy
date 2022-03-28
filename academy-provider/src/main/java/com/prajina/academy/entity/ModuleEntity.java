package com.prajina.academy.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(
		name="MODULE",
		uniqueConstraints= @UniqueConstraint(name="UK_MODULE_name_version", columnNames= {"name", "version"}))
@DynamicUpdate //generated SQL includes only the changed columns
public class ModuleEntity implements Serializable {

	private static final long serialVersionUID = -3959312241427277951L;
	@Id
	// IDENTITY strategy is not supported in H2 db. Hence, using sequence. Auto was using the same sequence across all entities.
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_course_id")
	@SequenceGenerator(name = "seq_module_id", allocationSize = 1)
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
	@ManyToMany(mappedBy = "modules", fetch = FetchType.LAZY)
	private Set<CourseEntity> courses = new HashSet<>();
	
	public ModuleEntity() {
		super();
	}
	
	public ModuleEntity(Long id, String name, String description, String contact, String version, String status,
			Set<CourseEntity> courses) {
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
	public Set<CourseEntity> getCourses() {
		return courses;
	}
	public void setCourses(Set<CourseEntity> courses) {
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
