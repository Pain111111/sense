package com.hack_attack.sense.entity;

import com.hack_attack.sense.enums.Item;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "levels")
@Data
public class Level {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    @DBRef
    private Bay bay;

    private List<Lpn> lpns;

    private Item item;

    private byte[] image;

}
