package com.gl.parcauto.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

class VehicleConsumptionReportTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link VehicleConsumptionReport#VehicleConsumptionReport()}
     *   <li>{@link VehicleConsumptionReport#setFuelEfficiency(double)}
     *   <li>{@link VehicleConsumptionReport#setId(Long)}
     *   <li>{@link VehicleConsumptionReport#setTotalDistanceTravelled(double)}
     *   <li>{@link VehicleConsumptionReport#setTotalFuelConsumed(double)}
     *   <li>{@link VehicleConsumptionReport#setVehicle(Vehicle)}
     *   <li>{@link VehicleConsumptionReport#getFuelEfficiency()}
     *   <li>{@link VehicleConsumptionReport#getId()}
     *   <li>{@link VehicleConsumptionReport#getTotalDistanceTravelled()}
     *   <li>{@link VehicleConsumptionReport#getTotalFuelConsumed()}
     *   <li>{@link VehicleConsumptionReport#getVehicle()}
     * </ul>
     */
    @Test
    void testGetId() {
        DriverConsumptionReport report = new DriverConsumptionReport();
        report.setId(1L);
        assertEquals(1L, report.getId());
    }

    @Test
    void testSetId() {
        DriverConsumptionReport report = new DriverConsumptionReport();
        report.setId(1L);
        assertEquals(1L, report.getId());
    }

    @Test
    void testGetDriver() {
        DriverConsumptionReport report = new DriverConsumptionReport();
        Driver driver = new Driver();
        report.setDriver(driver);
        assertSame(driver, report.getDriver());
    }

    @Test
    void testSetDriver() {
        DriverConsumptionReport report = new DriverConsumptionReport();
        Driver driver = new Driver();
        report.setDriver(driver);
        assertSame(driver, report.getDriver());
    }

    @Test
    void testConstructor() {
        VehicleConsumptionReport actualVehicleConsumptionReport = new VehicleConsumptionReport();
        actualVehicleConsumptionReport.setFuelEfficiency(10.0d);
        actualVehicleConsumptionReport.setId(1L);
        actualVehicleConsumptionReport.setTotalDistanceTravelled(10.0d);
        actualVehicleConsumptionReport.setTotalFuelConsumed(10.0d);
        GrisCard grisCard = new GrisCard();
        grisCard.setActivatedDate(LocalDate.of(1970, 1, 1));
        grisCard.setId(1L);
        grisCard.setVehicle(new Vehicle());
        VehicleInsurance vehicleInsurance = new VehicleInsurance();
        vehicleInsurance.setActivatedDate(LocalDate.of(1970, 1, 1));
        vehicleInsurance.setDuration(InsuranceDuration.MONTH);
        vehicleInsurance.setId(1L);
        vehicleInsurance.setVehicle(new Vehicle());
        Vignette vignette = new Vignette();
        vignette.setDeliveryDate(LocalDate.of(1970, 1, 1));
        vignette.setId(1L);
        vignette.setVehicle(new Vehicle());
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumptionRecords(new HashSet<>());
        vehicle.setGrisCard(grisCard);
        vehicle.setLicensePlate("License Plate");
        vehicle.setTechnicalVisits(new HashSet<>());
        vehicle.setTripSet(new HashSet<>());
        vehicle.setType(DriverLicenseType.A);
        vehicle.setVehicleConsumptionReports(new HashSet<>());
        vehicle.setVehicleInsurance(vehicleInsurance);
        vehicle.setVignette(vignette);
        vehicle.setYear(1);
        GrisCard grisCard2 = new GrisCard();
        grisCard2.setActivatedDate(LocalDate.of(1970, 1, 1));
        grisCard2.setId(1L);
        grisCard2.setVehicle(vehicle);
        GrisCard grisCard3 = new GrisCard();
        grisCard3.setActivatedDate(LocalDate.of(1970, 1, 1));
        grisCard3.setId(1L);
        grisCard3.setVehicle(new Vehicle());
        VehicleInsurance vehicleInsurance2 = new VehicleInsurance();
        vehicleInsurance2.setActivatedDate(LocalDate.of(1970, 1, 1));
        vehicleInsurance2.setDuration(InsuranceDuration.MONTH);
        vehicleInsurance2.setId(1L);
        vehicleInsurance2.setVehicle(new Vehicle());
        Vignette vignette2 = new Vignette();
        vignette2.setDeliveryDate(LocalDate.of(1970, 1, 1));
        vignette2.setId(1L);
        vignette2.setVehicle(new Vehicle());
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setFuelConsumptionRecords(new HashSet<>());
        vehicle2.setGrisCard(grisCard3);
        vehicle2.setLicensePlate("License Plate");
        vehicle2.setTechnicalVisits(new HashSet<>());
        vehicle2.setTripSet(new HashSet<>());
        vehicle2.setType(DriverLicenseType.A);
        vehicle2.setVehicleConsumptionReports(new HashSet<>());
        vehicle2.setVehicleInsurance(vehicleInsurance2);
        vehicle2.setVignette(vignette2);
        vehicle2.setYear(1);
        VehicleInsurance vehicleInsurance3 = new VehicleInsurance();
        vehicleInsurance3.setActivatedDate(LocalDate.of(1970, 1, 1));
        vehicleInsurance3.setDuration(InsuranceDuration.MONTH);
        vehicleInsurance3.setId(1L);
        vehicleInsurance3.setVehicle(vehicle2);
        GrisCard grisCard4 = new GrisCard();
        grisCard4.setActivatedDate(LocalDate.of(1970, 1, 1));
        grisCard4.setId(1L);
        grisCard4.setVehicle(new Vehicle());
        VehicleInsurance vehicleInsurance4 = new VehicleInsurance();
        vehicleInsurance4.setActivatedDate(LocalDate.of(1970, 1, 1));
        vehicleInsurance4.setDuration(InsuranceDuration.MONTH);
        vehicleInsurance4.setId(1L);
        vehicleInsurance4.setVehicle(new Vehicle());
        Vignette vignette3 = new Vignette();
        vignette3.setDeliveryDate(LocalDate.of(1970, 1, 1));
        vignette3.setId(1L);
        vignette3.setVehicle(new Vehicle());
        Vehicle vehicle3 = new Vehicle();
        vehicle3.setFuelConsumptionRecords(new HashSet<>());
        vehicle3.setGrisCard(grisCard4);
        vehicle3.setLicensePlate("License Plate");
        vehicle3.setTechnicalVisits(new HashSet<>());
        vehicle3.setTripSet(new HashSet<>());
        vehicle3.setType(DriverLicenseType.A);
        vehicle3.setVehicleConsumptionReports(new HashSet<>());
        vehicle3.setVehicleInsurance(vehicleInsurance4);
        vehicle3.setVignette(vignette3);
        vehicle3.setYear(1);
        Vignette vignette4 = new Vignette();
        vignette4.setDeliveryDate(LocalDate.of(1970, 1, 1));
        vignette4.setId(1L);
        vignette4.setVehicle(vehicle3);
        Vehicle vehicle4 = new Vehicle();
        vehicle4.setFuelConsumptionRecords(new HashSet<>());
        vehicle4.setGrisCard(grisCard2);
        vehicle4.setLicensePlate("License Plate");
        vehicle4.setTechnicalVisits(new HashSet<>());
        vehicle4.setTripSet(new HashSet<>());
        vehicle4.setType(DriverLicenseType.A);
        vehicle4.setVehicleConsumptionReports(new HashSet<>());
        vehicle4.setVehicleInsurance(vehicleInsurance3);
        vehicle4.setVignette(vignette4);
        vehicle4.setYear(1);
        actualVehicleConsumptionReport.setVehicle(vehicle4);
        double actualFuelEfficiency = actualVehicleConsumptionReport.getFuelEfficiency();
        Long actualId = actualVehicleConsumptionReport.getId();
        double actualTotalDistanceTravelled = actualVehicleConsumptionReport.getTotalDistanceTravelled();
        double actualTotalFuelConsumed = actualVehicleConsumptionReport.getTotalFuelConsumed();
        Vehicle actualVehicle = actualVehicleConsumptionReport.getVehicle();
        assertEquals(10.0d, actualFuelEfficiency);
        assertEquals(10.0d, actualTotalDistanceTravelled);
        assertEquals(10.0d, actualTotalFuelConsumed);
        assertEquals(1L, actualId.longValue());
        assertSame(vehicle4, actualVehicle);
    }
}
