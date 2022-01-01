package com.prajina.academy.model;

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
public class Course {
	// TODO move common fields to base class
	private long id;
	private String name;
	private String desc;
	private String status = "active";
	private String contact;
	// private List<Module> modules;

	public Course() {
		super();
	}

	public Course(long id, String name, String desc, String status, String contact) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.status = status;
		this.contact = contact;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	public int hashCode() {
		return Objects.hash(contact, desc, id, name, status);
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
		return Objects.equals(contact, other.contact) && Objects.equals(desc, other.desc) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", desc=" + desc + ", status=" + status + ", contact=" + contact
				+ "]";
	}
}
