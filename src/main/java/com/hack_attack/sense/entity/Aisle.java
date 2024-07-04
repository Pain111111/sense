package com.hack_attack.sense.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "aisles")
@Data
public class Aisle {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

}
