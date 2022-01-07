package com.prajina.academy.model;

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
