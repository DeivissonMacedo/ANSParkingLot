package com.api.ANSParkingLot.controllers;

import com.api.ANSParkingLot.dtos.VehicleDTO;
import com.api.ANSParkingLot.models.EmployeeModel;
import com.api.ANSParkingLot.models.VehicleModel;
import com.api.ANSParkingLot.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Object> registerVehicle(@RequestBody @Valid VehicleDTO dto) {
        if (vehicleService.existsByEmployeeRegistrationNumber(dto.getEmployeeRegistrationNumber())) {
            return ResponseEntity.badRequest().body("Funcionário já possui um veículo cadastrado.");
        }

        VehicleModel vehicle = new VehicleModel();
        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setBrand(dto.getBrand());
        vehicle.setModel(dto.getModel());
        vehicle.setColor(dto.getColor());

        // Correção aqui:
        EmployeeModel employee = new EmployeeModel();
        employee.setEmployeeRegistrationNumber(dto.getEmployeeRegistrationNumber());
        vehicle.setEmployee(employee);

        return ResponseEntity.ok(vehicleService.save(vehicle));
    }


    @GetMapping("/{employeeRegistrationNumber}")
    public ResponseEntity<Object> getVehicleByRegistration(@PathVariable int employeeRegistrationNumber) {
        Optional<VehicleModel> vehicle = vehicleService.findByRegistrationNumber(employeeRegistrationNumber);
        return vehicle.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{employeeRegistrationNumber}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable int employeeRegistrationNumber) {
        if (!vehicleService.existsByEmployeeRegistrationNumber(employeeRegistrationNumber)) {
            return ResponseEntity.notFound().build();
        }
        vehicleService.deleteByEmployeeRegistrationNumber(employeeRegistrationNumber);
        return ResponseEntity.ok("Veículo removido com sucesso.");
    }
}
