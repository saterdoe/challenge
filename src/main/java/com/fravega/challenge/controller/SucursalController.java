package com.fravega.challenge.controller;

import com.fravega.challenge.ChallengeApplication;
import com.fravega.challenge.entity.Sucursal;
import com.fravega.challenge.service.SucursalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.logging.Logger;

@Slf4j
@RestController
public class SucursalController {
    private static final Logger LOGGER = Logger.getLogger( SucursalController.class.getName() );

    @Autowired
    SucursalService sucursalService;

    @GetMapping("/inicio")
    public ResponseEntity<Sucursal> getNearbySucursal(@RequestParam("latitud") String latitud, @RequestParam("longitud") String longitud){
        return new ResponseEntity<>(sucursalService.getNearby(latitud, longitud), HttpStatus.OK);
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity <Sucursal> getSucursal(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(sucursalService.getSucursal(id), HttpStatus.OK);
    }

    @PostMapping("/sucursal")
    public ResponseEntity<Sucursal> createSucursal(@RequestBody Sucursal sucursal) throws Exception {
        Sucursal sucursalCreated = sucursalService.createSucursal(sucursal);
        return new ResponseEntity<>(sucursalCreated, HttpStatus.CREATED);
    }
}
