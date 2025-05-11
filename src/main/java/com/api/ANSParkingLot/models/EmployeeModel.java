package com.api.ANSParkingLot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_EMPLOYEE")
public class EmployeeModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, name = "employee_registration_number")
    private int employeeRegistrationNumber;

    @Column(length = 11, unique = true)
    private String cpf;

    private String gender;

    private String email;

    private String cellphone;

    private String address;

    private String cargo;

    private Double salary;

    private String birthdate;

    private String admissionDate;

    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private ParkingSpotModel parkingSpot;

    @OneToOne(mappedBy = "employee")
    private VehicleModel vehicle;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeRegistrationNumber() {
        return employeeRegistrationNumber;
    }

    public void setEmployeeRegistrationNumber(int employeeRegistrationNumber) {
        this.employeeRegistrationNumber = employeeRegistrationNumber;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public ParkingSpotModel getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpotModel parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public VehicleModel getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleModel vehicle) {
        this.vehicle = vehicle;
    }
}
