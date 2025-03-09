package br.com.findpark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parking_spot")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpot {

    @Id
    private String id;
    private String status;
    private String type;
    private double price;
}
