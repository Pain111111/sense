package com.hack_attack.sense.controller;

import com.hack_attack.sense.dto.LpnDTO;
import com.hack_attack.sense.entity.Level;
import com.hack_attack.sense.entity.Lpn;
import com.hack_attack.sense.repository.LevelRepository;
import com.hack_attack.sense.repository.LpnRepository;
import com.hack_attack.sense.service.LpnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lpns")
public class LpnController {

    private final LpnService lpnService;

    private final LevelRepository levelRepository;

    @Autowired
    public LpnController(LpnService lpnService, LevelRepository levelRepository)
    {
        this.lpnService = lpnService;
        this.levelRepository = levelRepository;
    }

    @GetMapping
    public ResponseEntity<List<LpnDTO>> getAllLpns() {
        List<Lpn> lpns = lpnService.getAllLpns();
        List<LpnDTO> dtos = lpns.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LpnDTO> getLpnById(@PathVariable String id) {
        Lpn lpn = lpnService.getLpnById(id);
        if (lpn != null) {
            return ResponseEntity.ok(toDTO(lpn));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LpnDTO> createLpn(@RequestBody LpnDTO lpnDTO) {
        Lpn lpn = toEntity(lpnDTO);

        Lpn createdLpn = lpnService.createLpn(lpn);

        LpnDTO responseDTO = toDTO(createdLpn);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LpnDTO> updateLpn(@PathVariable String id, @RequestBody LpnDTO lpnDTO) {
        Lpn lpn = toEntity(lpnDTO);
        lpn.setId(id);

        Lpn updatedLpn = lpnService.updateLpn(id, lpn);

        if (updatedLpn != null) {
            LpnDTO responseDTO = toDTO(updatedLpn);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLpn(@PathVariable String id) {
        boolean deleted = lpnService.deleteLpn(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Helper methods
    private LpnDTO toDTO(Lpn lpn) {
        LpnDTO dto = new LpnDTO();
        dto.setId(lpn.getId());
        dto.setName(lpn.getName());
        if (lpn.getLevel() != null) {
            dto.setLevelId(lpn.getLevel().getId());
        }
        dto.setWeightKg(lpn.getWeightKg());
        return dto;
    }

    private Lpn toEntity(LpnDTO dto) {
        Lpn lpn = new Lpn();
        lpn.setName(dto.getName());
        if (dto.getLevelId() != null) {
            Level level = levelRepository.findById(dto.getLevelId())
                    .orElseThrow(() -> new RuntimeException("The level id inserted as input is not valid"));
            lpn.setLevel(level);
        }
        // You can optionally set Level using its ID here if needed
        lpn.setWeightKg(dto.getWeightKg());
        return lpn;
    }
}
