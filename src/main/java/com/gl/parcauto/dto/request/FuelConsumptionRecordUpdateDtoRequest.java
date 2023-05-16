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
public record FuelConsumptionRecordUpdateDtoRequest(
        @Positive(message = "Kilometers traveled must be a positive number")
        Double kilometersTraveled,
        FuelType fuelType,
        @Positive(message = "Fuel consumed must be a positive number")
        Double fuelConsumed,
        @Positive(message = "Fuel cost must be a positive number")
        Double fuelCost,
        String refuelingLocation
) implements Serializable {
}