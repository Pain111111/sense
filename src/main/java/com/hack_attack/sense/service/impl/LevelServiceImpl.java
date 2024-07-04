package com.hack_attack.sense.service.impl;

import com.hack_attack.model.LevelDetailsResponse;
import com.hack_attack.model.LpnDetailsResponse;
import com.hack_attack.sense.entity.Level;
import com.hack_attack.sense.entity.Lpn;
import com.hack_attack.sense.entity.Vendor;
import com.hack_attack.sense.repository.LevelRepository;
import com.hack_attack.sense.repository.LpnRepository;
import com.hack_attack.sense.repository.VendorRepository;
import com.hack_attack.sense.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    private final VendorRepository vendorRepository;

    private final LpnRepository lpnRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository, VendorRepository vendorRepository, LpnRepository lpnRepository) {
        this.levelRepository = levelRepository;
        this.vendorRepository = vendorRepository;
        this.lpnRepository = lpnRepository;
    }

    @Override
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    @Override
    public Level getLevelById(String id) {
        Optional<Level> optionalLevel = levelRepository.findById(id);
        return optionalLevel.orElse(null);
    }

    @Override
    public Level createLevel(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public Level updateLevel(String id, Level level) {
        Optional<Level> optionalLevel = levelRepository.findById(id);
        if (optionalLevel.isPresent()) {
            level.setId(id);
            return levelRepository.save(level);
        }
        return null;
    }

    @Override
    public boolean deleteLevel(String id) {
        Optional<Level> optionalLevel = levelRepository.findById(id);
        if (optionalLevel.isPresent()) {
            levelRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public LevelDetailsResponse getLevelDetailsById(String id) {
        Level level = levelRepository.findById(id).orElseThrow(() -> new RuntimeException("Level not found"));
        Vendor vendor = level.getBay().getVendor();
        List<Lpn> lpns = lpnRepository.findByLevel(level);

        double totalWeight = lpns.stream().mapToDouble(Lpn::getWeightKg).sum();
        List<LpnDetailsResponse> huResponses = lpns.stream().map(this::toLpnDetailsResponse).collect(Collectors.toList());

        LevelDetailsResponse response = new LevelDetailsResponse();
        response.setId(level.getId());
        response.setRipenessValue(level.getRipenessValue());
        response.setAisle(level.getBay().getAisle().getName());
        response.setBay(level.getBay().getName());
        response.setLevel(level.getName());
        response.setColor(level.getColor().name());
        response.setVendor(vendor.getName());
//        response.setImage(new String(level.getImage())); // Assuming image is stored as byte array
        response.setQuantity(totalWeight + "kg");
        response.setFnvType(level.getFnvType());
        response.setHu(huResponses);

        return response;
    }

    private LpnDetailsResponse toLpnDetailsResponse(Lpn lpn) {
        LpnDetailsResponse response = new LpnDetailsResponse();
        response.setHuName(lpn.getName());
        response.setQuantity(lpn.getWeightKg() + "kg");
        response.setRipenessValue(lpn.getLevel().getRipenessValue());
        return response;
    }
}
