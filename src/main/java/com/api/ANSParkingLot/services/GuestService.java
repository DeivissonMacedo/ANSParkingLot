package com.api.ANSParkingLot.services;

import com.api.ANSParkingLot.models.GuestModel;
import com.api.ANSParkingLot.models.ParkingSpotModel;
import com.api.ANSParkingLot.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GuestService {

    final GuestRepository guestRepository;
    final ParkingSpotService parkingSpotService;

    public GuestService(GuestRepository guestRepository, ParkingSpotService parkingSpotService) {
        this.guestRepository = guestRepository;
        this.parkingSpotService = parkingSpotService;
    }

    @Transactional
    public GuestModel save(GuestModel guestModel) {
        GuestModel savedGuest = guestRepository.save(guestModel);



        Optional<ParkingSpotModel> parkingSpotOptional =
                parkingSpotService.findByParkingSpotNumber(guestModel.getParkingSpotNumber());

        if (parkingSpotOptional.isPresent()) {
            ParkingSpotModel parkingSpot = parkingSpotOptional.get();
            parkingSpot.setOccupied(true);
            parkingSpotService.save(parkingSpot);
        } else {

            System.err.println("Vaga de estacionamento com número " + guestModel.getParkingSpotNumber() + " não encontrada ao cadastrar hóspede.");
        }

        return savedGuest;
    }

    public List<GuestModel> findAll() {
        return guestRepository.findAll();
    }

    public Optional<GuestModel> findById(UUID id) {
        return guestRepository.findById(id);
    }

    @Transactional
    public void delete(GuestModel guestModel) {

        if (guestModel.getParkingSpotNumber() != null) {
            Optional<ParkingSpotModel> parkingSpotOptional =
                    parkingSpotService.findByParkingSpotNumber(guestModel.getParkingSpotNumber());

            if (parkingSpotOptional.isPresent()) {
                ParkingSpotModel parkingSpot = parkingSpotOptional.get();
                parkingSpot.setOccupied(false);
                parkingSpotService.save(parkingSpot);
            }
        }
        guestRepository.delete(guestModel);
    }

    public List<GuestModel> findExpiredGuests(LocalDateTime dateTime) {
        return guestRepository.findByExpireDateBefore(dateTime);
    }
}