package com.gl.parcauto.controller;

import com.gl.parcauto.dto.request.FuelConsumptionRecordDtoRequest;
import com.gl.parcauto.dto.request.FuelConsumptionRecordUpdateDtoRequest;
import com.gl.parcauto.dto.response.FuelConsumptionRecordDtoResponse;
import com.gl.parcauto.service.FuelConsumptionRecordService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class FuelConsumptionController {
    private final FuelConsumptionRecordService recordService;
    @PostMapping("/drivers/{cin}/consumptions")
    public ResponseEntity<FuelConsumptionRecordDtoResponse> createConsumption(
            @PathVariable(name = "cin") String cin,
            @Valid @RequestBody FuelConsumptionRecordDtoRequest request) {
        FuelConsumptionRecordDtoResponse response = recordService.createConsumption(cin, request);
        return ResponseEntity.created(URI.create("/drivers/" + cin + "/consumptions/" + response.id()))
                .body(response);
    }
    @GetMapping("/drivers/{cin}/consumptions/{recordId}")
    public ResponseEntity<FuelConsumptionRecordDtoResponse> getConsumptionDriverById(
            @PathVariable(name = "cin") String cin, @PathVariable(name = "recordId") Long recordId) {
        FuelConsumptionRecordDtoResponse response = recordService.getConsumptionDriverById(cin, recordId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/drivers/{cin}/consumptions")
    public ResponseEntity<List<FuelConsumptionRecordDtoResponse>> getConsumptionByDriver(
            @PathVariable(name = "cin") String cin) {
        List<FuelConsumptionRecordDtoResponse> response = recordService.getConsumptionByDriver(cin);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/vehicles/{licensePlate}/consumptions")
    public ResponseEntity<List<FuelConsumptionRecordDtoResponse>> getConsumptionByVehicle(
            @PathVariable(name = "licensePlate") String licensePlate) {
        List<FuelConsumptionRecordDtoResponse> response = recordService.getConsumptionByVehicle(licensePlate);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/consumptions")
    public ResponseEntity<List<FuelConsumptionRecordDtoResponse>> getAllConsumption() {
        List<FuelConsumptionRecordDtoResponse> response = recordService.getAllConsumption();
        return ResponseEntity.ok(response);
    }
    @PutMapping("/drivers/{cin}/consumptions/{recordId}")
    public ResponseEntity<FuelConsumptionRecordDtoResponse> updateConsumptionDriverById(
            @PathVariable(name = "cin") String cin, @PathVariable(name = "recordId") Long recordId,
            @Valid @RequestBody FuelConsumptionRecordUpdateDtoRequest request) {
        FuelConsumptionRecordDtoResponse response = recordService.updateConsumptionDriverById(cin, recordId, request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/consumptions/{consumptionId}")
    public ResponseEntity<FuelConsumptionRecordDtoResponse> getConsumptionById(
            @PathVariable(name = "consumptionId") Long consumptionId) {
        FuelConsumptionRecordDtoResponse response = recordService.getConsumptionById(consumptionId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/consumptions/{consumptionId}")
    public ResponseEntity<String> deleteConsumptionById(
            @PathVariable(name = "consumptionId") Long consumptionId) {
        recordService.deleteConsumptionById(consumptionId);
        return ResponseEntity.ok("Fuel consumption record deleted successfully");
    }
}
