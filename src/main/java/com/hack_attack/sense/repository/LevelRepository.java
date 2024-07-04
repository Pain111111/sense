package com.hack_attack.sense.repository;

import com.hack_attack.sense.entity.Level;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends MongoRepository<Level, String> {
}
