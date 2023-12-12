package com.gl.parcauto.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.parcauto.dto.request.FuelConsumptionRecordDtoRequest;
import com.gl.parcauto.dto.request.FuelConsumptionRecordUpdateDtoRequest;
import com.gl.parcauto.dto.response.FuelConsumptionRecordDtoResponse;
import com.gl.parcauto.entity.DriverLicenseType;
import com.gl.parcauto.entity.FuelType;
import com.gl.parcauto.service.FuelConsumptionRecordService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FuelConsumptionController.class})
@ExtendWith(SpringExtension.class)
class FuelConsumptionControllerDiffblueTest {
    @Autowired
    private FuelConsumptionController fuelConsumptionController;

    @MockBean
    private FuelConsumptionRecordService fuelConsumptionRecordService;

    /**
     * Method under test:
     * {@link FuelConsumptionController#createConsumption(String, FuelConsumptionRecordDtoRequest)}
     */
    @Test
    void testCreateConsumption() throws Exception {
        LocalDateTime tripStartDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(fuelConsumptionRecordService.createConsumption(Mockito.<String>any(),
                Mockito.<FuelConsumptionRecordDtoRequest>any()))
                .thenReturn(new FuelConsumptionRecordDtoResponse(1L, 1L, tripStartDate,
                        LocalDate.of(1970, 1, 1).atStartOfDay(), "127.0.0.1", "127.0.0.1", DriverLicenseType.A,
                        "Vehicle License Plate", "Driver Cin", FuelType.GASOLINE, 10.0d, 10.0d, "Refueling Location"));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .post("/api/v1/drivers/{cin}/consumptions", "Cin")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new FuelConsumptionRecordDtoRequest(1L, "Vehicle License Plate",
                        "Driver Cin", 10.0d, FuelType.GASOLINE, 10.0d, 10.0d, "Refueling Location")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(fuelConsumptionController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Method under test:
     * {@link FuelConsumptionController#getConsumptionDriverById(String, Long)}
     */
    @Test
    void testGetConsumptionDriverById() throws Exception {
        LocalDateTime tripStartDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(fuelConsumptionRecordService.getConsumptionDriverById(Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(new FuelConsumptionRecordDtoResponse(1L, 1L, tripStartDate, LocalDate.of(1970, 1, 1).atStartOfDay(),
                        "127.0.0.1", "127.0.0.1", DriverLicenseType.A, "Vehicle License Plate", "Driver Cin", FuelType.GASOLINE,
                        10.0d, 10.0d, "Refueling Location"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/drivers/{cin}/consumptions/{recordId}", "Cin", 1L);
        MockMvcBuilders.standaloneSetup(fuelConsumptionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Method under test:
     * {@link FuelConsumptionController#getConsumptionByDriver(String)}
     */
    @Test
    void testGetConsumptionByDriver() throws Exception {
        when(fuelConsumptionRecordService.getConsumptionByDriver(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/drivers/{cin}/consumptions",
                "Cin");
        MockMvcBuilders.standaloneSetup(fuelConsumptionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link FuelConsumptionController#getConsumptionByVehicle(String)}
     */
    @Test
    void testGetConsumptionByVehicle() throws Exception {
        when(fuelConsumptionRecordService.getConsumptionByVehicle(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/vehicles/{licensePlate}/consumptions", "License Plate");
        MockMvcBuilders.standaloneSetup(fuelConsumptionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FuelConsumptionController#getAllConsumption()}
     */
    @Test
    void testGetAllConsumption() throws Exception {
        when(fuelConsumptionRecordService.getAllConsumption()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/consumptions");
        MockMvcBuilders.standaloneSetup(fuelConsumptionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link FuelConsumptionController#updateConsumptionDriverById(String, Long, FuelConsumptionRecordUpdateDtoRequest)}
     */
    @Test
    void testUpdateConsumptionDriverById() throws Exception {
        LocalDateTime tripStartDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(fuelConsumptionRecordService.updateConsumptionDriverById(Mockito.<String>any(), Mockito.<Long>any(),
                Mockito.<FuelConsumptionRecordUpdateDtoRequest>any()))
                .thenReturn(new FuelConsumptionRecordDtoResponse(1L, 1L, tripStartDate,
                        LocalDate.of(1970, 1, 1).atStartOfDay(), "127.0.0.1", "127.0.0.1", DriverLicenseType.A,
                        "Vehicle License Plate", "Driver Cin", FuelType.GASOLINE, 10.0d, 10.0d, "Refueling Location"));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/v1/drivers/{cin}/consumptions/{recordId}", "Cin", 1L)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(
                new FuelConsumptionRecordUpdateDtoRequest(10.0d, FuelType.GASOLINE, 10.0d, 10.0d, "Refueling Location")));
        MockMvcBuilders.standaloneSetup(fuelConsumptionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Method under test: {@link FuelConsumptionController#getConsumptionById(Long)}
     */
    @Test
    void testGetConsumptionById() throws Exception {
        LocalDateTime tripStartDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(fuelConsumptionRecordService.getConsumptionById(Mockito.<Long>any()))
                .thenReturn(new FuelConsumptionRecordDtoResponse(1L, 1L, tripStartDate, LocalDate.of(1970, 1, 1).atStartOfDay(),
                        "127.0.0.1", "127.0.0.1", DriverLicenseType.A, "Vehicle License Plate", "Driver Cin", FuelType.GASOLINE,
                        10.0d, 10.0d, "Refueling Location"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/consumptions/{consumptionId}",
                1L);
        MockMvcBuilders.standaloneSetup(fuelConsumptionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Method under test:
     * {@link FuelConsumptionController#deleteConsumptionById(Long)}
     */
    @Test
    void testDeleteConsumptionById() throws Exception {
        doNothing().when(fuelConsumptionRecordService).deleteConsumptionById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/consumptions/{consumptionId}",
                1L);
        MockMvcBuilders.standaloneSetup(fuelConsumptionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Fuel consumption record deleted successfully"));
    }

    /**
     * Method under test:
     * {@link FuelConsumptionController#deleteConsumptionById(Long)}
     */
    @Test
    void testDeleteConsumptionById2() throws Exception {
        doNothing().when(fuelConsumptionRecordService).deleteConsumptionById(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/consumptions/{consumptionId}",
                1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(fuelConsumptionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Fuel consumption record deleted successfully"));
    }
}
