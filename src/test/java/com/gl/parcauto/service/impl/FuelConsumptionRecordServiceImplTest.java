package com.gl.parcauto.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gl.parcauto.dto.request.FuelConsumptionRecordDtoRequest;
import com.gl.parcauto.entity.Driver;
import com.gl.parcauto.entity.DriverLicenseType;
import com.gl.parcauto.entity.FuelType;
import com.gl.parcauto.entity.GrisCard;
import com.gl.parcauto.entity.InsuranceDuration;
import com.gl.parcauto.entity.Role;
import com.gl.parcauto.entity.User;
import com.gl.parcauto.entity.Vehicle;
import com.gl.parcauto.entity.VehicleInsurance;
import com.gl.parcauto.entity.Vignette;
import com.gl.parcauto.exception.AppException;
import com.gl.parcauto.repository.DriverRepository;
import com.gl.parcauto.repository.FuelConsumptionRecordRepository;
import com.gl.parcauto.repository.TripRepository;
import com.gl.parcauto.repository.VehicleRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FuelConsumptionRecordServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FuelConsumptionRecordServiceImplTest {
    @MockBean
    private DriverRepository driverRepository;

    @MockBean
    private FuelConsumptionRecordRepository fuelConsumptionRecordRepository;

    @Autowired
    private FuelConsumptionRecordServiceImpl fuelConsumptionRecordServiceImpl;

    @MockBean
    private TripRepository tripRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    /**
     * Method under test:
     * {@link FuelConsumptionRecordServiceImpl#createConsumption(String, FuelConsumptionRecordDtoRequest)}
     */
    @Test
    void testCreateConsumption() {
        Role role = new Role();
        role.setId(1L);
        role.setName("Name");

        User user = new User();
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setRole(role);
        user.setUsername("janedoe");

        Driver driver = new Driver();
        driver.setCin("Cin");
        driver.setDateOfBirth(LocalDate.of(1970, 1, 1));
        driver.setDriverConsumptionReports(new HashSet<>());
        driver.setDriverLicenses(new HashSet<>());
        driver.setFirstName("Jane");
        driver.setFuelConsumptionRecords(new HashSet<>());
        driver.setLastName("Doe");
        driver.setTrips(new HashSet<>());
        driver.setUser(user);
        driver.setVacations(new HashSet<>());
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Vehicle> ofResult2 = Optional.of(vehicle4);
        when(vehicleRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        when(tripRepository.findById(Mockito.<Long>any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class,
                () -> fuelConsumptionRecordServiceImpl.createConsumption("Cin", new FuelConsumptionRecordDtoRequest(1L,
                        "Vehicle License Plate", "Driver Cin", 10.0d, FuelType.GASOLINE, 10.0d, 10.0d, "Refueling Location")));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(tripRepository).findById(Mockito.<Long>any());
        verify(vehicleRepository).findById(Mockito.<String>any());
    }
}
