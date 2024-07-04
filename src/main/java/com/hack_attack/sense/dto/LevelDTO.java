package com.hack_attack.sense.dto;

import com.hack_attack.sense.enums.Item;
import lombok.Data;

@Data
public class LevelDTO {
    private String id;
    private String name;
    private String bayId;
    private Item item;
    private byte[] image;
}
