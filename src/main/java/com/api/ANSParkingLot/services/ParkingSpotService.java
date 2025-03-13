package com.api.ANSParkingLot.services;


import com.api.ANSParkingLot.models.ParkingSpotModel;
import com.api.ANSParkingLot.repositories.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpotService {

    @Autowired

    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService (ParkingSpotRepository parkingSpotRepository){
        this.parkingSpotRepository = parkingSpotRepository;

    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public boolean existsByLicensePlateVehicle(String licensePlateVehicle) {
        return parkingSpotRepository.existsByLicensePlateVehicle(licensePlateVehicle);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByEmployeeRegistrationNumber(int employeeRegistrationNumber) {
        return parkingSpotRepository.existsByEmployeeRegistrationNumber(employeeRegistrationNumber);
    }

    public List<ParkingSpotModel> findAll() {
        return parkingSpotRepository.findAll();
    }
    public Optional<ParkingSpotModel> findByParkingSpotNumber(String parkingSpotNumber) {
        return  parkingSpotRepository.findByParkingSpotNumber(parkingSpotNumber);
    }
    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }
    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }


}
