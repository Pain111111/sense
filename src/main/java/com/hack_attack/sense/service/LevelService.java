package com.hack_attack.sense.service;

import com.hack_attack.sense.entity.Level;

import java.util.List;

public interface LevelService {

    List<Level> getAllLevels();

    Level getLevelById(String id);

    Level createLevel(Level level);

    Level updateLevel(String id, Level level);

    boolean deleteLevel(String id);
}
