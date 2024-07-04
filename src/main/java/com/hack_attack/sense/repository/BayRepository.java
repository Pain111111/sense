package com.hack_attack.sense.repository;

import com.hack_attack.sense.entity.Bay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BayRepository extends MongoRepository<Bay, String> {
}
