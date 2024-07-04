package com.hack_attack.sense.service.impl;

import com.hack_attack.sense.entity.Level;
import com.hack_attack.sense.repository.LevelRepository;
import com.hack_attack.sense.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
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
}
