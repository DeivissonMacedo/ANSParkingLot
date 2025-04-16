package com.api.ANSParkingLot.services;

import com.api.ANSParkingLot.models.EmployeeModel;
import com.api.ANSParkingLot.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<EmployeeModel> findByEmployeeRegistrationNumber(int employeeRegistrationNumber) {
        return employeeRepository.findByEmployeeRegistrationNumber(employeeRegistrationNumber);
    }

    public Optional<EmployeeModel> findById(Long id) {
        return employeeRepository.findById(id);
    }
    public List<EmployeeModel> findAll() {
        return employeeRepository.findAll();
    }

}
