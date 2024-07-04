package com.hack_attack.sense.repository;

import com.hack_attack.sense.entity.Level;
import com.hack_attack.sense.entity.Lpn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LpnRepository extends MongoRepository<Lpn, String> {

    /**
     * find by level.
     * @param level
     * @return List<Lpn> associated with a level.
     */
    List<Lpn> findByLevel(Level level);

}
