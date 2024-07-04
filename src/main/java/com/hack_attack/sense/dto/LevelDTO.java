package com.hack_attack.sense.dto;

import com.hack_attack.sense.enums.RipenessColor;
import lombok.Data;

@Data
public class LevelDTO {
    private String id;
    private String name;
    private String bayId;

    private Double ripenessValue;

    private RipenessColor color;

    private String fnvType;

    private byte[] image;
}
