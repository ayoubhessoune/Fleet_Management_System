package com.gl.parcauto.controller;

import static org.mockito.Mockito.when;

import com.gl.parcauto.dto.response.VehicleConsumptionReportDtoResponse;
import com.gl.parcauto.entity.DriverLicenseType;
import com.gl.parcauto.service.ConsumptionReportService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {VehicleConsumptionReportController.class})
@ExtendWith(SpringExtension.class)
class VehicleConsumptionReportControllerDiffblueTest {
    @MockBean
    private ConsumptionReportService consumptionReportService;

    @Autowired
    private VehicleConsumptionReportController vehicleConsumptionReportController;

    /**
     * Method under test:
     * {@link VehicleConsumptionReportController#generateConsumptionReportByVehicle(String, LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportByVehicle() throws Exception {
        when(consumptionReportService.generateConsumptionReportByVehicle(Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<String>any()))
                .thenReturn(new VehicleConsumptionReportDtoResponse(1L, "Vehicle License Plate", DriverLicenseType.A, 10.0d,
                        10.0d, 10.0d));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/api/v1/vehicles/{licensePlate}/consumption-report", "License Plate");
        MockHttpServletRequestBuilder paramResult = getResult.param("endDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("startDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockMvcBuilders.standaloneSetup(vehicleConsumptionReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"vehicleLicensePlate\":\"Vehicle License Plate\",\"vehicleType\":\"A\",\"totalFuelConsumed\":10.0,"
                                        + "\"totalDistanceTravelled\":10.0,\"fuelEfficiency\":10.0}"));
    }

    /**
     * Method under test:
     * {@link VehicleConsumptionReportController#generateConsumptionReportForVehicles(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForVehicles() throws Exception {
        when(consumptionReportService.generateConsumptionReportForVehicles(Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/vehicles/consumption-reports");
        MockHttpServletRequestBuilder paramResult = getResult.param("endDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("startDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockMvcBuilders.standaloneSetup(vehicleConsumptionReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link VehicleConsumptionReportController#getMostEconomicalVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostEconomicalVehicle() throws Exception {
        when(consumptionReportService.getMostEconomicalVehicle(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any()))
                .thenReturn(new VehicleConsumptionReportDtoResponse(1L, "Vehicle License Plate", DriverLicenseType.A, 10.0d,
                        10.0d, 10.0d));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/vehicles/most-economical-vehicle");
        MockHttpServletRequestBuilder paramResult = getResult.param("endDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("startDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockMvcBuilders.standaloneSetup(vehicleConsumptionReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"vehicleLicensePlate\":\"Vehicle License Plate\",\"vehicleType\":\"A\",\"totalFuelConsumed\":10.0,"
                                        + "\"totalDistanceTravelled\":10.0,\"fuelEfficiency\":10.0}"));
    }

    /**
     * Method under test:
     * {@link VehicleConsumptionReportController#getMostConsumptiveVehicle(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostConsumptiveVehicle() throws Exception {
        when(consumptionReportService.getMostConsumptiveVehicle(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any()))
                .thenReturn(new VehicleConsumptionReportDtoResponse(1L, "Vehicle License Plate", DriverLicenseType.A, 10.0d,
                        10.0d, 10.0d));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/vehicles/most-consumptive-vehicle");
        MockHttpServletRequestBuilder paramResult = getResult.param("endDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("startDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockMvcBuilders.standaloneSetup(vehicleConsumptionReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"vehicleLicensePlate\":\"Vehicle License Plate\",\"vehicleType\":\"A\",\"totalFuelConsumed\":10.0,"
                                        + "\"totalDistanceTravelled\":10.0,\"fuelEfficiency\":10.0}"));
    }
}
