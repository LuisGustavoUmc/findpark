package br.com.findpark.dto;

import br.com.findpark.domain.ParkingSpot;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParkingSpotDTO(
        String id,
        @NotBlank(message = "Status cannot be blank") String status,
        @NotBlank(message = "Type cannot be blank") String type,
        @NotNull(message = "Price cannot be null") @Min(value = 0, message = "Price must be a positive number") Double price
) {

    public ParkingSpotDTO(ParkingSpot parkingSpot) {
        this(parkingSpot.getId(), parkingSpot.getStatus(), parkingSpot.getType(), parkingSpot.getPrice());
    }
}
