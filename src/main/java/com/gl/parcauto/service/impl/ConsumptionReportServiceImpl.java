package com.gl.parcauto.service.impl;

import com.gl.parcauto.dto.response.DriverConsumptionReportDtoResponse;
import com.gl.parcauto.dto.response.VehicleConsumptionReportDtoResponse;
import com.gl.parcauto.entity.*;
import com.gl.parcauto.exception.ResourceNotFoundException;
import com.gl.parcauto.mapper.DriverConsumptionReportMapper;
import com.gl.parcauto.mapper.VehicleConsumptionReportMapper;
import com.gl.parcauto.repository.*;
import com.gl.parcauto.service.ConsumptionReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ConsumptionReportServiceImpl implements ConsumptionReportService {
    private final VehicleConsumptionReportRepository vehicleConsumptionReportRepository;
    private final DriverConsumptionReportRepository driverConsumptionReportRepository;
    private final FuelConsumptionRecordRepository fuelConsumptionRecordRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    @Override
    @Transactional
    public VehicleConsumptionReportDtoResponse generateConsumptionReportByVehicle(LocalDateTime start, LocalDateTime end, String licensePlate) {
        VehicleConsumptionReport report = reportOfVehicle(start, end, licensePlate);

        // Save vehicle consumption report in database
        VehicleConsumptionReport saved = vehicleConsumptionReportRepository.save(report);

        return VehicleConsumptionReportMapper.INSTANCE.toDto(saved);
    }

    @Override
    public List<VehicleConsumptionReportDtoResponse> generateConsumptionReportForVehicles(LocalDateTime start, LocalDateTime end) {
        List<VehicleConsumptionReportDtoResponse> reports = new ArrayList<>();
        // Get vehicles that have fuel consumption records between start and end date
        List<String> licensePlates =
                fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates(start, end);

        for(String licensePlate : licensePlates) {
            reports.add(
                    VehicleConsumptionReportMapper.INSTANCE.toDto(reportOfVehicle(start, end, licensePlate))
            );
        }

        return reports;
    }

    @Override
    public VehicleConsumptionReportDtoResponse getMostEconomicalVehicle(LocalDateTime startDate, LocalDateTime endDate) {
        List<VehicleConsumptionReportDtoResponse> responses = generateConsumptionReportForVehicles(startDate, endDate);
        return Collections.min(responses, Comparator.comparing(VehicleConsumptionReportDtoResponse::fuelEfficiency));
    }

    @Override
    public VehicleConsumptionReportDtoResponse getMostConsumptiveVehicle(LocalDateTime startDate, LocalDateTime endDate) {
        List<VehicleConsumptionReportDtoResponse> responses = generateConsumptionReportForVehicles(startDate, endDate);
        return Collections.max(responses, Comparator.comparing(VehicleConsumptionReportDtoResponse::fuelEfficiency));
    }

    @Override
    @Transactional
    public DriverConsumptionReportDtoResponse generateConsumptionReportByDriver(LocalDateTime start, LocalDateTime end, String cin) {
        DriverConsumptionReport report = reportOfDriver(start, end, cin);

        // Save vehicle consumption report in database
        DriverConsumptionReport saved = driverConsumptionReportRepository.save(report);

        return DriverConsumptionReportMapper.INSTANCE.toDto(saved);

    }

    @Override
    public List<DriverConsumptionReportDtoResponse> generateConsumptionReportForDrivers(LocalDateTime start, LocalDateTime end) {
        List<DriverConsumptionReportDtoResponse> reports = new ArrayList<>();
        // Get drivers that have fuel consumption records between start and end date
        List<String> listOfCin =
                fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates(start, end);

        for(String cin : listOfCin) {
            reports.add(DriverConsumptionReportMapper.INSTANCE.toDto(reportOfDriver(start, end, cin)));
        }

        return reports;
    }

    @Override
    public DriverConsumptionReportDtoResponse getMostEconomicalDriver(LocalDateTime startDate, LocalDateTime endDate) {
        List<DriverConsumptionReportDtoResponse> responses = generateConsumptionReportForDrivers(startDate, endDate);
        return Collections.min(responses, Comparator.comparing(DriverConsumptionReportDtoResponse::fuelEfficiency));
    }

    @Override
    public DriverConsumptionReportDtoResponse getMostConsumptiveDriver(LocalDateTime startDate, LocalDateTime endDate) {
        List<DriverConsumptionReportDtoResponse> responses = generateConsumptionReportForDrivers(startDate, endDate);
        return Collections.max(responses, Comparator.comparing(DriverConsumptionReportDtoResponse::fuelEfficiency));
    }

    private DriverConsumptionReport reportOfDriver(LocalDateTime start, LocalDateTime end, String cin) {
        // Get driver from database
        Driver driver = driverRepository.findById(cin).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", cin)
        );

        // Get fuel consumption records of driver between start and end date
        List<FuelConsumptionRecord> records = fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver(
                start, end, driver.getCin());

        double totalFuelConsumed = 0.0;
        double totalDistanceTravelled = 0.0;
        double fuelEfficiency = 0.0;

        for(FuelConsumptionRecord record : records) {
            totalFuelConsumed += record.getFuelConsumed();
            totalDistanceTravelled += record.getKilometersTraveled();
        }

        if(totalDistanceTravelled != 0) {
            fuelEfficiency = totalFuelConsumed / totalDistanceTravelled;
        }

        DriverConsumptionReport report = new DriverConsumptionReport();
        report.setDriver(driver);
        report.setTotalFuelConsumed(totalFuelConsumed);
        report.setTotalDistanceTravelled(totalDistanceTravelled);
        report.setFuelEfficiency(fuelEfficiency);

        return report;
    }

    private VehicleConsumptionReport reportOfVehicle(LocalDateTime start, LocalDateTime end, String licensePlate) {
        // Get vehicle from database
        Vehicle vehicle = vehicleRepository.findById(licensePlate).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle", "id", licensePlate)
        );

        // Get fuel consumption records of vehicle between start and end date
        List<FuelConsumptionRecord> records = fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle(
                start, end, vehicle.getLicensePlate());

        double totalFuelConsumed = 0.0;
        double totalDistanceTravelled = 0.0;
        double fuelEfficiency = 0.0;

        for(FuelConsumptionRecord record : records) {
            totalFuelConsumed += record.getFuelConsumed();
            totalDistanceTravelled += record.getKilometersTraveled();
        }

        if(totalDistanceTravelled != 0) {
            fuelEfficiency = totalFuelConsumed / totalDistanceTravelled;
        }

        VehicleConsumptionReport report = new VehicleConsumptionReport();
        report.setVehicle(vehicle);
        report.setTotalFuelConsumed(totalFuelConsumed);
        report.setTotalDistanceTravelled(totalDistanceTravelled);
        report.setFuelEfficiency(fuelEfficiency);

        return report;
    }
}
