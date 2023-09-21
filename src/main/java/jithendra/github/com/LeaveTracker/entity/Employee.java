package jithendra.github.com.LeaveTracker.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Employee {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empId;
	private String empName;
	private String DOJ;
	private double leavesAvailable;
	private int leavesTaken;
	
	
	public Employee() {
		super();
	}
	
	public Employee(Integer empId, String empName, String DOJ, double leavesAvailable,  int leavesTaken) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.DOJ = DOJ;
		this.leavesTaken = leavesTaken;
		this.leavesAvailable=leaves(DOJ,leavesTaken);
	}
	
	private double leaves(String DOJ,Integer leavesTaken) {
		LocalDate today = LocalDate.now();
		LocalDate joined_date= LocalDate.parse(DOJ);
		long days_Between = ChronoUnit.DAYS.between(joined_date,today);
		double leaves=365*0.05;
		double leaves_available=leaves-leavesTaken;
		double leaves_to_be_lapsed=(0.6)*leaves_available;
		if(days_Between>365) {
			days_Between-=365;
			leaves_available=leaves_to_be_lapsed+(days_Between)*0.05;
		}
		else {
			if(days_Between<365) {
				leaves_available=days_Between*0.05;
			}
		}
		return leaves_available;
	}

	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDOJ() {
		return DOJ;
	}
	public void setDOJ(String DOJ) {
		this.DOJ = DOJ;
	}
	public int getNoofLeavesTaken() {
		return leavesTaken;
	}
	public void setNoofLeavesTaken(int noofLeaves) {
		this.leavesTaken = noofLeaves;
	}
	public double getLeavesAvailable() {
		return leavesAvailable;
	}
	public void setLeavesAvailable(double leaves_available) {
		this.leavesAvailable = leaves_available;
	}

	@Override
	public String toString() {
		return "Employee [Employee Id=" + empId + ", Employee Name=" + empName + ", Date Of Joining=" + DOJ + ", Leaves Available="
				+ leavesAvailable + ", Leaves Taken=" + leavesTaken + "]";
	}
	
}
