package com.prajina.academy.exception;

import java.util.List;
import java.util.UUID;

public class ExceptionResponse {
	private UUID id;
	private int status;
	private List<String> errors;
	private String type;
	private String path;
	private String message;
	
	public ExceptionResponse() {
		super();
	}
	
	public ExceptionResponse(UUID id, int status, List<String> errors, String type, String path, String message) {
		super();
		this.id = id;
		this.status = status;
		this.errors = errors;
		this.type = type;
		this.path = path;
		this.message = message;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
