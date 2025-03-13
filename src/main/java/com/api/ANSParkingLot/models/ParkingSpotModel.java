package com.api.ANSParkingLot.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable {
    private static final long serialVersionUID = 1L;




    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 10)
    private String parkingSpotNumber;
    @Column(nullable = false, unique = true, length = 7)
    private String licensePlateVehicle;;
    @Column(nullable = false, length = 70)
    private String brandVehicle;;
    @Column(nullable = false, length = 70)
    private String modelVehicle;;
    @Column(nullable = false, length = 70)
    private String colorVehicle;
    @Column (nullable = false)
    private LocalDateTime registrationDate;
    @Column (nullable = false, length = 150)
    private String employeeName;
    @Column (nullable = false,unique = true, length = 6)
    private int employeeRegistrationNumber;
    @Column (nullable = false, length = 150)
    private String guest;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getEmployeeRegistrationNumber() {
        return employeeRegistrationNumber;
    }

    public void setEmployeeRegistrationNumber(int employeeRegistrationNumber) {
        this.employeeRegistrationNumber = employeeRegistrationNumber;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }


    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getLicensePlateVehicle() {
        return licensePlateVehicle;
    }

    public void setLicensePlateVehicle(String licensePlateVehicle) {
        this.licensePlateVehicle = licensePlateVehicle;
    }

    public String getBrandVehicle() {
        return brandVehicle;
    }

    public void setBrandVehicle(String brandVehicle) {
        this.brandVehicle = brandVehicle;
    }

    public String getModelVehicle() {
        return modelVehicle;
    }

    public void setModelVehicle(String modelVehicle) {
        this.modelVehicle = modelVehicle;
    }

    public String getColorVehicle() {
        return colorVehicle;
    }

    public void setColorVehicle(String colorVehicle) {
        this.colorVehicle = colorVehicle;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }





}
