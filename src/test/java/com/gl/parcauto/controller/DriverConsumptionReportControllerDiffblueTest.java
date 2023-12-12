package com.gl.parcauto.controller;

import static org.mockito.Mockito.when;

import com.gl.parcauto.dto.response.DriverConsumptionReportDtoResponse;
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

@ContextConfiguration(classes = {DriverConsumptionReportController.class})
@ExtendWith(SpringExtension.class)
class DriverConsumptionReportControllerDiffblueTest {
    @MockBean
    private ConsumptionReportService consumptionReportService;

    @Autowired
    private DriverConsumptionReportController driverConsumptionReportController;

    /**
     * Method under test:
     * {@link DriverConsumptionReportController#generateConsumptionReportByDriver(String, LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportByDriver() throws Exception {
        when(consumptionReportService.generateConsumptionReportByDriver(Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<String>any()))
                .thenReturn(new DriverConsumptionReportDtoResponse(1L, "Driver Cin", 10.0d, 10.0d, 10.0d));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/drivers/{cin}/consumption-report",
                "Cin");
        MockHttpServletRequestBuilder paramResult = getResult.param("endDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("startDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockMvcBuilders.standaloneSetup(driverConsumptionReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"driverCin\":\"Driver Cin\",\"totalFuelConsumed\":10.0,\"totalDistanceTravelled\":10.0,\"fuelEfficiency"
                                        + "\":10.0}"));
    }

    /**
     * Method under test:
     * {@link DriverConsumptionReportController#generateConsumptionReportForDrivers(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGenerateConsumptionReportForDrivers() throws Exception {
        when(consumptionReportService.generateConsumptionReportForDrivers(Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/drivers/consumption-reports");
        MockHttpServletRequestBuilder paramResult = getResult.param("endDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("startDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockMvcBuilders.standaloneSetup(driverConsumptionReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link DriverConsumptionReportController#getMostEconomicalDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostEconomicalDriver() throws Exception {
        when(consumptionReportService.getMostEconomicalDriver(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any()))
                .thenReturn(new DriverConsumptionReportDtoResponse(1L, "Driver Cin", 10.0d, 10.0d, 10.0d));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/drivers/most-economical-driver");
        MockHttpServletRequestBuilder paramResult = getResult.param("endDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("startDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockMvcBuilders.standaloneSetup(driverConsumptionReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"driverCin\":\"Driver Cin\",\"totalFuelConsumed\":10.0,\"totalDistanceTravelled\":10.0,\"fuelEfficiency"
                                        + "\":10.0}"));
    }

    /**
     * Method under test:
     * {@link DriverConsumptionReportController#getMostConsumptiveDriver(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetMostConsumptiveDriver() throws Exception {
        when(consumptionReportService.getMostConsumptiveDriver(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any()))
                .thenReturn(new DriverConsumptionReportDtoResponse(1L, "Driver Cin", 10.0d, 10.0d, 10.0d));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/drivers/most-consumptive-driver");
        MockHttpServletRequestBuilder paramResult = getResult.param("endDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("startDate",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockMvcBuilders.standaloneSetup(driverConsumptionReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"driverCin\":\"Driver Cin\",\"totalFuelConsumed\":10.0,\"totalDistanceTravelled\":10.0,\"fuelEfficiency"
                                        + "\":10.0}"));
    }
}
