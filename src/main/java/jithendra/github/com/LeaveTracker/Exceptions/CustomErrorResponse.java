package jithendra.github.com.LeaveTracker.Exceptions;
import java.time.LocalDateTime;

public class CustomErrorResponse {
	
	LocalDateTime timeStamp;
	String status;
	String errorCode;
	String message;
	
	public CustomErrorResponse() {
		super();
	}
	
	
	public CustomErrorResponse(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}


	public CustomErrorResponse(LocalDateTime timeStamp, String status, String errorCode, String message) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
	}


	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "CustomErrorResponse [timeStamp=" + timeStamp + ", status=" + status + ", errorCode=" + errorCode
				+ ", message=" + message + "]";
	}
	
	
}
