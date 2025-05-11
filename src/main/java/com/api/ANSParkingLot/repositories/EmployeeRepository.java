package com.api.ANSParkingLot.repositories;

import com.api.ANSParkingLot.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {

    Optional<EmployeeModel> findByEmployeeRegistrationNumber(int registrationNumber);

}