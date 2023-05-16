package com.gl.parcauto.service;

import com.gl.parcauto.dto.response.DriverConsumptionReportDtoResponse;
import com.gl.parcauto.dto.response.VehicleConsumptionReportDtoResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsumptionReportService {
    VehicleConsumptionReportDtoResponse generateConsumptionReportByVehicle(
            LocalDateTime start, LocalDateTime end, String licensePlate);
    List<VehicleConsumptionReportDtoResponse> generateConsumptionReportForVehicles(LocalDateTime start, LocalDateTime end);
    VehicleConsumptionReportDtoResponse getMostEconomicalVehicle(LocalDateTime startDate, LocalDateTime endDate);
    VehicleConsumptionReportDtoResponse getMostConsumptiveVehicle(LocalDateTime startDate, LocalDateTime endDate);
    DriverConsumptionReportDtoResponse generateConsumptionReportByDriver(
            LocalDateTime start, LocalDateTime end, String cin);
    List<DriverConsumptionReportDtoResponse> generateConsumptionReportForDrivers(LocalDateTime start, LocalDateTime end);
    DriverConsumptionReportDtoResponse getMostEconomicalDriver(LocalDateTime startDate, LocalDateTime endDate);
    DriverConsumptionReportDtoResponse getMostConsumptiveDriver(LocalDateTime startDate, LocalDateTime endDate);
//    TripConsumptionReportDtoResponse generateConsumptionReportByTrip(Long tripId);
//    List<TripConsumptionReportDtoResponse> generateConsumptionReportForTrips();

}
