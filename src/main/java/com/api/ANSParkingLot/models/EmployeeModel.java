package com.api.ANSParkingLot.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TB_EMPLOYEE")
public class EmployeeModel {
    private static final long serialVersionUID = 1L;
    @OneToOne(mappedBy = "employee")
    @JsonManagedReference
    private ParkingSpotModel parkingSpot;

    @OneToOne(mappedBy = "employee")
    @JsonIgnoreProperties("employee") // evita o loop recursivo
    private VehicleModel vehicle;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
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

    public void setCellphone(String telefone) {
        this.cellphone = telefone;
    }

    public String getAddres() {
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

    public String getAdimissionDate() {
        return admissionDate;
    }

    public void setAdimissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }
}
