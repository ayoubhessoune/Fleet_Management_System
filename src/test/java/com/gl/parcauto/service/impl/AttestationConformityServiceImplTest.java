package com.gl.parcauto.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gl.parcauto.dto.request.AttestationConformityDtoRequest;
import com.gl.parcauto.dto.response.AttestationConformityDtoResponse;
import com.gl.parcauto.entity.AttestationConformity;
import com.gl.parcauto.entity.TechnicalVisit;
import com.gl.parcauto.entity.Vehicle;
import com.gl.parcauto.exception.AppException;
import com.gl.parcauto.exception.ResourceNotFoundException;
import com.gl.parcauto.repository.AttestationConformityRepository;
import com.gl.parcauto.repository.TechnicalVisitRepository;
import com.gl.parcauto.repository.VehicleRepository;

import java.time.LocalDate;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AttestationConformityServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AttestationConformityServiceImplTest {
    @MockBean
    private AttestationConformityRepository attestationConformityRepository;

    @Autowired
    private AttestationConformityServiceImpl attestationConformityServiceImpl;

    @MockBean
    private TechnicalVisitRepository technicalVisitRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    /**
     * Method under test: {@link AttestationConformityServiceImpl#createAttestationConformity(String, Long, AttestationConformityDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateAttestationConformity() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.entity.Vehicle.getLicensePlate()" because the return value of "com.gl.parcauto.entity.TechnicalVisit.getVehicle_technical_visit()" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateTechnicalVisitForVehicle(AttestationConformityServiceImpl.java:176)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.createAttestationConformity(AttestationConformityServiceImpl.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.of(new TechnicalVisit()));
        attestationConformityServiceImpl.createAttestationConformity("License Plate", 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something"));
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#createAttestationConformity(String, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testCreateAttestationConformity2() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        when(technicalVisitRepository.findById((Long) any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class,
                () -> attestationConformityServiceImpl.createAttestationConformity("License Plate", 1L,
                        new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#createAttestationConformity(String, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testCreateAttestationConformity3() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.empty());
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.of(new TechnicalVisit()));
        assertThrows(ResourceNotFoundException.class,
                () -> attestationConformityServiceImpl.createAttestationConformity("License Plate", 1L,
                        new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#createAttestationConformity(String, Long, AttestationConformityDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateAttestationConformity4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateTechnicalVisitForVehicle(AttestationConformityServiceImpl.java:176)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.createAttestationConformity(AttestationConformityServiceImpl.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(new Vehicle());
        Optional<TechnicalVisit> ofResult = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult);
        attestationConformityServiceImpl.createAttestationConformity("License Plate", 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something"));
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#createAttestationConformity(String, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testCreateAttestationConformity5() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));

        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle);
        Optional<TechnicalVisit> ofResult = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(AppException.class,
                () -> attestationConformityServiceImpl.createAttestationConformity("License Plate", 1L,
                        new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#createAttestationConformity(String, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testCreateAttestationConformity6() {
        when(attestationConformityRepository.save((AttestationConformity) any())).thenReturn(new AttestationConformity());
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.save((TechnicalVisit) any())).thenReturn(new TechnicalVisit());
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        AttestationConformityDtoResponse actualCreateAttestationConformityResult = attestationConformityServiceImpl
                .createAttestationConformity("License Plate", 1L,
                        new AttestationConformityDtoRequest("The characteristics of someone or something"));
        assertNull(actualCreateAttestationConformityResult.attestationId());
        assertEquals("License Plate", actualCreateAttestationConformityResult.vehicleLicensePlate());
        assertNull(actualCreateAttestationConformityResult.technicalVisitId());
        assertNull(actualCreateAttestationConformityResult.description());
        verify(attestationConformityRepository).save((AttestationConformity) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle, atLeast(1)).getLicensePlate();
        verify(technicalVisitRepository).save((TechnicalVisit) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#createAttestationConformity(String, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testCreateAttestationConformity7() {
        when(attestationConformityRepository.save((AttestationConformity) any())).thenReturn(new AttestationConformity());
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.save((TechnicalVisit) any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(AppException.class,
                () -> attestationConformityServiceImpl.createAttestationConformity("License Plate", 1L,
                        new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(attestationConformityRepository).save((AttestationConformity) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).save((TechnicalVisit) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#createAttestationConformity(String, Long, AttestationConformityDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateAttestationConformity8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.entity.AttestationConformity.getId()" because "attestation" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.mapToAttestationConformityDtoResponse(AttestationConformityServiceImpl.java:189)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.createAttestationConformity(AttestationConformityServiceImpl.java:61)
        //   See https://diff.blue/R013 to resolve this issue.

        when(attestationConformityRepository.save((AttestationConformity) any())).thenReturn(null);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.save((TechnicalVisit) any())).thenReturn(new TechnicalVisit());
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        attestationConformityServiceImpl.createAttestationConformity("License Plate", 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something"));
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#createAttestationConformity(String, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testCreateAttestationConformity9() {
        when(attestationConformityRepository.save((AttestationConformity) any())).thenReturn(new AttestationConformity());
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);
        when(technicalVisitRepository.save((TechnicalVisit) any())).thenReturn(new TechnicalVisit());
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> attestationConformityServiceImpl.createAttestationConformity("License Plate", 1L,
                        new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#createAttestationConformity(String, Long, AttestationConformityDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateAttestationConformity10() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.entity.AttestationConformity.setTechnicalVisit(com.gl.parcauto.entity.TechnicalVisit)" because "attestationConformity" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.createAttestationConformity(AttestationConformityServiceImpl.java:50)
        //   See https://diff.blue/R013 to resolve this issue.

        when(attestationConformityRepository.save((AttestationConformity) any())).thenReturn(new AttestationConformity());
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.save((TechnicalVisit) any())).thenReturn(new TechnicalVisit());
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        attestationConformityServiceImpl.createAttestationConformity("License Plate", 1L, null);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetById() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.entity.Vehicle.getLicensePlate()" because the return value of "com.gl.parcauto.entity.TechnicalVisit.getVehicle_technical_visit()" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateTechnicalVisitForVehicle(AttestationConformityServiceImpl.java:176)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.getById(AttestationConformityServiceImpl.java:77)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.of(new TechnicalVisit()));
        attestationConformityServiceImpl.getById("License Plate", 1L, 1L);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    void testGetById2() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        when(technicalVisitRepository.findById((Long) any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.getById("License Plate", 1L, 1L));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    void testGetById3() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.empty());
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.of(new TechnicalVisit()));
        assertThrows(ResourceNotFoundException.class,
                () -> attestationConformityServiceImpl.getById("License Plate", 1L, 1L));
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetById4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateTechnicalVisitForVehicle(AttestationConformityServiceImpl.java:176)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.getById(AttestationConformityServiceImpl.java:77)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(new Vehicle());
        Optional<TechnicalVisit> ofResult = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult);
        attestationConformityServiceImpl.getById("License Plate", 1L, 1L);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    void testGetById5() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));

        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle);
        Optional<TechnicalVisit> ofResult = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.getById("License Plate", 1L, 1L));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetById6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.entity.TechnicalVisit.getId()" because the return value of "com.gl.parcauto.entity.AttestationConformity.getTechnicalVisit()" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateAttestationForTechnicalVisit(AttestationConformityServiceImpl.java:181)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.getById(AttestationConformityServiceImpl.java:85)
        //   See https://diff.blue/R013 to resolve this issue.

        when(attestationConformityRepository.findById((Long) any())).thenReturn(Optional.of(new AttestationConformity()));
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        attestationConformityServiceImpl.getById("License Plate", 1L, 1L);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    void testGetById7() {
        when(attestationConformityRepository.findById((Long) any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.getById("License Plate", 1L, 1L));
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetById8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.equals(Object)" because the return value of "com.gl.parcauto.entity.TechnicalVisit.getId()" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateAttestationForTechnicalVisit(AttestationConformityServiceImpl.java:181)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.getById(AttestationConformityServiceImpl.java:85)
        //   See https://diff.blue/R013 to resolve this issue.

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(new TechnicalVisit());
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        attestationConformityServiceImpl.getById("License Plate", 1L, 1L);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    void testGetById9() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit1 = new TechnicalVisit();
        technicalVisit1.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.getById("License Plate", 1L, 1L));
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    void testGetById10() {
        when(attestationConformityRepository.findById((Long) any())).thenReturn(Optional.empty());
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(ResourceNotFoundException.class,
                () -> attestationConformityServiceImpl.getById("License Plate", 1L, 1L));
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    void testGetById11() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit1 = new TechnicalVisit();
        technicalVisit1.setId(1L);
        technicalVisit1.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        AttestationConformityDtoResponse actualById = attestationConformityServiceImpl.getById("License Plate", 1L, 1L);
        assertNull(actualById.attestationId());
        assertEquals("License Plate", actualById.vehicleLicensePlate());
        assertEquals(1L, actualById.technicalVisitId().longValue());
        assertNull(actualById.description());
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle, atLeast(1)).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getById(String, Long, Long)}
     */
    @Test
    void testGetById12() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> attestationConformityServiceImpl.getById("License Plate", 1L, 1L));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getAllAttestation(String, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllAttestation() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.entity.Vehicle.getLicensePlate()" because the return value of "com.gl.parcauto.entity.TechnicalVisit.getVehicle_technical_visit()" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateTechnicalVisitForVehicle(AttestationConformityServiceImpl.java:176)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.getAllAttestation(AttestationConformityServiceImpl.java:103)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.of(new TechnicalVisit()));
        attestationConformityServiceImpl.getAllAttestation("License Plate", 1L);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getAllAttestation(String, Long)}
     */
    @Test
    void testGetAllAttestation2() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        when(technicalVisitRepository.findById((Long) any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.getAllAttestation("License Plate", 1L));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getAllAttestation(String, Long)}
     */
    @Test
    void testGetAllAttestation3() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.empty());
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.of(new TechnicalVisit()));
        assertThrows(ResourceNotFoundException.class,
                () -> attestationConformityServiceImpl.getAllAttestation("License Plate", 1L));
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getAllAttestation(String, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllAttestation4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateTechnicalVisitForVehicle(AttestationConformityServiceImpl.java:176)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.getAllAttestation(AttestationConformityServiceImpl.java:103)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(new Vehicle());
        Optional<TechnicalVisit> ofResult = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult);
        attestationConformityServiceImpl.getAllAttestation("License Plate", 1L);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getAllAttestation(String, Long)}
     */
    @Test
    void testGetAllAttestation5() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));

        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle);
        Optional<TechnicalVisit> ofResult = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.getAllAttestation("License Plate", 1L));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getAllAttestation(String, Long)}
     */
    @Test
    void testGetAllAttestation6() {
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        assertTrue(attestationConformityServiceImpl.getAllAttestation("License Plate", 1L).isEmpty());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getAllAttestation(String, Long)}
     */
    @Test
    void testGetAllAttestation7() {
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");
        LocalDate visitDate = LocalDate.ofEpochDay(1L);
        AttestationConformity attestation = new AttestationConformity();

        TechnicalVisit technicalVisit = new TechnicalVisit(1L, visitDate, attestation, new Vehicle());
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        List<AttestationConformityDtoResponse> actualAllAttestation = attestationConformityServiceImpl
                .getAllAttestation("License Plate", 1L);
        assertEquals(1, actualAllAttestation.size());
        AttestationConformityDtoResponse getResult = actualAllAttestation.get(0);
        assertNull(getResult.attestationId());
        assertEquals("License Plate", getResult.vehicleLicensePlate());
        assertEquals(1L, getResult.technicalVisitId().longValue());
        assertNull(getResult.description());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle, atLeast(1)).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getAllAttestation(String, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllAttestation8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "com.gl.parcauto.repository.TechnicalVisitRepository.findById(Object)" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.getAllAttestation(AttestationConformityServiceImpl.java:98)
        //   See https://diff.blue/R013 to resolve this issue.

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(null);
        attestationConformityServiceImpl.getAllAttestation("License Plate", 1L);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getAllAttestation(String, Long)}
     */
    @Test
    void testGetAllAttestation9() {
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> attestationConformityServiceImpl.getAllAttestation("License Plate", 1L));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#getAllAttestation(String, Long)}
     */
    @Test
    void testGetAllAttestation10() {
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");
        AttestationConformity attestationConformity = mock(AttestationConformity.class);
        when(attestationConformity.getId()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(attestationConformity.getDescription())
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        LocalDate visitDate = LocalDate.ofEpochDay(1L);

        TechnicalVisit technicalVisit = new TechnicalVisit(1L, visitDate, attestationConformity, new Vehicle());
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(ResourceNotFoundException.class,
                () -> attestationConformityServiceImpl.getAllAttestation("License Plate", 1L));
        verify(vehicleRepository).findById((String) any());
        verify(vehicle, atLeast(1)).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
        verify(attestationConformity).getId();
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.entity.Vehicle.getLicensePlate()" because the return value of "com.gl.parcauto.entity.TechnicalVisit.getVehicle_technical_visit()" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateTechnicalVisitForVehicle(AttestationConformityServiceImpl.java:176)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.update(AttestationConformityServiceImpl.java:128)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.of(new TechnicalVisit()));
        attestationConformityServiceImpl.update("License Plate", 1L, 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something"));
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testUpdate2() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        when(technicalVisitRepository.findById((Long) any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.update("License Plate", 1L, 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testUpdate3() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.empty());
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.of(new TechnicalVisit()));
        assertThrows(ResourceNotFoundException.class, () -> attestationConformityServiceImpl.update("License Plate", 1L,
                1L, new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateTechnicalVisitForVehicle(AttestationConformityServiceImpl.java:176)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.update(AttestationConformityServiceImpl.java:128)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(new Vehicle());
        Optional<TechnicalVisit> ofResult = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult);
        attestationConformityServiceImpl.update("License Plate", 1L, 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something"));
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testUpdate5() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));

        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle);
        Optional<TechnicalVisit> ofResult = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.update("License Plate", 1L, 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.entity.TechnicalVisit.getId()" because the return value of "com.gl.parcauto.entity.AttestationConformity.getTechnicalVisit()" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateAttestationForTechnicalVisit(AttestationConformityServiceImpl.java:181)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.update(AttestationConformityServiceImpl.java:136)
        //   See https://diff.blue/R013 to resolve this issue.

        when(attestationConformityRepository.findById((Long) any())).thenReturn(Optional.of(new AttestationConformity()));
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        attestationConformityServiceImpl.update("License Plate", 1L, 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something"));
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testUpdate7() {
        when(attestationConformityRepository.findById((Long) any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.update("License Plate", 1L, 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.equals(Object)" because the return value of "com.gl.parcauto.entity.TechnicalVisit.getId()" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateAttestationForTechnicalVisit(AttestationConformityServiceImpl.java:181)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.update(AttestationConformityServiceImpl.java:136)
        //   See https://diff.blue/R013 to resolve this issue.

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(new TechnicalVisit());
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        attestationConformityServiceImpl.update("License Plate", 1L, 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something"));
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testUpdate9() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit1 = new TechnicalVisit();
        technicalVisit1.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.update("License Plate", 1L, 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testUpdate10() {
        when(attestationConformityRepository.findById((Long) any())).thenReturn(Optional.empty());
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(ResourceNotFoundException.class, () -> attestationConformityServiceImpl.update("License Plate", 1L,
                1L, new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testUpdate11() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.save((AttestationConformity) any())).thenReturn(new AttestationConformity());
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit1 = new TechnicalVisit();
        technicalVisit1.setId(1L);
        technicalVisit1.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        AttestationConformityDtoResponse actualUpdateResult = attestationConformityServiceImpl.update("License Plate", 1L,
                1L, new AttestationConformityDtoRequest("The characteristics of someone or something"));
        assertNull(actualUpdateResult.attestationId());
        assertEquals("License Plate", actualUpdateResult.vehicleLicensePlate());
        assertEquals(1L, actualUpdateResult.technicalVisitId().longValue());
        assertNull(actualUpdateResult.description());
        verify(attestationConformityRepository).save((AttestationConformity) any());
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle, atLeast(1)).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate12() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.entity.AttestationConformity.getId()" because "attestation" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.mapToAttestationConformityDtoResponse(AttestationConformityServiceImpl.java:189)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.update(AttestationConformityServiceImpl.java:145)
        //   See https://diff.blue/R013 to resolve this issue.

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.save((AttestationConformity) any())).thenReturn(null);
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit1 = new TechnicalVisit();
        technicalVisit1.setId(1L);
        technicalVisit1.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        attestationConformityServiceImpl.update("License Plate", 1L, 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something"));
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testUpdate13() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.save((AttestationConformity) any())).thenReturn(new AttestationConformity());
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> attestationConformityServiceImpl.update("License Plate", 1L,
                1L, new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testUpdate14() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.save((AttestationConformity) any())).thenReturn(new AttestationConformity());
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit1 = new TechnicalVisit();
        technicalVisit1.setId(1L);
        technicalVisit1.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        AttestationConformityDtoResponse actualUpdateResult = attestationConformityServiceImpl.update("License Plate", 1L,
                1L, new AttestationConformityDtoRequest(""));
        assertNull(actualUpdateResult.attestationId());
        assertEquals("License Plate", actualUpdateResult.vehicleLicensePlate());
        assertEquals(1L, actualUpdateResult.technicalVisitId().longValue());
        assertNull(actualUpdateResult.description());
        verify(attestationConformityRepository).save((AttestationConformity) any());
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle, atLeast(1)).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate15() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.dto.request.AttestationConformityDtoRequest.description()" because "request" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.update(AttestationConformityServiceImpl.java:138)
        //   See https://diff.blue/R013 to resolve this issue.

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.save((AttestationConformity) any())).thenReturn(new AttestationConformity());
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit1 = new TechnicalVisit();
        technicalVisit1.setId(1L);
        technicalVisit1.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        attestationConformityServiceImpl.update("License Plate", 1L, 1L, null);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#update(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testUpdate16() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.save((AttestationConformity) any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit1 = new TechnicalVisit();
        technicalVisit1.setId(1L);
        technicalVisit1.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.update("License Plate", 1L, 1L,
                new AttestationConformityDtoRequest("The characteristics of someone or something")));
        verify(attestationConformityRepository).save((AttestationConformity) any());
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDelete() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.entity.Vehicle.getLicensePlate()" because the return value of "com.gl.parcauto.entity.TechnicalVisit.getVehicle_technical_visit()" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateTechnicalVisitForVehicle(AttestationConformityServiceImpl.java:176)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.delete(AttestationConformityServiceImpl.java:161)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.of(new TechnicalVisit()));
        attestationConformityServiceImpl.delete("License Plate", 1L, 1L);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    void testDelete2() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        when(technicalVisitRepository.findById((Long) any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.delete("License Plate", 1L, 1L));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    void testDelete3() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.empty());
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.of(new TechnicalVisit()));
        assertThrows(ResourceNotFoundException.class,
                () -> attestationConformityServiceImpl.delete("License Plate", 1L, 1L));
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDelete4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "str" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateTechnicalVisitForVehicle(AttestationConformityServiceImpl.java:176)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.delete(AttestationConformityServiceImpl.java:161)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(new Vehicle());
        Optional<TechnicalVisit> ofResult = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult);
        attestationConformityServiceImpl.delete("License Plate", 1L, 1L);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    void testDelete5() {
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));

        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle);
        Optional<TechnicalVisit> ofResult = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.delete("License Plate", 1L, 1L));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDelete6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.entity.TechnicalVisit.getId()" because the return value of "com.gl.parcauto.entity.AttestationConformity.getTechnicalVisit()" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateAttestationForTechnicalVisit(AttestationConformityServiceImpl.java:181)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.delete(AttestationConformityServiceImpl.java:169)
        //   See https://diff.blue/R013 to resolve this issue.

        when(attestationConformityRepository.findById((Long) any())).thenReturn(Optional.of(new AttestationConformity()));
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        attestationConformityServiceImpl.delete("License Plate", 1L, 1L);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    void testDelete7() {
        when(attestationConformityRepository.findById((Long) any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.delete("License Plate", 1L, 1L));
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDelete8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Long.equals(Object)" because the return value of "com.gl.parcauto.entity.TechnicalVisit.getId()" is null
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.validateAttestationForTechnicalVisit(AttestationConformityServiceImpl.java:181)
        //       at com.gl.parcauto.service.impl.AttestationConformityServiceImpl.delete(AttestationConformityServiceImpl.java:169)
        //   See https://diff.blue/R013 to resolve this issue.

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(new TechnicalVisit());
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        attestationConformityServiceImpl.delete("License Plate", 1L, 1L);
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    void testDelete9() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit1 = new TechnicalVisit();
        technicalVisit1.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.delete("License Plate", 1L, 1L));
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    void testDelete10() {
        when(attestationConformityRepository.findById((Long) any())).thenReturn(Optional.empty());
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult1 = Optional.of(technicalVisit);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult1);
        assertThrows(ResourceNotFoundException.class,
                () -> attestationConformityServiceImpl.delete("License Plate", 1L, 1L));
        verify(attestationConformityRepository).findById((Long) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    void testDelete11() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        doNothing().when(attestationConformityRepository).delete((AttestationConformity) any());
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit1 = new TechnicalVisit();
        technicalVisit1.setId(1L);
        technicalVisit1.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        attestationConformityServiceImpl.delete("License Plate", 1L, 1L);
        verify(attestationConformityRepository).findById((Long) any());
        verify(attestationConformityRepository).delete((AttestationConformity) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    void testDelete12() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        doNothing().when(attestationConformityRepository).delete((AttestationConformity) any());
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> attestationConformityServiceImpl.delete("License Plate", 1L, 1L));
        verify(vehicleRepository).findById((String) any());
        verify(technicalVisitRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AttestationConformityServiceImpl#delete(String, Long, Long)}
     */
    @Test
    void testDelete13() {
        TechnicalVisit technicalVisit = new TechnicalVisit();
        technicalVisit.setId(1L);

        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setTechnicalVisit(technicalVisit);
        Optional<AttestationConformity> ofResult = Optional.of(attestationConformity);
        doThrow(new AppException(HttpStatus.CONTINUE, "An error occurred")).when(attestationConformityRepository)
                .delete((AttestationConformity) any());
        when(attestationConformityRepository.findById((Long) any())).thenReturn(ofResult);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getLicensePlate()).thenReturn("License Plate");
        Optional<Vehicle> ofResult1 = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult1);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("License Plate");

        TechnicalVisit technicalVisit1 = new TechnicalVisit();
        technicalVisit1.setId(1L);
        technicalVisit1.setVehicle_technical_visit(vehicle1);
        Optional<TechnicalVisit> ofResult2 = Optional.of(technicalVisit1);
        when(technicalVisitRepository.findById((Long) any())).thenReturn(ofResult2);
        assertThrows(AppException.class, () -> attestationConformityServiceImpl.delete("License Plate", 1L, 1L));
        verify(attestationConformityRepository).findById((Long) any());
        verify(attestationConformityRepository).delete((AttestationConformity) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
        verify(technicalVisitRepository).findById((Long) any());
    }
}

