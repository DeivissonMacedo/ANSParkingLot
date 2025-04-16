package com.api.ANSParkingLot.repositories;

import com.api.ANSParkingLot.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    Optional<ParkingSpotModel> findByParkingSpotNumber(String parkingSpotNumber);

    Optional<ParkingSpotModel> findFirstByOccupiedFalse();
}
