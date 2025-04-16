package com.api.ANSParkingLot.services;

import com.api.ANSParkingLot.models.EmployeeModel;
import com.api.ANSParkingLot.models.ParkingSpotModel;
import com.api.ANSParkingLot.models.VehicleModel;
import com.api.ANSParkingLot.repositories.EmployeeRepository;
import com.api.ANSParkingLot.repositories.ParkingSpotRepository;
import com.api.ANSParkingLot.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public VehicleModel save(VehicleModel vehicleModel) {
        int regNumber = vehicleModel.getEmployee().getEmployeeRegistrationNumber();

        // Verifica se funcionário existe
        Optional<EmployeeModel> employeeOpt = employeeRepository.findByEmployeeRegistrationNumber(regNumber);
        if (employeeOpt.isEmpty()) {
            throw new RuntimeException("Funcionário com matrícula " +  regNumber+" não encontrado.");
        }

        // Verifica se já tem veículo
        if (vehicleRepository.existsByEmployee_EmployeeRegistrationNumber(regNumber)) {
            throw new RuntimeException("Funcionário já possui um veículo cadastrado");
        }

        // Encontra vaga livre
        Optional<ParkingSpotModel> spotOpt = parkingSpotRepository.findFirstByOccupiedFalse();
        if (spotOpt.isEmpty()) {
            throw new RuntimeException("Nenhuma vaga disponível");
        }

        // Atribui vaga ao veículo
        ParkingSpotModel spot = spotOpt.get();
        spot.setOccupied(true);
        parkingSpotRepository.save(spot);

        vehicleModel.setParkingSpot(spot);
        vehicleModel.setEmployee(employeeOpt.get());

        return vehicleRepository.save(vehicleModel);
    }

    public Optional<VehicleModel> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Optional<VehicleModel> findByRegistrationNumber(int employeeRegistrationNumber) {
        return vehicleRepository.findByEmployee_EmployeeRegistrationNumber(employeeRegistrationNumber);
    }

    public boolean existsByEmployeeRegistrationNumber(int employeeRegistrationNumber) {
        return vehicleRepository.existsByEmployee_EmployeeRegistrationNumber(employeeRegistrationNumber);
    }

    public void deleteByEmployeeRegistrationNumber(int employeeRegistrationNumber) {
        Optional<VehicleModel> vehicleOpt = vehicleRepository.findByEmployee_EmployeeRegistrationNumber(employeeRegistrationNumber);
        vehicleOpt.ifPresent(vehicle -> {
            ParkingSpotModel spot = vehicle.getParkingSpot();
            if (spot != null) {
                spot.setOccupied(false);
                parkingSpotRepository.save(spot);
            }
            vehicleRepository.deleteByEmployeeRegistrationNumber(employeeRegistrationNumber);
        });
    }
}
