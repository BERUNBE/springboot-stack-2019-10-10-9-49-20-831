package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeResource {

    private List<Employee> employeeList = new ArrayList<>();

    @PostMapping(path = "employee", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        employeeList.add(employee);
        return ResponseEntity.ok("Created employee " + employee.getName());
    }

    @GetMapping(path = "employees", produces = {"application/json"})
    public List<Employee> getEmployees() {
        return employeeList;
    }


}
