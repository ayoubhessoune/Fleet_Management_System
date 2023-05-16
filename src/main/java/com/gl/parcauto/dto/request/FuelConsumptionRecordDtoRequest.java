package com.gl.parcauto.dto.request;

import com.gl.parcauto.entity.FuelType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gl.parcauto.entity.FuelConsumptionRecord} entity
 */
public record FuelConsumptionRecordDtoRequest(
        @NotNull(message = "Trip ID is required")
        @Positive(message = "Trip ID must be a positive number")
        Long tripId,
        @NotNull(message = "License plate is required")
        @NotBlank(message = "License plate cannot be blank")
        String vehicleLicensePlate,
        @NotNull(message = "Cin is required")
        @NotBlank(message = "Cin should not be blank")
        @Size(min = 5, message = "Cin should at least contain {min} characters")
        String driverCin,
        @NotNull(message = "Kilometers traveled is required")
        @Positive(message = "Kilometers traveled must be a positive number")
        Double kilometersTraveled,
        @NotNull(message = "Fuel type is required")
        FuelType fuelType,
        @NotNull(message = "Fuel consumed is required")
        @Positive(message = "Fuel consumed must be a positive number")
        Double fuelConsumed,
        @NotNull(message = "fuel cost is required")
        @Positive(message = "Fuel cost must be a positive number")
        Double fuelCost,
        @NotNull(message = "Refueling location is required")
        @NotBlank(message = "Refueling location cannot be blank")
        String refuelingLocation
) implements Serializable { }