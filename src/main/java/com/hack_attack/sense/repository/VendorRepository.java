package com.hack_attack.sense.repository;

import com.hack_attack.sense.entity.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorRepository extends MongoRepository<Vendor, String> {
}
