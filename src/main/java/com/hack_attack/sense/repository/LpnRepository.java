package com.hack_attack.sense.repository;

import com.hack_attack.sense.entity.Lpn;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LpnRepository extends MongoRepository<Lpn, String> {
}
