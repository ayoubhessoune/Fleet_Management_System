package com.gl.parcauto.mapper;

import com.gl.parcauto.dto.request.FuelConsumptionRecordDtoRequest;
import com.gl.parcauto.dto.response.FuelConsumptionRecordDtoResponse;
import com.gl.parcauto.entity.FuelConsumptionRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FuelConsumptionRecordMapper {
    FuelConsumptionRecordMapper INSTANCE = Mappers.getMapper(FuelConsumptionRecordMapper.class);
    FuelConsumptionRecord toEntity(FuelConsumptionRecordDtoRequest fuelConsumptionRecordDtoRequest);
    @Mappings({
            @Mapping(target = "tripId", source = "fuelConsumptionRecord.trip.id"),
            @Mapping(target = "tripStartDate", source = "fuelConsumptionRecord.trip.startDate"),
            @Mapping(target = "tripEndDate", source = "fuelConsumptionRecord.trip.endDate"),
            @Mapping(target = "tripStartTrip", source = "fuelConsumptionRecord.trip.startTrip"),
            @Mapping(target = "tripEndTrip", source = "fuelConsumptionRecord.trip.endTrip"),
            @Mapping(target = "tripType", source = "fuelConsumptionRecord.trip.type"),
            @Mapping(target = "vehicleLicensePlate", source = "fuelConsumptionRecord.vehicle.licensePlate"),
            @Mapping(target = "driverCin", source = "fuelConsumptionRecord.driver.cin"),
    })
    FuelConsumptionRecordDtoResponse toDto(FuelConsumptionRecord fuelConsumptionRecord);
}
