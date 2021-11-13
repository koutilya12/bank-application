package com.icin.bankapplication.entity;

import lombok.Data;

@Data
public class Response {

	private String status;
	private String errorMessage;
	private Object data;

	public Response(String status, String errorMessage, Object data) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
		this.data = data;
	}

	
	public Response(String status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public Response(String status) {
		super();
		this.status = status;
	}
}

