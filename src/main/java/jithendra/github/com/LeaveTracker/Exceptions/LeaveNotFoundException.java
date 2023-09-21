package jithendra.github.com.LeaveTracker.Exceptions;

public class LeaveNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LeaveNotFoundException(){
		System.out.println("Leave Not Found Exception");
	}
	public LeaveNotFoundException(String msg){
		System.out.println(msg);
	}

}
