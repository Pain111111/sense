package com.hack_attack.sense.controller;

import com.hack_attack.sense.dto.VendorDTO;
import com.hack_attack.sense.entity.Vendor;
import com.hack_attack.sense.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        List<VendorDTO> dtos = vendors.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable String id) {
        Vendor vendor = vendorService.getVendorById(id);
        if (vendor != null) {
            return ResponseEntity.ok(toDTO(vendor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<VendorDTO> createVendor(@RequestBody VendorDTO vendorDTO) {
        Vendor vendor = toEntity(vendorDTO);

        Vendor createdVendor = vendorService.createVendor(vendor);

        VendorDTO responseDTO = toDTO(createdVendor);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable String id, @RequestBody VendorDTO vendorDTO) {
        Vendor vendor = toEntity(vendorDTO);
        vendor.setId(id);

        Vendor updatedVendor = vendorService.updateVendor(id, vendor);

        if (updatedVendor != null) {
            VendorDTO responseDTO = toDTO(updatedVendor);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable String id) {
        boolean deleted = vendorService.deleteVendor(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Helper methods
    private VendorDTO toDTO(Vendor vendor) {
        VendorDTO dto = new VendorDTO();
        dto.setId(vendor.getId());
        dto.setName(vendor.getName());
        dto.setEmail(vendor.getEmail());
        dto.setPhone(vendor.getPhone());
        return dto;
    }

    private Vendor toEntity(VendorDTO dto) {
        Vendor vendor = new Vendor();
        vendor.setName(dto.getName());
        vendor.setEmail(dto.getEmail());
        vendor.setPhone(dto.getPhone());
        return vendor;
    }
}
