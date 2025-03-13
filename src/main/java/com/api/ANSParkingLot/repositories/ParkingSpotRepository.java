package com.api.ANSParkingLot.repositories;

import com.api.ANSParkingLot.models.ParkingSpotModel;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {

    boolean existsByLicensePlateVehicle (String licensePlateVehicle);
    boolean existsByParkingSpotNumber (String parkingSpotNumber);
    boolean existsByEmployeeRegistrationNumber (int employeeRegistrationNumber);

    Optional<ParkingSpotModel> findByParkingSpotNumber(String parkingSpotNumber);
}


