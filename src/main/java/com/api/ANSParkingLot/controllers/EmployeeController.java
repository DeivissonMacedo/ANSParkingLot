package com.api.ANSParkingLot.controllers;

import com.api.ANSParkingLot.models.EmployeeModel;
import com.api.ANSParkingLot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{registrationNumber}")
    public ResponseEntity<?> getEmployeeByRegistrationNumber(@PathVariable int registrationNumber) {
        return employeeService.findByEmployeeRegistrationNumber(registrationNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

}
