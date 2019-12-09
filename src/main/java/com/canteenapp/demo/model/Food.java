package com.canteenapp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Document(collection = "food")
public class Food {

    @Id
    public String foodId;

    @Indexed(unique = true)
    public String foodName;

    public int foodPrice;

    public boolean isForToday;
}
