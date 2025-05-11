package com.api.ANSParkingLot.controllers;

import com.api.ANSParkingLot.models.GuestModel;
import com.api.ANSParkingLot.services.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/guest")
public class GuestController {

    final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public ResponseEntity<Object> createGuest(@RequestBody GuestModel guestModel) {
        if (guestModel.getInitialDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data inicial obrigatória");
        }

        GuestModel saved = guestService.save(guestModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<GuestModel>> getAllGuests() {
        return ResponseEntity.ok(guestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGuestById(@PathVariable UUID id) {
        Optional<GuestModel> guestOpt = guestService.findById(id);
        if (guestOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visitante não encontrado");
        }
        return ResponseEntity.ok(guestOpt.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGuest(@PathVariable UUID id) {
        Optional<GuestModel> guestOpt = guestService.findById(id);
        if (guestOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visitante não encontrado");
        }

        guestService.delete(guestOpt.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
