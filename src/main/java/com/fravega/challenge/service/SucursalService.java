package com.fravega.challenge.service;

import com.fravega.challenge.entity.Sucursal;
import com.fravega.challenge.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import static com.fravega.challenge.util.DistanciaEntreDosPuntos.getDistancia;
import static com.fravega.challenge.util.CheckInput.isValid;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class SucursalService {
    private static final Logger LOGGER = Logger.getLogger( SucursalService.class.getName() );

    @Autowired
    SucursalRepository sucursalRepository;

    public Sucursal getSucursal(String id) {
        LOGGER.info("Inicia consulta sucursal por id...");
        if (id == null){
            LOGGER.severe("No se ha ingresado el parametro id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe proporcionar un id");
        }
        LOGGER.info("Buscando sucursal con id: " + id);
        Sucursal result = sucursalRepository.findSucursalById(id);

        if (result == null) {
            LOGGER.info("No se encontró una sucursal con ese id");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró una sucursal con ese id");
        }
        LOGGER.info("Consulta finalizada");
        return result;
    }

    public Sucursal createSucursal(Sucursal sucursal) {
        LOGGER.info("Inicia alta de sucursal");
        if(sucursal == null) {
            LOGGER.severe("No se recibieron datos");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos los campos son obligatorios");
        }
        if (sucursal.getDireccion() == null) {
            LOGGER.severe("No se recibio el campo direccion");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo direccion es obligatorio");
        }

        if (sucursal.getLatitud() == null) {
            LOGGER.severe("No se recibio el campo latitud");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo latitud es obligatorio");
        }

        if (sucursal.getLongitud() == null) {
            LOGGER.severe("No se recibio el campo longitud");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo longitud es obligatorio");
        }


        if(!isValid(sucursal.getLatitud()) || !isValid(sucursal.getLongitud())){
            LOGGER.severe("Los campos latitud o longitud no son válidos");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los campos latitud o longitud no son válidos");
        }
        LOGGER.info("Validaciones OK");
        LOGGER.info("Creando nueva sucursal...");
        return sucursalRepository.save(sucursal);
    }

    public Sucursal getNearby(String latitud, String longitud) {
        LOGGER.info("Inicia consulta sucursal cercana");
        Double lat = null;
        Double lon = null;

        if(isValid(latitud) && isValid(longitud)){
            lat = Double.valueOf(latitud);
            lon = Double.valueOf(longitud);
        }else{
            LOGGER.severe("Los valores no son válidos");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los valores ingresados no son válidos");
        }
        LOGGER.info("Obteniendo sucursales...");
        List<Sucursal> sucursales = sucursalRepository.findAll();

        HashMap<String, Double> distancias = new HashMap<>();

        LOGGER.info("Calculando distancias...");
        for (Sucursal sucursal : sucursales){
            Double current = getDistancia(lat, lon, Double.valueOf(sucursal.getLatitud()), Double.valueOf(sucursal.getLongitud()));
            distancias.put(sucursal.getId(), current);
        }

        LOGGER.info("Ordenando resultados...");
        Map<String, Double> resultado = distancias.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (viejo, nuevo) -> viejo, LinkedHashMap::new));

        Map.Entry<String,Double> entry = resultado.entrySet().iterator().next();
        String key = entry.getKey();
        Sucursal search = getSucursal(key);

        if (search == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado una sucursal cerca");

        return getSucursal(key);
    }
}
