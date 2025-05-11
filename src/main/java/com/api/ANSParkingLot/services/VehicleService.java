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
        // Obtém o número de matrícula do funcionário
        int employeeRegistrationNumber = vehicleModel.getEmployee().getEmployeeRegistrationNumber();

        // Verifica se o funcionário existe
        Optional<EmployeeModel> employeeOpt = employeeRepository.findByEmployeeRegistrationNumber(employeeRegistrationNumber);
        if (employeeOpt.isEmpty()) {
            throw new RuntimeException("Funcionário com matrícula " + employeeRegistrationNumber + " não encontrado.");
        }

        // Verifica se o funcionário já tem um veículo
        if (vehicleRepository.existsByEmployee_EmployeeRegistrationNumber(employeeRegistrationNumber)) {
            throw new RuntimeException("Funcionário já possui um veículo cadastrado.");
        }

        // Encontra a próxima vaga disponível
        Optional<ParkingSpotModel> parkingSpotOpt = parkingSpotRepository.findFirstByOccupiedFalse();
        if (parkingSpotOpt.isEmpty()) {
            throw new RuntimeException("Nenhuma vaga disponível.");
        }

        ParkingSpotModel parkingSpot = parkingSpotOpt.get(); // Aqui garantimos que temos uma vaga disponível

        // Atribui o veículo e o funcionário à vaga
        vehicleModel.setEmployee(employeeOpt.get());
        vehicleModel.setParkingSpot(parkingSpot);

        // Salva o veículo primeiro
        vehicleModel = vehicleRepository.save(vehicleModel);

        // Agora que o veículo está salvo, marca a vaga como ocupada
        parkingSpot.setOccupied(true);
        parkingSpot.setVehicle(vehicleModel);
        parkingSpot.setEmployee(employeeOpt.get());

        // Salva a vaga com as novas associações
        parkingSpotRepository.save(parkingSpot);

        // Retorna o veículo salvo
        return vehicleModel;
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
