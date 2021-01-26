package com.fravega.challenge.repository;

import com.fravega.challenge.entity.Sucursal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends MongoRepository<Sucursal, String> {

    Sucursal findSucursalById(String id);
}
