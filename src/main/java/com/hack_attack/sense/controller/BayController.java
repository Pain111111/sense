package com.hack_attack.sense.controller;

import com.hack_attack.sense.dto.BayDTO;
import com.hack_attack.sense.entity.Bay;
import com.hack_attack.sense.service.BayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bays")
public class BayController {

    private final BayService bayService;

    @Autowired
    public BayController(BayService bayService) {
        this.bayService = bayService;
    }

    @GetMapping
    public ResponseEntity<List<BayDTO>> getAllBays() {
        List<Bay> bays = bayService.getAllBays();
        List<BayDTO> dtos = bays.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BayDTO> getBayById(@PathVariable String id) {
        Bay bay = bayService.getBayById(id);
        if (bay != null) {
            return ResponseEntity.ok(toDTO(bay));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BayDTO> createBay(@RequestBody BayDTO bayDTO) {
        Bay bay = toEntity(bayDTO);

        Bay createdBay = bayService.createBay(bay);

        BayDTO responseDTO = toDTO(createdBay);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BayDTO> updateBay(@PathVariable String id, @RequestBody BayDTO bayDTO) {
        Bay bay = toEntity(bayDTO);
        bay.setId(id);

        Bay updatedBay = bayService.updateBay(id, bay);

        if (updatedBay != null) {
            BayDTO responseDTO = toDTO(updatedBay);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBay(@PathVariable String id) {
        boolean deleted = bayService.deleteBay(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Helper methods
    private BayDTO toDTO(Bay bay) {
        BayDTO dto = new BayDTO();
        dto.setId(bay.getId());
        dto.setName(bay.getName());
        if (bay.getVendor() != null) {
            dto.setVendorId(bay.getVendor().getId());
        }
        if (bay.getAisle() != null) {
            dto.setAisleId(bay.getAisle().getId());
        }
        return dto;
    }

    private Bay toEntity(BayDTO dto) {
        Bay bay = new Bay();
        bay.setName(dto.getName());
        // You can optionally set Vendor and Aisle using their IDs here if needed
        return bay;
    }
}
