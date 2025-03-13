package com.api.ANSParkingLot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ParkingSpotDTO {

    @NotBlank
    @Size(max = 7)
    private String licensePlateVehicle;
    @NotBlank
    private String brandVehicle;
    @NotBlank
    private String modelVehicle;
    @NotBlank
    private String colorVehicle;
    @NotBlank
    private String employeeName;
    @NotNull
    private int employeeRegistrationNumber;
    @NotBlank
    private String guest;
    @NotBlank
    private String parkingSpotNumber;

    public @NotBlank String getGuest() {
        return guest;
    }

    public void setGuest(@NotBlank String guest) {
        this.guest = guest;
    }

    public @NotBlank String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(@NotBlank String employeeName) {
        this.employeeName = employeeName;
    }

    public @NotBlank String getColorVehicle() {
        return colorVehicle;
    }

    public void setColorVehicle(@NotBlank String colorVehicle) {
        this.colorVehicle = colorVehicle;
    }

    public @NotBlank String getModelVehicle() {
        return modelVehicle;
    }

    public void setModelVehicle(@NotBlank String modelVehicle) {
        this.modelVehicle = modelVehicle;
    }

    public @NotBlank String getBrandVehicle() {
        return brandVehicle;
    }

    public void setBrandVehicle(@NotBlank String brandVehicle) {
        this.brandVehicle = brandVehicle;
    }

    public @NotBlank @Size(max = 7) String getLicensePlateVehicle() {
        return licensePlateVehicle;
    }

    public void setLicensePlateVehicle(@NotBlank @Size(max = 7) String licensePlateVehicle) {
        this.licensePlateVehicle = licensePlateVehicle;
    }

    public @NotBlank String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(@NotBlank String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }
    @NotNull
    public int getEmployeeRegistrationNumber() {
        return employeeRegistrationNumber;
    }

    public void setEmployeeRegistrationNumber(@NotNull int employeeRegistrationNumber) {
        this.employeeRegistrationNumber = employeeRegistrationNumber;
    }



}
