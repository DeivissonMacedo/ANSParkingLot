package com.api.ANSParkingLot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VehicleDTO {

    @NotBlank
    @Size(max = 7)
    private String licensePlate;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String color;

    @NotNull
    private int employeeRegistrationNumber;

    // Getters e Setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEmployeeRegistrationNumber() {
        return employeeRegistrationNumber;
    }

    public void setEmployeeRegistrationNumber(int employeeRegistrationNumber) {
        this.employeeRegistrationNumber = employeeRegistrationNumber;
    }
}
