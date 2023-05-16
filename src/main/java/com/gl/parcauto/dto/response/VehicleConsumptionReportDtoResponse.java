package com.gl.parcauto.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gl.parcauto.entity.DriverLicenseType;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gl.parcauto.entity.VehicleConsumptionReport} entity
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record VehicleConsumptionReportDtoResponse(Long id, String vehicleLicensePlate, DriverLicenseType vehicleType,
                                                  double totalFuelConsumed, double totalDistanceTravelled,
                                                  double fuelEfficiency) implements Serializable {
}