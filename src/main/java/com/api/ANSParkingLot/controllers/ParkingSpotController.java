package com.api.ANSParkingLot.controllers;


import com.api.ANSParkingLot.dtos.ParkingSpotDTO;
import com.api.ANSParkingLot.models.ParkingSpotModel;
import com.api.ANSParkingLot.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;


    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }


    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO){
        if(parkingSpotService.existsByLicensePlateVehicle(parkingSpotDTO.getLicensePlateVehicle())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: A placa deste veículo já está registrada em uma vaga!");
        }
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDTO.getParkingSpotNumber())){
            return  ResponseEntity.status((HttpStatus.CONFLICT)).body("CONFLITO: Esta vaga não está disponível!");
        }
        if(parkingSpotService.existsByEmployeeRegistrationNumber(parkingSpotDTO.getEmployeeRegistrationNumber())){
            return ResponseEntity.status((HttpStatus.CONFLICT)).body("CONFLITO: O numero de registro do funcionário já está associado a uma vaga");
        }
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));

    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());

    }

    @GetMapping("/{parkingSpotNumber}")
    public ResponseEntity <Object> getParkingSpotByparkingSpotNumber(@PathVariable (value ="parkingSpotNumber") String parkingSpotNumber){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findByParkingSpotNumber(parkingSpotNumber);
        return parkingSpotModelOptional.<ResponseEntity<Object>>map(
                parkingSpotModel -> ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel)).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada"));
    }

    @DeleteMapping("/{parkingSpotNumber}")
    public ResponseEntity<Object> deletParkingSpot(@PathVariable(value= "parkingSpotNumber")String parkingSpotNumber){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findByParkingSpotNumber(parkingSpotNumber);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
        }
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vaga removida com sucesso!");
    }

    @PutMapping("/{parkingSpotNumber}")
    public ResponseEntity<Object> updateParkingSpot (@PathVariable (value = "parkingSpotNumber")String parkingSpotNumber,
                                                     @RequestBody @Valid ParkingSpotDTO parkingSpotDTO){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findByParkingSpotNumber(parkingSpotNumber);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
        }
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDTO,parkingSpotModel);
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());

        return ResponseEntity.status(HttpStatus.OK).body("Vaga atualizada com sucesso");



    }
}
