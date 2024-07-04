package com.hack_attack.model;

import lombok.Data;

import java.util.List;

@Data
public class LevelDetailsResponse {
    private String id;
    private Double ripenessValue;
    private String aisle;
    private String bay;
    private String level;
    private String color;
    private String vendor;
    private String image;
    private String quantity;
    private String fnvType;
    private List<LpnDetailsResponse> hu;
}