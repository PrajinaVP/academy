package com.prajina.academy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author prajina.purayil
 *
 */
@Entity
public class Course {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String desc;
	private String status = "active";
	private String contact;
	// private List<Module> modules;

	public Course() {
		super();
	}

	public Course(Long id, String name, String desc, String status, String contact) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.status = status;
		this.contact = contact;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", desc=" + desc + ", status=" + status + "]";
	}
}
