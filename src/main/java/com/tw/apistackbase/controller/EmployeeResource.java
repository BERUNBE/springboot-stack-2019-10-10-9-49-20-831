package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {

    private List<Employee> employeeList = new ArrayList<>();

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public List<Employee> createEmployees(@RequestBody List<Employee> employeeList) {
        this.employeeList.addAll(employeeList);
        return employeeList;
    }

    @GetMapping(path = "/{employeeId}", produces = {"application/json"})
    public Employee getEmployee(@PathVariable String employeeId) {
        return getEmployeeWithMatchingId(employeeId);
    }

    @GetMapping(produces = {"application/json"})
    public List<Employee> getEmployees() {
        return employeeList;
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<String> updateEmployee(@PathVariable String employeeId, @RequestBody Employee updatedEmployee) {
        Employee employee = getEmployeeWithMatchingId(employeeId);
        employee.setName(updatedEmployee.getName());
        employee.setAge(updatedEmployee.getAge());
        employee.setGender(updatedEmployee.getGender());
        return ResponseEntity.ok("Updated employee " + employee.getName());
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
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
