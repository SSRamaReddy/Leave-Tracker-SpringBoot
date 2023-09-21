package jithendra.github.com.LeaveTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jithendra.github.com.LeaveTracker.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
