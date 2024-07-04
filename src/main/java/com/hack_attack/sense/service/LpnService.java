package com.hack_attack.sense.service;

import com.hack_attack.sense.entity.Lpn;

import java.util.List;

public interface LpnService {

    List<Lpn> getAllLpns();

    Lpn getLpnById(String id);

    Lpn createLpn(Lpn lpn);

    Lpn updateLpn(String id, Lpn lpn);

    boolean deleteLpn(String id);
}
