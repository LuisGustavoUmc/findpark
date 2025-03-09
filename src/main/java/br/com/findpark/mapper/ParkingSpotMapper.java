package br.com.findpark.mapper;

import br.com.findpark.domain.ParkingSpot;
import br.com.findpark.dto.ParkingSpotDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParkingSpotMapper {
    ParkingSpotDTO toDto(ParkingSpot parkingSpot);
    ParkingSpot toEntity(ParkingSpotDTO parkingSpotDTO);
    List<ParkingSpotDTO> toDtoList(List<ParkingSpot> parkingSpotList);
}

