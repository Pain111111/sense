package com.hack_attack.sense.service;

import com.hack_attack.sense.entity.Aisle;

import java.util.List;

public interface AisleService {

    List<Aisle> getAllAisles();

    Aisle getAisleById(String id);

    Aisle createAisle(Aisle aisle);

    Aisle updateAisle(String id, Aisle aisle);

    boolean deleteAisle(String id);
}
