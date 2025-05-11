package com.api.ANSParkingLot.services;

import com.api.ANSParkingLot.models.GuestModel;
import com.api.ANSParkingLot.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public GuestModel save(GuestModel guestModel) {
        return guestRepository.save(guestModel);
    }

    public List<GuestModel> findAll() {
        return guestRepository.findAll();
    }

    public Optional<GuestModel> findById(UUID id) {
        return guestRepository.findById(id);
    }

    public void delete(GuestModel guestModel) {
        guestRepository.delete(guestModel);
    }
}
