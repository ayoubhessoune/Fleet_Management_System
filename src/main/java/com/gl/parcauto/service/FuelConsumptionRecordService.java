package com.gl.parcauto.service;

import com.gl.parcauto.dto.request.FuelConsumptionRecordDtoRequest;
import com.gl.parcauto.dto.request.FuelConsumptionRecordUpdateDtoRequest;
import com.gl.parcauto.dto.response.FuelConsumptionRecordDtoResponse;

import java.util.List;

public interface FuelConsumptionRecordService {
    FuelConsumptionRecordDtoResponse createConsumption(String cin, FuelConsumptionRecordDtoRequest request);
    FuelConsumptionRecordDtoResponse getConsumptionDriverById(String cin, Long recordId);
    List<FuelConsumptionRecordDtoResponse> getConsumptionByDriver(String cin);
    List<FuelConsumptionRecordDtoResponse> getConsumptionByVehicle(String licensePlate);
    List<FuelConsumptionRecordDtoResponse> getAllConsumption();
    FuelConsumptionRecordDtoResponse updateConsumptionDriverById(String cin, Long recordId, FuelConsumptionRecordUpdateDtoRequest request);
    FuelConsumptionRecordDtoResponse getConsumptionById(Long consumptionId);
    void deleteConsumptionById(Long consumptionId);
}
