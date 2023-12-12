package com.gl.parcauto.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gl.parcauto.entity.Driver;
import com.gl.parcauto.entity.DriverLicenseType;
import com.gl.parcauto.entity.GrisCard;
import com.gl.parcauto.entity.InsuranceDuration;
import com.gl.parcauto.entity.Role;
import com.gl.parcauto.entity.Trip;
import com.gl.parcauto.entity.User;
import com.gl.parcauto.entity.Vehicle;
import com.gl.parcauto.entity.VehicleInsurance;
import com.gl.parcauto.entity.Vignette;
import com.gl.parcauto.exception.AppException;
import com.gl.parcauto.repository.DriverRepository;
import com.gl.parcauto.repository.TripRepository;
import com.gl.parcauto.repository.VehicleRepository;
import com.gl.parcauto.service.AvailabilityService;
import com.gl.parcauto.service.ConformityService;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

@ContextConfiguration(classes = {AffectationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AffectationServiceImplDiffblueTest {
    @Autowired
    private AffectationServiceImpl affectationServiceImpl;

    @MockBean
    private AvailabilityService availabilityService;

    @MockBean
    private ConformityService conformityService;

    @MockBean
    private DriverRepository driverRepository;

    @MockBean
    private TripRepository tripRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    /**
     * Method under test:
     * {@link AffectationServiceImpl#DriverToVehicle(String, String, Long)}
     */
    @Test
    void testDriverToVehicle() {
        when(conformityService.isConformVehicle(Mockito.<String>any(), Mockito.<DriverLicenseType>any())).thenReturn(true);
        when(conformityService.hasDriverLicense(Mockito.<String>any(), Mockito.<DriverLicenseType>any())).thenReturn(true);
        when(availabilityService.isAvailableVehicle(Mockito.<String>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any())).thenReturn(true);
        when(availabilityService.isAvailableDriver(Mockito.<String>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any())).thenReturn(true);

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

        Vehicle vehicle = new Vehicle();
        vehicle.setFuelConsumptionRecords(new HashSet<>());
        vehicle.setGrisCard(new GrisCard());
        vehicle.setLicensePlate("License Plate");
        vehicle.setTechnicalVisits(new HashSet<>());
        vehicle.setTripSet(new HashSet<>());
        vehicle.setType(DriverLicenseType.A);
        vehicle.setVehicleConsumptionReports(new HashSet<>());
        vehicle.setVehicleInsurance(new VehicleInsurance());
        vehicle.setVignette(new Vignette());
        vehicle.setYear(1);

        GrisCard grisCard = new GrisCard();
        grisCard.setActivatedDate(LocalDate.of(1970, 1, 1));
        grisCard.setId(1L);
        grisCard.setVehicle(vehicle);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setFuelConsumptionRecords(new HashSet<>());
        vehicle2.setGrisCard(new GrisCard());
        vehicle2.setLicensePlate("License Plate");
        vehicle2.setTechnicalVisits(new HashSet<>());
        vehicle2.setTripSet(new HashSet<>());
        vehicle2.setType(DriverLicenseType.A);
        vehicle2.setVehicleConsumptionReports(new HashSet<>());
        vehicle2.setVehicleInsurance(new VehicleInsurance());
        vehicle2.setVignette(new Vignette());
        vehicle2.setYear(1);

        VehicleInsurance vehicleInsurance = new VehicleInsurance();
        vehicleInsurance.setActivatedDate(LocalDate.of(1970, 1, 1));
        vehicleInsurance.setDuration(InsuranceDuration.MONTH);
        vehicleInsurance.setId(1L);
        vehicleInsurance.setVehicle(vehicle2);

        Vehicle vehicle3 = new Vehicle();
        vehicle3.setFuelConsumptionRecords(new HashSet<>());
        vehicle3.setGrisCard(new GrisCard());
        vehicle3.setLicensePlate("License Plate");
        vehicle3.setTechnicalVisits(new HashSet<>());
        vehicle3.setTripSet(new HashSet<>());
        vehicle3.setType(DriverLicenseType.A);
        vehicle3.setVehicleConsumptionReports(new HashSet<>());
        vehicle3.setVehicleInsurance(new VehicleInsurance());
        vehicle3.setVignette(new Vignette());
        vehicle3.setYear(1);

        Vignette vignette = new Vignette();
        vignette.setDeliveryDate(LocalDate.of(1970, 1, 1));
        vignette.setId(1L);
        vignette.setVehicle(vehicle3);

        Vehicle vehicle4 = new Vehicle();
        vehicle4.setFuelConsumptionRecords(new HashSet<>());
        vehicle4.setGrisCard(grisCard);
        vehicle4.setLicensePlate("License Plate");
        vehicle4.setTechnicalVisits(new HashSet<>());
        vehicle4.setTripSet(new HashSet<>());
        vehicle4.setType(DriverLicenseType.A);
        vehicle4.setVehicleConsumptionReports(new HashSet<>());
        vehicle4.setVehicleInsurance(vehicleInsurance);
        vehicle4.setVignette(vignette);
        vehicle4.setYear(1);

        Trip trip = new Trip();
        trip.setDriver(driver);
        trip.setEndDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        trip.setEndTrip("127.0.0.1");
        trip.setFuelConsumptionRecords(new HashSet<>());
        trip.setId(1L);
        trip.setStartDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        trip.setStartTrip("127.0.0.1");
        trip.setType(DriverLicenseType.A);
        trip.setVehicle(vehicle4);
        Optional<Trip> ofResult = Optional.of(trip);
        when(tripRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("Name");

        User user2 = new User();
        user2.setId(1L);
        user2.setPassword("iloveyou");
        user2.setRole(role2);
        user2.setUsername("janedoe");

        Driver driver2 = new Driver();
        driver2.setCin("Cin");
        driver2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        driver2.setDriverConsumptionReports(new HashSet<>());
        driver2.setDriverLicenses(new HashSet<>());
        driver2.setFirstName("Jane");
        driver2.setFuelConsumptionRecords(new HashSet<>());
        driver2.setLastName("Doe");
        driver2.setTrips(new HashSet<>());
        driver2.setUser(user2);
        driver2.setVacations(new HashSet<>());
        Optional<Driver> ofResult2 = Optional.of(driver2);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        when(vehicleRepository.findById(Mockito.<String>any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class, () -> affectationServiceImpl.DriverToVehicle("42", "42", 1L));
        verify(availabilityService).isAvailableDriver(Mockito.<String>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any());
        verify(availabilityService).isAvailableVehicle(Mockito.<String>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any());
        verify(conformityService).hasDriverLicense(Mockito.<String>any(), Mockito.<DriverLicenseType>any());
        verify(conformityService).isConformVehicle(Mockito.<String>any(), Mockito.<DriverLicenseType>any());
        verify(driverRepository).findById(Mockito.<String>any());
        verify(tripRepository).findById(Mockito.<Long>any());
        verify(vehicleRepository).findById(Mockito.<String>any());
    }
}
