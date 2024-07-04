package com.hack_attack.sense.service.impl;

import com.hack_attack.sense.entity.Bay;
import com.hack_attack.sense.repository.BayRepository;
import com.hack_attack.sense.service.BayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BayServiceImpl implements BayService {

    private final BayRepository bayRepository;

    @Autowired
    public BayServiceImpl(BayRepository bayRepository) {
        this.bayRepository = bayRepository;
    }

    @Override
    public List<Bay> getAllBays() {
        return bayRepository.findAll();
    }

    @Override
    public Bay getBayById(String id) {
        Optional<Bay> optionalBay = bayRepository.findById(id);
        return optionalBay.orElse(null);
    }

    @Override
    public Bay createBay(Bay bay) {
        return bayRepository.save(bay);
    }

    @Override
    public Bay updateBay(String id, Bay bay) {
        Optional<Bay> optionalBay = bayRepository.findById(id);
        if (optionalBay.isPresent()) {
            bay.setId(id);
            return bayRepository.save(bay);
        }
        return null;
    }

    @Override
    public boolean deleteBay(String id) {
        Optional<Bay> optionalBay = bayRepository.findById(id);
        if (optionalBay.isPresent()) {
            bayRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
