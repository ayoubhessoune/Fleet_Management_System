package com.gl.parcauto.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.parcauto.dto.request.TechnicalVisitDtoRequest;
import com.gl.parcauto.dto.response.TechnicalVisitDtoResponse;
import com.gl.parcauto.service.TechnicalVisitService;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TechnicalVisitController.class})
@ExtendWith(SpringExtension.class)
class TechnicalVisitControllerDiffblueTest {
    @Autowired
    private TechnicalVisitController technicalVisitController;

    @MockBean
    private TechnicalVisitService technicalVisitService;

    /**
     * Method under test:
     * {@link TechnicalVisitController#createTechnicalVisit(String, TechnicalVisitDtoRequest)}
     */
    @Test
    void testCreateTechnicalVisit() throws Exception {
        when(technicalVisitService.getTechnicalVisitsForVehicle(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .get("/api/v1/vehicles/{licensePlate}/technicalVisits", "License Plate")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new TechnicalVisitDtoRequest(null)));
        MockMvcBuilders.standaloneSetup(technicalVisitController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link TechnicalVisitController#getTechnicalVisitsForVehicle(String)}
     */
    @Test
    void testGetTechnicalVisitsForVehicle() throws Exception {
        when(technicalVisitService.getTechnicalVisitsForVehicle(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/vehicles/{licensePlate}/technicalVisits", "License Plate");
        MockMvcBuilders.standaloneSetup(technicalVisitController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link TechnicalVisitController#deleteTechnicalVisit(String, Long)}
     */
    @Test
    void testDeleteTechnicalVisit() throws Exception {
        doNothing().when(technicalVisitService).delete(Mockito.<String>any(), Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/vehicles/{licensePlate}/technicalVisits/{technicalVisitId}", "License Plate", 1L);
        MockMvcBuilders.standaloneSetup(technicalVisitController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Technical visit deleted successfully"));
    }

    /**
     * Method under test:
     * {@link TechnicalVisitController#deleteTechnicalVisit(String, Long)}
     */
    @Test
    void testDeleteTechnicalVisit2() throws Exception {
        doNothing().when(technicalVisitService).delete(Mockito.<String>any(), Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/vehicles/{licensePlate}/technicalVisits/{technicalVisitId}", "License Plate", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(technicalVisitController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Technical visit deleted successfully"));
    }

    /**
     * Method under test:
     * {@link TechnicalVisitController#getTechnicalVisitById(String, Long)}
     */
    @Test
    void testGetTechnicalVisitById() throws Exception {
        when(technicalVisitService.getById(Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(new TechnicalVisitDtoResponse(1L, "Vehicle License Plate", LocalDate.of(1970, 1, 1), 1L,
                        "The characteristics of someone or something"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/vehicles/{licensePlate}/technicalVisits/{technicalVisitId}", "License Plate", 1L);
        MockMvcBuilders.standaloneSetup(technicalVisitController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"vehicleLicensePlate\":\"Vehicle License Plate\",\"visitDate\":[1970,1,1],\"attestationId\":1,"
                                        + "\"description\":\"The characteristics of someone or something\"}"));
    }
}
