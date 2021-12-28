package com.prajina.academy.model;

import java.util.List;
import java.util.Objects;

/**
 * This class represent the courses
 * 
 * @author Prajina
 *
 */
/**
 * @author prajina.purayil
 *
 */
/**
 * @author prajina.purayil
 *
 */
public class Course {
	// TODO move common fields to base class
	private Integer id;
	private String name;
	private String desc;
	private String status = "active";
	private String contact;
	private List<Module> modules;

	public Course() {
		super();
	}

	public Course(Integer id, String name, String desc, String status, String contact) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.status = status;
		this.contact = contact;
	}

	public Course(Integer id, String name, String desc, String status, String contact, List<Module> modules) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.status = status;
		this.contact = contact;
		this.modules = modules;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contact, desc, id, modules, name, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(contact, other.contact) && Objects.equals(desc, other.desc)
				&& Objects.equals(id, other.id) && Objects.equals(modules, other.modules)
				&& Objects.equals(name, other.name) && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", desc=" + desc + ", status=" + status + ", contact=" + contact
				+ ", modules=" + modules + "]";
	}
}
