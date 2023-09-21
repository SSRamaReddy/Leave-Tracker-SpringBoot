package jithendra.github.com.LeaveTracker.Exceptions;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleNotException(EmployeeNotFoundException execption){
		CustomErrorResponse response = new CustomErrorResponse("EMPLOYEE_NOT_FOUND_EXCEPTION",execption.getMessage());
		response.setTimeStamp(LocalDateTime.now());
		response.setStatus(HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<CustomErrorResponse> (response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(LeaveNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleNotException1(LeaveNotFoundException execption){
		CustomErrorResponse response = new CustomErrorResponse("LEAVE_NOT_FOUND_EXCEPTION",execption.getMessage());
		response.setTimeStamp(LocalDateTime.now());
		response.setStatus(HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<CustomErrorResponse> (response,HttpStatus.NOT_FOUND);
	}
	
	
}