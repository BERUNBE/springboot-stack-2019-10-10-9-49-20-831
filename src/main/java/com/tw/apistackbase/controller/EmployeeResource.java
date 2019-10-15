package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class EmployeeResource {

    private List<Employee> employeeList = new ArrayList<>();

    @PostMapping(path = "/employee", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        employeeList.add(employee);
        return ResponseEntity.ok("Created employee " + employee.getName());
    }

    @GetMapping(path = "/employees", produces = {"application/json"})
    public List<Employee> getEmployees() {
        return employeeList;
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<String> updateUser(@PathVariable String employeeId, @RequestBody Employee updatedEmployee) {
        Employee employee = getEmployeeWithMatchingId(employeeId);
        employee.setName(updatedEmployee.getName());
        employee.setAge(updatedEmployee.getAge());
        employee.setGender(updatedEmployee.getGender());
        return ResponseEntity.ok("Updated employee " + updatedEmployee.getName());
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteUser(@PathVariable String employeeId) {
        Employee employee = getEmployeeWithMatchingId(employeeId);
        String name = employee.getName();
        employeeList.remove(employee);
        return ResponseEntity.ok("Deleted employee " + name);
    }

    private Employee getEmployeeWithMatchingId(String employeeId) {
        return employeeList.stream()
                .filter(employee1 -> employee1.getId() == Integer.parseInt(employeeId))
                .findFirst()
                .get();
    }
}
