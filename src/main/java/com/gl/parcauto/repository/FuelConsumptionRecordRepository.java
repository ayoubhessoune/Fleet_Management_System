package com.gl.parcauto.repository;

import com.gl.parcauto.entity.FuelConsumptionRecord;
import com.gl.parcauto.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FuelConsumptionRecordRepository extends JpaRepository<FuelConsumptionRecord, Long> {
    @Query(value = "SELECT r FROM FuelConsumptionRecord r where r.vehicle.licensePlate = :license_plate and " +
            "(r.trip.startDate >= :start_date and r.trip.endDate <= :end_date)")
    List<FuelConsumptionRecord> findFuelConsumptionRecordBetweenDatesByVehicle(
            @Param(value = "start_date") LocalDateTime start,
            @Param(value = "end_date") LocalDateTime end,
            @Param(value = "license_plate") String licensePlate);

    @Query(value = "SELECT DISTINCT r.vehicle.licensePlate FROM FuelConsumptionRecord r where " +
            "(r.trip.startDate >= :start_date and r.trip.endDate <= :end_date)")
    List<String> findVehiclesThatHaveFuelConsumptionRecordBetweenDates(
            @Param(value = "start_date") LocalDateTime start,
            @Param(value = "end_date") LocalDateTime end);

    @Query(value = "SELECT r FROM FuelConsumptionRecord r where r.driver.cin = :cin and " +
            "(r.trip.startDate >= :start_date and r.trip.endDate <= :end_date)")
    List<FuelConsumptionRecord> findFuelConsumptionRecordBetweenDatesByDriver(
            @Param(value = "start_date") LocalDateTime start,
            @Param(value = "end_date") LocalDateTime end,
            @Param(value = "cin") String cin);

    @Query(value = "SELECT DISTINCT r.driver.cin FROM FuelConsumptionRecord r where " +
            "(r.trip.startDate >= :start_date and r.trip.endDate <= :end_date)")
    List<String> findDriversThatHaveFuelConsumptionRecordBetweenDates(
            @Param(value = "start_date") LocalDateTime start,
            @Param(value = "end_date") LocalDateTime end);
}