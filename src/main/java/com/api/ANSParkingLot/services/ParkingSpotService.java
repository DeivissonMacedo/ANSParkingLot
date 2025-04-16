package com.api.ANSParkingLot.services;

import com.api.ANSParkingLot.models.ParkingSpotModel;
import com.api.ANSParkingLot.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public List<ParkingSpotModel> findAll() {
        try {
            List<ParkingSpotModel> spots = parkingSpotRepository.findAll();
            System.out.println("Total de vagas encontradas: " + spots.size());
            return spots;
        } catch (Exception e) {
            e.printStackTrace(); // Para ver o erro no log
            throw new RuntimeException("Erro ao buscar todas as vagas de estacionamento", e);
        }
    }

    public Optional<ParkingSpotModel> findByParkingSpotNumber(String number) {
        return parkingSpotRepository.findByParkingSpotNumber(number);
    }

    public ParkingSpotModel save(ParkingSpotModel spot) {
        return parkingSpotRepository.save(spot);
    }
}
