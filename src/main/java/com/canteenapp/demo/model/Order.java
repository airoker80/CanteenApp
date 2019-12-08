package com.canteenapp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
@Data
@AllArgsConstructor
public class Order {
    @Id
    String orderId;

    String foodName;

    String employeeName;

    Status status;

    long createdOn;

    public enum Status{
        PENDING,
        IN_PROGRESS,
        READY
    }


}

