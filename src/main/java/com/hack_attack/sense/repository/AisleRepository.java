package com.hack_attack.sense.repository;

import com.hack_attack.sense.entity.Aisle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AisleRepository extends MongoRepository<Aisle, String> {
}

