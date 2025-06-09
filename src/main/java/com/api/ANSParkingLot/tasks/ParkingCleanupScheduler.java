package com.api.ANSParkingLot.tasks;

import com.api.ANSParkingLot.models.GuestModel;
import com.api.ANSParkingLot.models.ParkingSpotModel;
import com.api.ANSParkingLot.services.GuestService;
import com.api.ANSParkingLot.services.ParkingSpotService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ParkingCleanupScheduler {

    private final GuestService guestService;
    private final ParkingSpotService parkingSpotService;

    public ParkingCleanupScheduler(GuestService guestService, ParkingSpotService parkingSpotService) {
        this.guestService = guestService;
        this.parkingSpotService = parkingSpotService;
    }

    // Esta tarefa será executada uma vez por dia à meia-noite (0 0 0 * * *)
    // Para testes, pode ser a cada 30 segundos: "*/30 * * * * *"
    @Scheduled(cron = "*/30 * * * * *") // Executa todo dia à meia-noite
    @Transactional
    public void cleanUpExpiredGuestsAndSpots() {
        System.out.println("Iniciando limpeza de hóspedes expirados e vagas.");
        LocalDateTime now = LocalDateTime.now();

        // Buscar todos os hóspedes com expireDate anterior ou igual à data atual

        List<GuestModel> expiredGuests = guestService.findExpiredGuests(now);

        for (GuestModel guest : expiredGuests) {
            System.out.println("Hóspede expirado encontrado: " + guest.getName() + " para a vaga " + guest.getParkingSpotNumber());

            Optional<ParkingSpotModel> parkingSpotOptional = parkingSpotService.findByParkingSpotNumber(guest.getParkingSpotNumber());

            if (parkingSpotOptional.isPresent()) {
                ParkingSpotModel parkingSpot = parkingSpotOptional.get();


                if (parkingSpot.isOccupied()) {
                    parkingSpot.setOccupied(false);
                    // Adicionar depois o guest na parkingSpot
                    // parkingSpot.setGuest(null);
                    parkingSpotService.save(parkingSpot);
                    System.out.println("Vaga " + parkingSpot.getParkingSpotNumber() + " desocupada.");
                }
            }
            //Deletar o hóspede expirado
            // guestService.delete(guest);
        }
        System.out.println("Limpeza de hóspedes expirados e vagas concluída.");
    }
}