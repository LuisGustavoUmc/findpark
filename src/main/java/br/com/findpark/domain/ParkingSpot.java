package br.com.findpark.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parking_spot")
@Data
public class ParkingSpot {

    @Id
    private String id;
    private String status;
    private String type;
    private double price;
}
