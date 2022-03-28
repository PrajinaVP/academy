package com.prajina.academy.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(
		name="COURSE",
		uniqueConstraints= @UniqueConstraint(name="UK_COURSE_name_version", columnNames= {"name", "version"}))
@DynamicUpdate
public class CourseEntity implements Serializable {
	
	private static final long serialVersionUID = -3502398350753306290L;
	@Id
	// IDENTITY strategy is not supported in H2 db. Hence, using sequence. Auto was using the same sequence across all entities.
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_module_id")
	@SequenceGenerator(name = "seq_module_id", allocationSize = 1)
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
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "COURSE_MODULE",
        joinColumns = {
                @JoinColumn(name = "course_id", referencedColumnName = "id")},
        		inverseJoinColumns = {
					@JoinColumn(name = "module_id", referencedColumnName = "id")})
    private Set<ModuleEntity> modules = new HashSet<>();

	public CourseEntity() {
		super();
	}

	public CourseEntity(Long id, String name, String description, String contact, String version, String status,
			Set<ModuleEntity> modules) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.contact = contact;
		this.version = version;
		this.status = status;
		this.modules = modules;
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

	public Set<ModuleEntity> getModules() {
		return modules;
	}

	public void setModules(Set<ModuleEntity> modules) {
		this.modules = modules;
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
