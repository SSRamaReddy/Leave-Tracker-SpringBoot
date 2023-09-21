package jithendra.github.com.LeaveTracker.Exceptions;

public class EmployeeNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmployeeNotFoundException() {
		// TODO Auto-generated constructor stub
		System.out.println("Employee Details are Not Found");
	}
	public EmployeeNotFoundException(String message) {
		System.out.println(message);
	}
	
}
