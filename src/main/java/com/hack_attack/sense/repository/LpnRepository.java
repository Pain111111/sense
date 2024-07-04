package com.hack_attack.sense.repository;

import com.hack_attack.sense.entity.Lpn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LpnRepository extends MongoRepository<Lpn, String> {
}
