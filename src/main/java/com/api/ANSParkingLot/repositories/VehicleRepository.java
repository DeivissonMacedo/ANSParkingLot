package com.api.ANSParkingLot.repositories;

import com.api.ANSParkingLot.models.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleModel, Long> {

    Optional<VehicleModel> findByEmployee_EmployeeRegistrationNumber(int employeeRegistrationNumber);

    @Modifying
    @Transactional
    @Query("DELETE FROM VehicleModel v WHERE v.employee.employeeRegistrationNumber = :registrationNumber")
    void deleteByEmployeeRegistrationNumber(@Param("registrationNumber") int registrationNumber);

    boolean existsByEmployee_EmployeeRegistrationNumber(int employeeRegistrationNumber);
}
