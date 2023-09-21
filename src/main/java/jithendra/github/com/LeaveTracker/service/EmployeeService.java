package jithendra.github.com.LeaveTracker.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jithendra.github.com.LeaveTracker.Exceptions.EmployeeNotFoundException;
import jithendra.github.com.LeaveTracker.Exceptions.LeaveNotFoundException;
import jithendra.github.com.LeaveTracker.entity.Employee;
import jithendra.github.com.LeaveTracker.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	final double leaves=365*0.05;
	public ResponseEntity<Employee> createEmployee(Employee employee) throws Exception{
		employeeRepository.save(employee);
		return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);
	}
	
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);
		Employee emp = employee.get();
		if(emp==null) {
			throw new EmployeeNotFoundException("Employee with Employee Id "+id+" is not found!");
		}
		return emp;
	}
	
	public Employee updateEmployee(Integer id,Employee employee) throws EmployeeNotFoundException, LeaveNotFoundException {
		Optional<Employee> emp= employeeRepository.findById(id);
		Employee empl= emp.get();
		if(empl==null) {
			throw new EmployeeNotFoundException("Employee with Employee Id "+id+" is not found!");
		}
		empl.setEmpName(employee.getEmpName());
		empl.setDOJ(employee.getDOJ());
		empl.setNoofLeavesTaken(employee.getNoofLeavesTaken());
		Employee e = checkLeaves(id);
		empl.setLeavesAvailable(e.getLeavesAvailable());
		return empl;
	}
	
	public Employee deleteEmployee(Integer id) throws EmployeeNotFoundException {
		
		Optional<Employee> employee = employeeRepository.findById(id);
		Employee emp = employee.get();
		if(emp==null) {
			throw new EmployeeNotFoundException("Employee with Employee Id "+id+" is not found!");
		}
		employeeRepository.delete(emp);
		return emp;
	}
	
	public Employee applyLeave(Integer empId,Integer noofLeaves) throws EmployeeNotFoundException, LeaveNotFoundException {
		Optional<Employee> employoee = employeeRepository.findById(empId);
		Employee emp = employoee.get();
		if(emp==null) {
			throw new EmployeeNotFoundException("Employee with Employee Id "+empId+" is not found!");
		}
		LocalDate today = LocalDate.now();
		LocalDate joined_date= LocalDate.parse(emp.getDOJ());
		long days_Between = ChronoUnit.DAYS.between(joined_date,today);
		int leaves_taken=emp.getNoofLeavesTaken();
		double leaves_available=leaves-leaves_taken;
		double leaves_to_be_lapsed=(0.6)*leaves_available;
		int leaves_applied=noofLeaves;
		if(days_Between>365) {
			days_Between-=365;
			leaves_available=leaves_to_be_lapsed+(days_Between)*0.05;
		}
		else {
			if(days_Between<365) {
				leaves_available=days_Between*0.05;
			}
		}
		
		if(leaves_applied>leaves_available) {
			throw new LeaveNotFoundException("Dear "+emp.getEmpName()+" you don't have enough leaves. Availabel Leaves:"+leaves_available);
		}
		else {
			leaves_available-=leaves_applied;
		}
		
		emp.setLeavesAvailable(leaves_available);
		emp.setNoofLeavesTaken(emp.getNoofLeavesTaken()+leaves_applied);
		employeeRepository.save(emp);
		return emp;
				
	}

	public Employee checkLeaves(Integer id) throws EmployeeNotFoundException, LeaveNotFoundException {
		return applyLeave(id, 0);
	}
}
