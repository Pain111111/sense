package com.hack_attack.sense.controller;

import com.hack_attack.sense.dto.LevelDTO;
import com.hack_attack.sense.entity.Bay;
import com.hack_attack.sense.entity.Level;
import com.hack_attack.sense.repository.BayRepository;
import com.hack_attack.sense.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/levels")
public class LevelController {

    private final LevelService levelService;

    private final BayRepository bayRepository;

    @Autowired
    public LevelController(LevelService levelService, BayRepository bayRepository) {
        this.levelService = levelService;
        this.bayRepository = bayRepository;
    }

    @GetMapping
    public ResponseEntity<List<LevelDTO>> getAllLevels() {
        List<Level> levels = levelService.getAllLevels();
        List<LevelDTO> dtos = levels.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LevelDTO> getLevelById(@PathVariable String id) {
        Level level = levelService.getLevelById(id);
        if (level != null) {
            return ResponseEntity.ok(toDTO(level));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LevelDTO> createLevel(@RequestBody LevelDTO levelDTO) {
        Level level = toEntity(levelDTO);

        Level createdLevel = levelService.createLevel(level);

        LevelDTO responseDTO = toDTO(createdLevel);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LevelDTO> updateLevel(@PathVariable String id, @RequestBody LevelDTO levelDTO) {
        Level level = toEntity(levelDTO);
        level.setId(id);

        Level updatedLevel = levelService.updateLevel(id, level);

        if (updatedLevel != null) {
            LevelDTO responseDTO = toDTO(updatedLevel);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable String id) {
        boolean deleted = levelService.deleteLevel(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Helper methods
    private LevelDTO toDTO(Level level) {
        LevelDTO dto = new LevelDTO();
        dto.setId(level.getId());
        dto.setName(level.getName());
        if (level.getBay() != null) {
            dto.setBayId(level.getBay().getId());
        }
        dto.setImage(level.getImage());
        dto.setRipenessValue(level.getRipenessValue());
        dto.setColor(level.getColor());
        dto.setFnvType(level.getFnvType());
        return dto;
    }

    private Level toEntity(LevelDTO dto) {
        Level level = new Level();
        if(dto.getBayId() != null) {
            Bay bay = bayRepository.findById(dto.getBayId()).orElseThrow(()-> new RuntimeException("The BayId entered as input is invalid"));
            level.setBay(bay);
        }
        level.setName(dto.getName());
        // You can optionally set Bay using its ID here if needed
        level.setImage(dto.getImage());
        level.setRipenessValue(dto.getRipenessValue());
        level.setColor(dto.getColor());
        level.setFnvType(dto.getFnvType());
        return level;
    }

}
