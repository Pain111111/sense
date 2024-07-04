package com.hack_attack.sense.controller;

import com.hack_attack.sense.dto.AisleDTO;
import com.hack_attack.sense.entity.Aisle;
import com.hack_attack.sense.service.AisleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/aisles")
public class AisleController {

    private final AisleService aisleService;

    @Autowired
    public AisleController(AisleService aisleService) {
        this.aisleService = aisleService;
    }

    @GetMapping
    public ResponseEntity<List<AisleDTO>> getAllAisles() {
        List<Aisle> aisles = aisleService.getAllAisles();
        List<AisleDTO> dtos = aisles.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AisleDTO> getAisleById(@PathVariable String id) {
        Aisle aisle = aisleService.getAisleById(id);
        if (aisle != null) {
            return ResponseEntity.ok(toDTO(aisle));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AisleDTO> createAisle(@RequestBody AisleDTO aisleDTO) {
        Aisle aisle = toEntity(aisleDTO);

        Aisle createdAisle = aisleService.createAisle(aisle);

        AisleDTO responseDTO = toDTO(createdAisle);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AisleDTO> updateAisle(@PathVariable String id, @RequestBody AisleDTO aisleDTO) {
        Aisle aisle = toEntity(aisleDTO);
        aisle.setId(id);

        Aisle updatedAisle = aisleService.updateAisle(id, aisle);

        if (updatedAisle != null) {
            AisleDTO responseDTO = toDTO(updatedAisle);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAisle(@PathVariable String id) {
        boolean deleted = aisleService.deleteAisle(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Helper methods
    private AisleDTO toDTO(Aisle aisle) {
        AisleDTO dto = new AisleDTO();
        dto.setId(aisle.getId());
        dto.setName(aisle.getName());
        return dto;
    }

    private Aisle toEntity(AisleDTO dto) {
        Aisle aisle = new Aisle();
        aisle.setName(dto.getName());
        return aisle;
    }
}
