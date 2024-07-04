package com.hack_attack.sense.service.impl;

import com.hack_attack.sense.entity.Vendor;
import com.hack_attack.sense.repository.VendorRepository;
import com.hack_attack.sense.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendorById(String id) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        return optionalVendor.orElse(null);
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(String id, Vendor vendor) {
        Optional<Vendor> optionalExistingVendor = vendorRepository.findById(id);
        if (optionalExistingVendor.isPresent()) {
            vendor.setId(id); // Ensure the ID is set for update
            return vendorRepository.save(vendor);
        }
        return null;
    }

    @Override
    public boolean deleteVendor(String id) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (optionalVendor.isPresent()) {
            vendorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
