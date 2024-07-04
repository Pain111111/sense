package com.hack_attack.sense.service.impl;

import com.hack_attack.sense.entity.Lpn;
import com.hack_attack.sense.repository.LpnRepository;
import com.hack_attack.sense.service.LpnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LpnServiceImpl implements LpnService {

    private final LpnRepository lpnRepository;

    @Autowired
    public LpnServiceImpl(LpnRepository lpnRepository) {
        this.lpnRepository = lpnRepository;
    }

    @Override
    public List<Lpn> getAllLpns() {
        return lpnRepository.findAll();
    }

    @Override
    public Lpn getLpnById(String id) {
        Optional<Lpn> optionalLpn = lpnRepository.findById(id);
        return optionalLpn.orElse(null);
    }

    @Override
    public Lpn createLpn(Lpn lpn) {
        return lpnRepository.save(lpn);
    }

    @Override
    public Lpn updateLpn(String id, Lpn lpn) {
        Optional<Lpn> optionalLpn = lpnRepository.findById(id);
        if (optionalLpn.isPresent()) {
            lpn.setId(id);
            return lpnRepository.save(lpn);
        }
        return null;
    }

    @Override
    public boolean deleteLpn(String id) {
        Optional<Lpn> optionalLpn = lpnRepository.findById(id);
        if (optionalLpn.isPresent()) {
            lpnRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
