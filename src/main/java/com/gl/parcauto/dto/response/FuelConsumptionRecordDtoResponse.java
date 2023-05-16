package com.gl.parcauto.dto.response;

import com.gl.parcauto.entity.DriverLicenseType;
import com.gl.parcauto.entity.FuelType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.gl.parcauto.entity.FuelConsumptionRecord} entity
 */
public record FuelConsumptionRecordDtoResponse(Long id, Long tripId, LocalDateTime tripStartDate,
                                               LocalDateTime tripEndDate, String tripStartTrip, String tripEndTrip,
                                               DriverLicenseType tripType, String vehicleLicensePlate, String driverCin,
                                               FuelType fuelType, double fuelConsumed, double fuelCost,
                                               String refuelingLocation) implements Serializable {
}