package com.rushi.vehiclesurvey.vo;

public enum Messages {

	FILE_PATH_NOT_PROVIDED("Please provide the file path");
	
	String message;
	
	private Messages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
