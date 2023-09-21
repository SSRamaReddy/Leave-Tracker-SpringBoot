package jithendra.github.com.LeaveTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import jithendra.github.com.LeaveTracker.Exceptions.EmployeeNotFoundException;
import jithendra.github.com.LeaveTracker.Exceptions.LeaveNotFoundException;
import jithendra.github.com.LeaveTracker.entity.Employee;
import jithendra.github.com.LeaveTracker.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public String startPage() {
		return "ShivaSolutionsHomePage";
	}
	
	@GetMapping("/ShivaSolutionsHomePage.html")
	public String ShivaSolutionsHomePage() {
		return "ShivaSolutionsHomePage";
	}
	
	@GetMapping("/ShivaSolutionsEmployeePortal.html")
	public String ShivaSolutionsEmployeePortal() {
		return "ShivaSolutionsEmployeePortal";
	}
	
	@GetMapping("/ShivaSolutionsCareers.html")
	public String ShivaSolutionsCareers() {
		return "ShivaSolutionsCareers";
	}
	
	@GetMapping("/about.html")
	public String ShivaSolutionsAbout() {
		return "about";
	}
	
	@GetMapping("/contact.html")
	public String ShivaSolutionsContacts() {
		return "contact";
	}
	
	@GetMapping("/services.html")
	public String ShivaSolutionsServices() {
		return "services";
	}
	
	@GetMapping("/team.html")
	public String ShivaSolutionsTeam() {
		return "team";
	}
	
	@GetMapping("/getEmployee/{id}")
	@ResponseBody
	public Employee getEmployee(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
		Employee emp =employeeService.getEmployeeById(id);
		if(emp== null) {
			return new Employee();
		}
		return emp;
	}
	
	@PostMapping("/saveEmployee")
	@ResponseBody
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws Exception{
		return employeeService.createEmployee(employee);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	@ResponseBody
	public Employee deleteEmployee(@PathVariable("id") Integer id) throws EmployeeNotFoundException {
		return employeeService.deleteEmployee(id);
	}
	
	@GetMapping("/applyLeave/{id}")
	@ResponseBody
	public Employee applyLeave(@PathVariable("id") Integer id, @RequestParam(value="noofLeaves" , required=true) String noofLeaves) throws EmployeeNotFoundException, LeaveNotFoundException {
		return employeeService.applyLeave(id,Integer.parseInt(noofLeaves));
	}
	
	@GetMapping("/getEmployees")
	@ResponseBody
	public List<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	@PutMapping("/updateEmployee/{id}")
	@ResponseBody
	public Employee updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) throws EmployeeNotFoundException, LeaveNotFoundException {
		return employeeService.updateEmployee(id, employee);
	}
	
	@GetMapping("/checkLeaves/{id}")
	@ResponseBody
	public  Employee checkLeaves(@PathVariable("id") Integer id) throws EmployeeNotFoundException, LeaveNotFoundException {
		return employeeService.checkLeaves(id);
	}
}
