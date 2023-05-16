package com.gl.parcauto.controller;

import com.gl.parcauto.dto.response.VehicleConsumptionReportDtoResponse;
import com.gl.parcauto.service.ConsumptionReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class VehicleConsumptionReportController {
    private final ConsumptionReportService consumptionReportService;
    @GetMapping("/vehicles/{licensePlate}/consumption-report")
    public ResponseEntity<VehicleConsumptionReportDtoResponse> generateConsumptionReportByVehicle(
            @PathVariable(name = "licensePlate") String licensePlate,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate) {
        VehicleConsumptionReportDtoResponse report = consumptionReportService.generateConsumptionReportByVehicle(startDate, endDate, licensePlate);
        return ResponseEntity.ok(report);
    }
    @GetMapping("/vehicles/consumption-reports")
    public ResponseEntity<List<VehicleConsumptionReportDtoResponse>> generateConsumptionReportForVehicles(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate) {
        List<VehicleConsumptionReportDtoResponse> reports = consumptionReportService.generateConsumptionReportForVehicles(startDate, endDate);
        return ResponseEntity.ok(reports);
    }
    @GetMapping("/vehicles/most-economical-vehicle")
    public ResponseEntity<VehicleConsumptionReportDtoResponse> getMostEconomicalVehicle(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate) {
        VehicleConsumptionReportDtoResponse report = consumptionReportService.getMostEconomicalVehicle(startDate, endDate);
        return ResponseEntity.ok(report);
    }
    @GetMapping("/vehicles/most-consumptive-vehicle")
    public ResponseEntity<VehicleConsumptionReportDtoResponse> getMostConsumptiveVehicle(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate) {
        VehicleConsumptionReportDtoResponse report = consumptionReportService.getMostConsumptiveVehicle(startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
