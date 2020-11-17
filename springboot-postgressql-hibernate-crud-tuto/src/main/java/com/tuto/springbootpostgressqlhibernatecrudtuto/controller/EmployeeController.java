package com.tuto.springbootpostgressqlhibernatecrudtuto.controller;

import com.tuto.springbootpostgressqlhibernatecrudtuto.exception.ResourceNotFoundException;
import com.tuto.springbootpostgressqlhibernatecrudtuto.model.Employee;
import com.tuto.springbootpostgressqlhibernatecrudtuto.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }
    //get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id")Long empId) throws ResourceNotFoundException {
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not found with this id :" +empId));
        return ResponseEntity.ok().body(employee);
    }
    //save employee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
//        System.out.println(employee.getFirstname());
//        System.out.println(employee.getLastname());
//        System.out.println(employee.getEmail());
        return this.employeeRepository.save(employee);
    }
    //update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long empid,@RequestBody Employee employeeDetails) throws ResourceNotFoundException {

        Employee employee=employeeRepository.findById(empid)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not found with this id :" +empid));

        employee.setFirstname(employeeDetails.getFirstname());
//        employee.setLastname(employeeDetails.getLastname());
//        employee.setEmail(employeeDetails.getEmail());

        return ResponseEntity.ok(this.employeeRepository.save(employee));
    }
    //delete employee
    @DeleteMapping("/employees/{id}")
    public Map<String,Boolean> deleteEmployee(@PathVariable(value = "id") Long empid) throws ResourceNotFoundException {

        Employee employee=employeeRepository.findById(empid)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not found with this id :" +empid));

        this.employeeRepository.delete(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);

        return response;
    }
}
