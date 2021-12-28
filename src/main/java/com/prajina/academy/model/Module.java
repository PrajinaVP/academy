package com.prajina.academy.model;

import java.util.Objects;

public class Module {

	private Integer id;
	private String name;
	private String desc;
	private Integer courseId; // Can a module be part of multiple courses?

	public Module() {
		super();
	}

	public Module(Integer id, String name, String desc, Integer courseId) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.courseId = courseId;
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

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId, desc, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		return Objects.equals(courseId, other.courseId) && Objects.equals(desc, other.desc)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", desc=" + desc + ", courseId=" + courseId + "]";
	}

}
