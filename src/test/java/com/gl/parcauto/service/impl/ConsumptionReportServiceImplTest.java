package com.gl.parcauto.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gl.parcauto.dto.response.DriverConsumptionReportDtoResponse;
import com.gl.parcauto.dto.response.VehicleConsumptionReportDtoResponse;
import com.gl.parcauto.entity.Driver;
import com.gl.parcauto.entity.DriverConsumptionReport;
import com.gl.parcauto.entity.FuelConsumptionRecord;
import com.gl.parcauto.entity.Vehicle;
import com.gl.parcauto.entity.VehicleConsumptionReport;
import com.gl.parcauto.exception.ResourceNotFoundException;
import com.gl.parcauto.repository.DriverConsumptionReportRepository;
import com.gl.parcauto.repository.DriverRepository;
import com.gl.parcauto.repository.FuelConsumptionRecordRepository;
import com.gl.parcauto.repository.VehicleConsumptionReportRepository;
import com.gl.parcauto.repository.VehicleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ConsumptionReportServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ConsumptionReportServiceImplTest {
    @Autowired
    private ConsumptionReportServiceImpl consumptionReportServiceImpl;

    @MockBean
    private DriverConsumptionReportRepository driverConsumptionReportRepository;

    @MockBean
    private DriverRepository driverRepository;

    @MockBean
    private FuelConsumptionRecordRepository fuelConsumptionRecordRepository;

    @MockBean
    private VehicleConsumptionReportRepository vehicleConsumptionReportRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportByVehicle(LocalDateTime, LocalDateTime, String)}
     */
    @Test
    void testGenerateConsumptionReportByVehicle() {
        when(vehicleConsumptionReportRepository.save((VehicleConsumptionReport) any()))
                .thenReturn(new VehicleConsumptionReport());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        VehicleConsumptionReportDtoResponse actualGenerateConsumptionReportByVehicleResult = consumptionReportServiceImpl
                .generateConsumptionReportByVehicle(start, LocalDateTime.of(1, 1, 1, 1, 1), "License Plate");
        assertEquals(0.0d, actualGenerateConsumptionReportByVehicleResult.fuelEfficiency());
        assertNull(actualGenerateConsumptionReportByVehicleResult.vehicleType());
        assertNull(actualGenerateConsumptionReportByVehicleResult.vehicleLicensePlate());
        assertEquals(0.0d, actualGenerateConsumptionReportByVehicleResult.totalFuelConsumed());
        assertEquals(0.0d, actualGenerateConsumptionReportByVehicleResult.totalDistanceTravelled());
        assertNull(actualGenerateConsumptionReportByVehicleResult.id());
        verify(vehicleConsumptionReportRepository).save((VehicleConsumptionReport) any());
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportByVehicle(LocalDateTime, LocalDateTime, String)}
     */
    @Test
    void testGenerateConsumptionReportByVehicle2() {
        when(vehicleConsumptionReportRepository.save((VehicleConsumptionReport) any()))
                .thenReturn(new VehicleConsumptionReport());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class, () -> consumptionReportServiceImpl
                .generateConsumptionReportByVehicle(start, LocalDateTime.of(1, 1, 1, 1, 1), "License Plate"));
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportByVehicle(LocalDateTime, LocalDateTime, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateConsumptionReportByVehicle3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.gl.parcauto.exception.ResourceNotFoundException: Resource Name not found with Field Name : '42'
        //       at com.gl.parcauto.mapper.VehicleConsumptionReportMapperImpl.reportVehicleLicensePlate(VehicleConsumptionReportMapperImpl.java:45)
        //       at com.gl.parcauto.mapper.VehicleConsumptionReportMapperImpl.toDto(VehicleConsumptionReportMapperImpl.java:29)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.generateConsumptionReportByVehicle(ConsumptionReportServiceImpl.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleConsumptionReportRepository.save((VehicleConsumptionReport) any()))
                .thenReturn(mock(VehicleConsumptionReport.class));
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.generateConsumptionReportByVehicle(start, LocalDateTime.of(1, 1, 1, 1, 1),
                "License Plate");
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportByVehicle(LocalDateTime, LocalDateTime, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateConsumptionReportByVehicle4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Double.doubleValue()" because the return value of "com.gl.parcauto.entity.FuelConsumptionRecord.getFuelConsumed()" is null
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.reportOfVehicle(ConsumptionReportServiceImpl.java:150)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.generateConsumptionReportByVehicle(ConsumptionReportServiceImpl.java:29)
        //   See https://diff.blue/R013 to resolve this issue.

        when(vehicleConsumptionReportRepository.save((VehicleConsumptionReport) any()))
                .thenReturn(mock(VehicleConsumptionReport.class));
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        ArrayList<FuelConsumptionRecord> fuelConsumptionRecordList = new ArrayList<>();
        fuelConsumptionRecordList.add(new FuelConsumptionRecord());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(fuelConsumptionRecordList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.generateConsumptionReportByVehicle(start, LocalDateTime.of(1, 1, 1, 1, 1),
                "License Plate");
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportByVehicle(LocalDateTime, LocalDateTime, String)}
     */
    @Test
    void testGenerateConsumptionReportByVehicle5() {
        when(vehicleConsumptionReportRepository.save((VehicleConsumptionReport) any()))
                .thenReturn(mock(VehicleConsumptionReport.class));
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.empty());
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class, () -> consumptionReportServiceImpl
                .generateConsumptionReportByVehicle(start, LocalDateTime.of(1, 1, 1, 1, 1), "License Plate"));
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForVehicles(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForVehicles() {
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(new ArrayList<>());
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertTrue(
                consumptionReportServiceImpl.generateConsumptionReportForVehicles(start, LocalDateTime.of(1, 1, 1, 1, 1))
                        .isEmpty());
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForVehicles(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForVehicles2() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertEquals(1,
                consumptionReportServiceImpl.generateConsumptionReportForVehicles(start, LocalDateTime.of(1, 1, 1, 1, 1))
                        .size());
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForVehicles(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForVehicles3() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class, () -> consumptionReportServiceImpl
                .generateConsumptionReportForVehicles(start, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForVehicles(LocalDateTime, LocalDateTime)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateConsumptionReportForVehicles4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Double.doubleValue()" because the return value of "com.gl.parcauto.entity.FuelConsumptionRecord.getFuelConsumed()" is null
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.reportOfVehicle(ConsumptionReportServiceImpl.java:150)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.generateConsumptionReportForVehicles(ConsumptionReportServiceImpl.java:46)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");

        ArrayList<FuelConsumptionRecord> fuelConsumptionRecordList = new ArrayList<>();
        fuelConsumptionRecordList.add(new FuelConsumptionRecord());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(fuelConsumptionRecordList);
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.generateConsumptionReportForVehicles(start, LocalDateTime.of(1, 1, 1, 1, 1));
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForVehicles(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForVehicles5() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getType()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(vehicle.getLicensePlate()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class, () -> consumptionReportServiceImpl
                .generateConsumptionReportForVehicles(start, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForVehicles(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForVehicles6() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class, () -> consumptionReportServiceImpl
                .generateConsumptionReportForVehicles(start, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMostEconomicalVehicle() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at java.util.ArrayList$Itr.next(ArrayList.java:970)
        //       at java.util.Collections.min(Collections.java:640)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.getMostEconomicalVehicle(ConsumptionReportServiceImpl.java:56)
        //   See https://diff.blue/R013 to resolve this issue.

        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(new ArrayList<>());
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.getMostEconomicalVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostEconomicalVehicle2() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        VehicleConsumptionReportDtoResponse actualMostEconomicalVehicle = consumptionReportServiceImpl
                .getMostEconomicalVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
        assertEquals(0.0d, actualMostEconomicalVehicle.fuelEfficiency());
        assertNull(actualMostEconomicalVehicle.vehicleType());
        assertNull(actualMostEconomicalVehicle.vehicleLicensePlate());
        assertEquals(0.0d, actualMostEconomicalVehicle.totalFuelConsumed());
        assertEquals(0.0d, actualMostEconomicalVehicle.totalDistanceTravelled());
        assertNull(actualMostEconomicalVehicle.id());
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostEconomicalVehicle3() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostEconomicalVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMostEconomicalVehicle4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Double.doubleValue()" because the return value of "com.gl.parcauto.entity.FuelConsumptionRecord.getFuelConsumed()" is null
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.reportOfVehicle(ConsumptionReportServiceImpl.java:150)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.generateConsumptionReportForVehicles(ConsumptionReportServiceImpl.java:46)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.getMostEconomicalVehicle(ConsumptionReportServiceImpl.java:55)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");

        ArrayList<FuelConsumptionRecord> fuelConsumptionRecordList = new ArrayList<>();
        fuelConsumptionRecordList.add(new FuelConsumptionRecord());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(fuelConsumptionRecordList);
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.getMostEconomicalVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostEconomicalVehicle5() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getType()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(vehicle.getLicensePlate()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostEconomicalVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostEconomicalVehicle6() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostEconomicalVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMostConsumptiveVehicle() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at java.util.ArrayList$Itr.next(ArrayList.java:970)
        //       at java.util.Collections.max(Collections.java:713)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.getMostConsumptiveVehicle(ConsumptionReportServiceImpl.java:62)
        //   See https://diff.blue/R013 to resolve this issue.

        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(new ArrayList<>());
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.getMostConsumptiveVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostConsumptiveVehicle2() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        VehicleConsumptionReportDtoResponse actualMostConsumptiveVehicle = consumptionReportServiceImpl
                .getMostConsumptiveVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
        assertEquals(0.0d, actualMostConsumptiveVehicle.fuelEfficiency());
        assertNull(actualMostConsumptiveVehicle.vehicleType());
        assertNull(actualMostConsumptiveVehicle.vehicleLicensePlate());
        assertEquals(0.0d, actualMostConsumptiveVehicle.totalFuelConsumed());
        assertEquals(0.0d, actualMostConsumptiveVehicle.totalDistanceTravelled());
        assertNull(actualMostConsumptiveVehicle.id());
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostConsumptiveVehicle3() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostConsumptiveVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMostConsumptiveVehicle4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Double.doubleValue()" because the return value of "com.gl.parcauto.entity.FuelConsumptionRecord.getFuelConsumed()" is null
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.reportOfVehicle(ConsumptionReportServiceImpl.java:150)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.generateConsumptionReportForVehicles(ConsumptionReportServiceImpl.java:46)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.getMostConsumptiveVehicle(ConsumptionReportServiceImpl.java:61)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");

        ArrayList<FuelConsumptionRecord> fuelConsumptionRecordList = new ArrayList<>();
        fuelConsumptionRecordList.add(new FuelConsumptionRecord());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(fuelConsumptionRecordList);
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.of(new Vehicle()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.getMostConsumptiveVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostConsumptiveVehicle5() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getType()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(vehicle.getLicensePlate()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        Optional<Vehicle> ofResult = Optional.of(vehicle);
        when(vehicleRepository.findById((String) any())).thenReturn(ofResult);
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostConsumptiveVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
        verify(vehicle).getLicensePlate();
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostConsumptiveVehicle6() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByVehicle((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(vehicleRepository.findById((String) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostConsumptiveVehicle(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findVehiclesThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(vehicleRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportByDriver(LocalDateTime, LocalDateTime, String)}
     */
    @Test
    void testGenerateConsumptionReportByDriver() {
        when(driverConsumptionReportRepository.save((DriverConsumptionReport) any()))
                .thenReturn(new DriverConsumptionReport());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        DriverConsumptionReportDtoResponse actualGenerateConsumptionReportByDriverResult = consumptionReportServiceImpl
                .generateConsumptionReportByDriver(start, LocalDateTime.of(1, 1, 1, 1, 1), "Cin");
        assertNull(actualGenerateConsumptionReportByDriverResult.driverCin());
        assertEquals(0.0d, actualGenerateConsumptionReportByDriverResult.totalFuelConsumed());
        assertEquals(0.0d, actualGenerateConsumptionReportByDriverResult.totalDistanceTravelled());
        assertNull(actualGenerateConsumptionReportByDriverResult.id());
        assertEquals(0.0d, actualGenerateConsumptionReportByDriverResult.fuelEfficiency());
        verify(driverConsumptionReportRepository).save((DriverConsumptionReport) any());
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportByDriver(LocalDateTime, LocalDateTime, String)}
     */
    @Test
    void testGenerateConsumptionReportByDriver2() {
        when(driverConsumptionReportRepository.save((DriverConsumptionReport) any()))
                .thenReturn(new DriverConsumptionReport());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class, () -> consumptionReportServiceImpl
                .generateConsumptionReportByDriver(start, LocalDateTime.of(1, 1, 1, 1, 1), "Cin"));
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportByDriver(LocalDateTime, LocalDateTime, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateConsumptionReportByDriver3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.gl.parcauto.exception.ResourceNotFoundException: Resource Name not found with Field Name : '42'
        //       at com.gl.parcauto.mapper.DriverConsumptionReportMapperImpl.reportDriverCin(DriverConsumptionReportMapperImpl.java:42)
        //       at com.gl.parcauto.mapper.DriverConsumptionReportMapperImpl.toDto(DriverConsumptionReportMapperImpl.java:27)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.generateConsumptionReportByDriver(ConsumptionReportServiceImpl.java:73)
        //   See https://diff.blue/R013 to resolve this issue.

        when(driverConsumptionReportRepository.save((DriverConsumptionReport) any()))
                .thenReturn(mock(DriverConsumptionReport.class));
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.generateConsumptionReportByDriver(start, LocalDateTime.of(1, 1, 1, 1, 1), "Cin");
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportByDriver(LocalDateTime, LocalDateTime, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateConsumptionReportByDriver4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Double.doubleValue()" because the return value of "com.gl.parcauto.entity.FuelConsumptionRecord.getFuelConsumed()" is null
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.reportOfDriver(ConsumptionReportServiceImpl.java:118)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.generateConsumptionReportByDriver(ConsumptionReportServiceImpl.java:68)
        //   See https://diff.blue/R013 to resolve this issue.

        when(driverConsumptionReportRepository.save((DriverConsumptionReport) any()))
                .thenReturn(mock(DriverConsumptionReport.class));
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        ArrayList<FuelConsumptionRecord> fuelConsumptionRecordList = new ArrayList<>();
        fuelConsumptionRecordList.add(new FuelConsumptionRecord());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(fuelConsumptionRecordList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.generateConsumptionReportByDriver(start, LocalDateTime.of(1, 1, 1, 1, 1), "Cin");
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportByDriver(LocalDateTime, LocalDateTime, String)}
     */
    @Test
    void testGenerateConsumptionReportByDriver5() {
        when(driverConsumptionReportRepository.save((DriverConsumptionReport) any()))
                .thenReturn(mock(DriverConsumptionReport.class));
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(driverRepository.findById((String) any())).thenReturn(Optional.empty());
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class, () -> consumptionReportServiceImpl
                .generateConsumptionReportByDriver(start, LocalDateTime.of(1, 1, 1, 1, 1), "Cin"));
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForDrivers(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForDrivers() {
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(new ArrayList<>());
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertTrue(
                consumptionReportServiceImpl.generateConsumptionReportForDrivers(start, LocalDateTime.of(1, 1, 1, 1, 1))
                        .isEmpty());
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForDrivers(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForDrivers2() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        List<DriverConsumptionReportDtoResponse> actualGenerateConsumptionReportForDriversResult = consumptionReportServiceImpl
                .generateConsumptionReportForDrivers(start, LocalDateTime.of(1, 1, 1, 1, 1));
        assertEquals(1, actualGenerateConsumptionReportForDriversResult.size());
        DriverConsumptionReportDtoResponse getResult = actualGenerateConsumptionReportForDriversResult.get(0);
        assertNull(getResult.driverCin());
        assertEquals(0.0d, getResult.totalFuelConsumed());
        assertEquals(0.0d, getResult.totalDistanceTravelled());
        assertNull(getResult.id());
        assertEquals(0.0d, getResult.fuelEfficiency());
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForDrivers(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForDrivers3() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class, () -> consumptionReportServiceImpl
                .generateConsumptionReportForDrivers(start, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForDrivers(LocalDateTime, LocalDateTime)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateConsumptionReportForDrivers4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Double.doubleValue()" because the return value of "com.gl.parcauto.entity.FuelConsumptionRecord.getFuelConsumed()" is null
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.reportOfDriver(ConsumptionReportServiceImpl.java:118)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.generateConsumptionReportForDrivers(ConsumptionReportServiceImpl.java:85)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");

        ArrayList<FuelConsumptionRecord> fuelConsumptionRecordList = new ArrayList<>();
        fuelConsumptionRecordList.add(new FuelConsumptionRecord());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(fuelConsumptionRecordList);
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.generateConsumptionReportForDrivers(start, LocalDateTime.of(1, 1, 1, 1, 1));
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForDrivers(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForDrivers5() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        Driver driver = mock(Driver.class);
        when(driver.getCin()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class, () -> consumptionReportServiceImpl
                .generateConsumptionReportForDrivers(start, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(driverRepository).findById((String) any());
        verify(driver).getCin();
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#generateConsumptionReportForDrivers(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForDrivers6() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class, () -> consumptionReportServiceImpl
                .generateConsumptionReportForDrivers(start, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMostEconomicalDriver() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at java.util.ArrayList$Itr.next(ArrayList.java:970)
        //       at java.util.Collections.min(Collections.java:640)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.getMostEconomicalDriver(ConsumptionReportServiceImpl.java:94)
        //   See https://diff.blue/R013 to resolve this issue.

        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(new ArrayList<>());
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.getMostEconomicalDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostEconomicalDriver2() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        DriverConsumptionReportDtoResponse actualMostEconomicalDriver = consumptionReportServiceImpl
                .getMostEconomicalDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
        assertNull(actualMostEconomicalDriver.driverCin());
        assertEquals(0.0d, actualMostEconomicalDriver.totalFuelConsumed());
        assertEquals(0.0d, actualMostEconomicalDriver.totalDistanceTravelled());
        assertNull(actualMostEconomicalDriver.id());
        assertEquals(0.0d, actualMostEconomicalDriver.fuelEfficiency());
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostEconomicalDriver3() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostEconomicalDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMostEconomicalDriver4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Double.doubleValue()" because the return value of "com.gl.parcauto.entity.FuelConsumptionRecord.getFuelConsumed()" is null
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.reportOfDriver(ConsumptionReportServiceImpl.java:118)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.generateConsumptionReportForDrivers(ConsumptionReportServiceImpl.java:85)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.getMostEconomicalDriver(ConsumptionReportServiceImpl.java:93)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");

        ArrayList<FuelConsumptionRecord> fuelConsumptionRecordList = new ArrayList<>();
        fuelConsumptionRecordList.add(new FuelConsumptionRecord());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(fuelConsumptionRecordList);
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.getMostEconomicalDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostEconomicalDriver5() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        Driver driver = mock(Driver.class);
        when(driver.getCin()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostEconomicalDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(driverRepository).findById((String) any());
        verify(driver).getCin();
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostEconomicalDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostEconomicalDriver6() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostEconomicalDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMostConsumptiveDriver() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at java.util.ArrayList$Itr.next(ArrayList.java:970)
        //       at java.util.Collections.max(Collections.java:713)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.getMostConsumptiveDriver(ConsumptionReportServiceImpl.java:100)
        //   See https://diff.blue/R013 to resolve this issue.

        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(new ArrayList<>());
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.getMostConsumptiveDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostConsumptiveDriver2() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        DriverConsumptionReportDtoResponse actualMostConsumptiveDriver = consumptionReportServiceImpl
                .getMostConsumptiveDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
        assertNull(actualMostConsumptiveDriver.driverCin());
        assertEquals(0.0d, actualMostConsumptiveDriver.totalFuelConsumed());
        assertEquals(0.0d, actualMostConsumptiveDriver.totalDistanceTravelled());
        assertNull(actualMostConsumptiveDriver.id());
        assertEquals(0.0d, actualMostConsumptiveDriver.fuelEfficiency());
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostConsumptiveDriver3() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostConsumptiveDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(fuelConsumptionRecordRepository).findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMostConsumptiveDriver4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Double.doubleValue()" because the return value of "com.gl.parcauto.entity.FuelConsumptionRecord.getFuelConsumed()" is null
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.reportOfDriver(ConsumptionReportServiceImpl.java:118)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.generateConsumptionReportForDrivers(ConsumptionReportServiceImpl.java:85)
        //       at com.gl.parcauto.service.impl.ConsumptionReportServiceImpl.getMostConsumptiveDriver(ConsumptionReportServiceImpl.java:99)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");

        ArrayList<FuelConsumptionRecord> fuelConsumptionRecordList = new ArrayList<>();
        fuelConsumptionRecordList.add(new FuelConsumptionRecord());
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(fuelConsumptionRecordList);
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        consumptionReportServiceImpl.getMostConsumptiveDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1));
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostConsumptiveDriver5() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        Driver driver = mock(Driver.class);
        when(driver.getCin()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostConsumptiveDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(driverRepository).findById((String) any());
        verify(driver).getCin();
    }

    /**
     * Method under test: {@link ConsumptionReportServiceImpl#getMostConsumptiveDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostConsumptiveDriver6() {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        when(fuelConsumptionRecordRepository.findFuelConsumptionRecordBetweenDatesByDriver((LocalDateTime) any(),
                (LocalDateTime) any(), (String) any())).thenReturn(new ArrayList<>());
        when(fuelConsumptionRecordRepository.findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(),
                (LocalDateTime) any())).thenReturn(stringList);
        when(driverRepository.findById((String) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        LocalDateTime startDate = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(ResourceNotFoundException.class,
                () -> consumptionReportServiceImpl.getMostConsumptiveDriver(startDate, LocalDateTime.of(1, 1, 1, 1, 1)));
        verify(fuelConsumptionRecordRepository)
                .findDriversThatHaveFuelConsumptionRecordBetweenDates((LocalDateTime) any(), (LocalDateTime) any());
        verify(driverRepository).findById((String) any());
    }
}

