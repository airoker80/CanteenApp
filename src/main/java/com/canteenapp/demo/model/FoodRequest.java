package com.canteenapp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "request")
public class FoodRequest {

    @Id
    String uri;

    String foodName;

    String employName;
}
