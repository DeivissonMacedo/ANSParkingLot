package com.api.ANSParkingLot.repositories;

import com.api.ANSParkingLot.models.GuestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface GuestRepository extends JpaRepository<GuestModel, UUID> {
    List<GuestModel> findByExpireDateBefore(LocalDateTime dateTime);

}
