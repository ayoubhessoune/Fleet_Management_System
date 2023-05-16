package com.gl.parcauto.service.impl;

import com.gl.parcauto.dto.request.FuelConsumptionRecordDtoRequest;
import com.gl.parcauto.dto.request.FuelConsumptionRecordUpdateDtoRequest;
import com.gl.parcauto.dto.response.FuelConsumptionRecordDtoResponse;
import com.gl.parcauto.entity.Driver;
import com.gl.parcauto.entity.FuelConsumptionRecord;
import com.gl.parcauto.entity.Trip;
import com.gl.parcauto.entity.Vehicle;
import com.gl.parcauto.exception.AppException;
import com.gl.parcauto.exception.ResourceNotFoundException;
import com.gl.parcauto.mapper.FuelConsumptionRecordMapper;
import com.gl.parcauto.repository.DriverRepository;
import com.gl.parcauto.repository.FuelConsumptionRecordRepository;
import com.gl.parcauto.repository.TripRepository;
import com.gl.parcauto.repository.VehicleRepository;
import com.gl.parcauto.service.FuelConsumptionRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuelConsumptionRecordServiceImpl implements FuelConsumptionRecordService {
    private final FuelConsumptionRecordRepository consumptionRecordRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final TripRepository tripRepository;
    @Override
    @Transactional
    public FuelConsumptionRecordDtoResponse createConsumption(
            String cin, FuelConsumptionRecordDtoRequest request) {
        // Get driver from database
        Driver driver = driverRepository.findById(cin).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", cin)
        );

        // DTO to entity
        FuelConsumptionRecord fuelConsumptionRecord = FuelConsumptionRecordMapper.INSTANCE.toEntity(request);

        // Get vehicle from database
        Vehicle vehicle = vehicleRepository.findById(request.vehicleLicensePlate()).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle", "id", request.vehicleLicensePlate())
        );

        // Get trip from database
        Trip trip = tripRepository.findById(request.tripId()).orElseThrow(
                () -> new ResourceNotFoundException("Trip", "id", request.tripId().toString())
        );

        // Add driver to fuel consumption record
        fuelConsumptionRecord.setDriver(driver);

        // Add vehicle to fuel consumption record
        fuelConsumptionRecord.setVehicle(vehicle);

        // Add trip to fuel consumption record
        fuelConsumptionRecord.setTrip(trip);

        // Save fuel consumption record in database
        FuelConsumptionRecord saved = consumptionRecordRepository.save(fuelConsumptionRecord);

        return FuelConsumptionRecordMapper.INSTANCE.toDto(saved);
    }

    @Override
    public FuelConsumptionRecordDtoResponse getConsumptionDriverById(String cin, Long recordId) {
        // Get driver from database
        Driver driver = driverRepository.findById(cin).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", cin)
        );

        // Get fuel consumption record from database
        FuelConsumptionRecord record = consumptionRecordRepository.findById(recordId).orElseThrow(
                () -> new ResourceNotFoundException("Fuel consumption record", "id", recordId.toString())
        );

        // Check if fuel consumption record is for driver
        isFuelConsumptionRecordForDriver(driver, record);

        return FuelConsumptionRecordMapper.INSTANCE.toDto(record);
    }

    @Override
    public List<FuelConsumptionRecordDtoResponse> getConsumptionByDriver(String cin) {
        // Get driver from database
        Driver driver = driverRepository.findById(cin).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", cin)
        );

        // Get fuel consumption record list from driver
        return driver.getFuelConsumptionRecords().stream()
                .map(FuelConsumptionRecordMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FuelConsumptionRecordDtoResponse> getConsumptionByVehicle(String licensePlate) {
        // Get vehicle from database
        Vehicle vehicle = vehicleRepository.findById(licensePlate).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle", "id", licensePlate)
        );

        // Get fuel consumption record list from vehicle
        return vehicle.getFuelConsumptionRecords().stream()
                .map(FuelConsumptionRecordMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FuelConsumptionRecordDtoResponse> getAllConsumption() {
        return consumptionRecordRepository.findAll().stream()
                .map(FuelConsumptionRecordMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FuelConsumptionRecordDtoResponse updateConsumptionDriverById(String cin, Long recordId, FuelConsumptionRecordUpdateDtoRequest dto) {
        // Get driver from database
        Driver driver = driverRepository.findById(cin).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", cin)
        );

        // Get fuel consumption record from database
        FuelConsumptionRecord record = consumptionRecordRepository.findById(recordId).orElseThrow(
                () -> new ResourceNotFoundException("Fuel consumption record", "id", recordId.toString())
        );

        // Check if fuel consumption record is for driver
        isFuelConsumptionRecordForDriver(driver, record);

        if(dto.kilometersTraveled() != null) {
            record.setKilometersTraveled(dto.kilometersTraveled());
        }

        if(dto.fuelType() != null) {
            record.setFuelType(dto.fuelType());
        }

        if(dto.fuelConsumed() != null) {
            record.setFuelConsumed(dto.fuelConsumed());
        }

        if(dto.fuelCost() != null) {
            record.setFuelCost(dto.fuelCost());
        }

        if(StringUtils.hasText(dto.refuelingLocation())) {
            record.setRefuelingLocation(dto.refuelingLocation());
        }

        // Save fuel consumption record update in database
        FuelConsumptionRecord updated = consumptionRecordRepository.save(record);

        return FuelConsumptionRecordMapper.INSTANCE.toDto(updated);
    }

    @Override
    public FuelConsumptionRecordDtoResponse getConsumptionById(Long consumptionId) {
        // Get fuel consumption record from database
        FuelConsumptionRecord record = consumptionRecordRepository.findById(consumptionId).orElseThrow(
                () -> new ResourceNotFoundException("Fuel consumption record", "id", consumptionId.toString())
        );

        return FuelConsumptionRecordMapper.INSTANCE.toDto(record);
    }
    @Override
    public void deleteConsumptionById(Long consumptionId) {
        // Get fuel consumption record from database
        FuelConsumptionRecord record = consumptionRecordRepository.findById(consumptionId).orElseThrow(
                () -> new ResourceNotFoundException("Fuel consumption record", "id", consumptionId.toString())
        );

        // Delete fuel consumption from database
        consumptionRecordRepository.delete(record);
    }

    private void isFuelConsumptionRecordForDriver(Driver driver, FuelConsumptionRecord record) {
        if(!record.getDriver().getCin().equals(driver.getCin())) {
            throw new AppException(HttpStatus.NOT_FOUND, "Fuel consumption record not found for driver ID " + driver.getCin());
        }
    }

}
