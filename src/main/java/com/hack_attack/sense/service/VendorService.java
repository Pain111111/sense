package com.hack_attack.sense.service;
import com.hack_attack.sense.entity.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> getAllVendors();

    Vendor getVendorById(String id);

    Vendor createVendor(Vendor vendor);

    Vendor updateVendor(String id, Vendor vendor);

    boolean deleteVendor(String id);
}
