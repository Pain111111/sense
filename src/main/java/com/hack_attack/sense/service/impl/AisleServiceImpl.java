package com.hack_attack.sense.service.impl;

import com.hack_attack.sense.entity.Aisle;
import com.hack_attack.sense.repository.AisleRepository;
import com.hack_attack.sense.service.AisleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AisleServiceImpl implements AisleService {

    private final AisleRepository aisleRepository;

    @Autowired
    public AisleServiceImpl(AisleRepository aisleRepository) {
        this.aisleRepository = aisleRepository;
    }

    @Override
    public List<Aisle> getAllAisles() {
        return aisleRepository.findAll();
    }

    @Override
    public Aisle getAisleById(String id) {
        Optional<Aisle> optionalAisle = aisleRepository.findById(id);
        return optionalAisle.orElse(null);
    }

    @Override
    public Aisle createAisle(Aisle aisle) {
        return aisleRepository.save(aisle);
    }

    @Override
    public Aisle updateAisle(String id, Aisle aisle) {
        Optional<Aisle> optionalAisle = aisleRepository.findById(id);
        if (optionalAisle.isPresent()) {
            aisle.setId(id);
            return aisleRepository.save(aisle);
        }
        return null;
    }

    @Override
    public boolean deleteAisle(String id) {
        Optional<Aisle> optionalAisle = aisleRepository.findById(id);
        if (optionalAisle.isPresent()) {
            aisleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
