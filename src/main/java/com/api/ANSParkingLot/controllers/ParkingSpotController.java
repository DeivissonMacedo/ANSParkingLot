package com.api.ANSParkingLot.controllers;

import com.api.ANSParkingLot.models.EmployeeModel;
import com.api.ANSParkingLot.models.ParkingSpotModel;
import com.api.ANSParkingLot.models.VehicleModel;
import com.api.ANSParkingLot.services.EmployeeService;
import com.api.ANSParkingLot.services.ParkingSpotService;
import com.api.ANSParkingLot.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;
    final VehicleService vehicleService;
    final EmployeeService employeeService;

    public ParkingSpotController(ParkingSpotService parkingSpotService, VehicleService vehicleService, EmployeeService employeeService) {
        this.parkingSpotService = parkingSpotService;
        this.vehicleService = vehicleService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots() {
        return ResponseEntity.ok(parkingSpotService.findAll());
    }

    @GetMapping("/{number}")
    public ResponseEntity<Object> getByNumber(@PathVariable String number) {
        Optional<ParkingSpotModel> optional = parkingSpotService.findByParkingSpotNumber(number);
        return optional.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada"));
    }

    @PostMapping("/{number}/assign")
    public ResponseEntity<Object> assignVehicleAndEmployee(@PathVariable String number, @RequestParam Long vehicleId, @RequestParam Long employeeId) {
        Optional<ParkingSpotModel> spotOpt = parkingSpotService.findByParkingSpotNumber(number);
        if (spotOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
        }

        ParkingSpotModel spot = spotOpt.get();

        if (spot.getVehicle() != null || spot.getEmployee() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Vaga já está ocupada");
        }

        Optional<VehicleModel> vehicleOpt = vehicleService.findById(vehicleId);
        Optional<EmployeeModel> employeeOpt = employeeService.findById(employeeId);

        if (vehicleOpt.isEmpty() || employeeOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo ou Funcionário não encontrado");
        }

        spot.setVehicle(vehicleOpt.get());
        spot.setEmployee(employeeOpt.get());

        return ResponseEntity.ok(parkingSpotService.save(spot));
    }

    @PutMapping("/{number}/unassign")
    public ResponseEntity<Object> unassign(@PathVariable String number) {
        Optional<ParkingSpotModel> spotOpt = parkingSpotService.findByParkingSpotNumber(number);
        if (spotOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
        }

        ParkingSpotModel spot = spotOpt.get();
        spot.setVehicle(null);
        spot.setEmployee(null);

        return ResponseEntity.ok(parkingSpotService.save(spot));
    }
}
