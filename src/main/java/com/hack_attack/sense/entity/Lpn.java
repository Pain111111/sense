package com.hack_attack.sense.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lpns")
@Data
public class Lpn {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    @DBRef
    private Level level;

    private long weightKg;

}
