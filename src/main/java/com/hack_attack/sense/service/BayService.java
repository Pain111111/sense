package com.hack_attack.sense.service;

import com.hack_attack.sense.entity.Bay;

import java.util.List;

public interface BayService {

    List<Bay> getAllBays();

    Bay getBayById(String id);

    Bay createBay(Bay bay);

    Bay updateBay(String id, Bay bay);

    boolean deleteBay(String id);
}
