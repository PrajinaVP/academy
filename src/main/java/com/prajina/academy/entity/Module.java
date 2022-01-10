package com.prajina.academy.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Module {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String desc;
	private Long courseId; // Can a module be part of multiple courses?

	public Module() {
		super();
	}

	public Module(Long id, String name, String desc, Long courseId) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.courseId = courseId;
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

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", desc=" + desc + ", courseId=" + courseId + "]";
	}

}
