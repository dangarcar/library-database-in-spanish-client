package es.library.databaseinspanish;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiError {

	private int statusCode;
	private ZonedDateTime timestamp;
	private String message;
	
	@JsonCreator
	public ApiError() {}
	
	public ApiError(int statusCode, ZonedDateTime timestamp, String message) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}