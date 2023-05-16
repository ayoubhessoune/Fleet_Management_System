package com.gl.parcauto.controller;

import com.gl.parcauto.dto.response.DriverConsumptionReportDtoResponse;
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
public class DriverConsumptionReportController {
    private final ConsumptionReportService consumptionReportService;
    @GetMapping("/drivers/{cin}/consumption-report")
    public ResponseEntity<DriverConsumptionReportDtoResponse> generateConsumptionReportByDriver(
            @PathVariable(name = "cin") String cin,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate) {
        DriverConsumptionReportDtoResponse report =
                consumptionReportService.generateConsumptionReportByDriver(startDate, endDate, cin);
        return ResponseEntity.ok(report);
    }
    @GetMapping("/drivers/consumption-reports")
    public ResponseEntity<List<DriverConsumptionReportDtoResponse>> generateConsumptionReportForDrivers(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate) {
        List<DriverConsumptionReportDtoResponse> reports =
                consumptionReportService.generateConsumptionReportForDrivers(startDate, endDate);
        return ResponseEntity.ok(reports);
    }
    @GetMapping("/drivers/most-economical-driver")
    public ResponseEntity<DriverConsumptionReportDtoResponse> getMostEconomicalDriver(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate) {
        DriverConsumptionReportDtoResponse report = consumptionReportService.getMostEconomicalDriver(startDate, endDate);
        return ResponseEntity.ok(report);
    }
    @GetMapping("/drivers/most-consumptive-driver")
    public ResponseEntity<DriverConsumptionReportDtoResponse> getMostConsumptiveDriver(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate) {
        DriverConsumptionReportDtoResponse report = consumptionReportService.getMostConsumptiveDriver(startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
