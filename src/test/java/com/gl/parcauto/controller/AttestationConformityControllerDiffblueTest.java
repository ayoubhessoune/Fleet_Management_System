package com.gl.parcauto.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.parcauto.dto.request.AttestationConformityDtoRequest;
import com.gl.parcauto.dto.response.AttestationConformityDtoResponse;
import com.gl.parcauto.service.AttestationConformityService;

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

@ContextConfiguration(classes = {AttestationConformityController.class})
@ExtendWith(SpringExtension.class)
class AttestationConformityControllerDiffblueTest {
    @Autowired
    private AttestationConformityController attestationConformityController;

    @MockBean
    private AttestationConformityService attestationConformityService;

    /**
     * Method under test:
     * {@link AttestationConformityController#createAttestation(String, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testCreateAttestation() throws Exception {
        when(attestationConformityService.getAllAttestation(Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .get("/api/v1/vehicles/{licensePlate}/technicalVisits/{technicalVisitId}/attestations", "License Plate", 1L)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper
                .writeValueAsString(new AttestationConformityDtoRequest("The characteristics of someone or something")));
        MockMvcBuilders.standaloneSetup(attestationConformityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link AttestationConformityController#getAllAttestation(String, Long)}
     */
    @Test
    void testGetAllAttestation() throws Exception {
        when(attestationConformityService.getAllAttestation(Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/vehicles/{licensePlate}/technicalVisits/{technicalVisitId}/attestations", "License Plate", 1L);
        MockMvcBuilders.standaloneSetup(attestationConformityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link AttestationConformityController#getAllAttestation(String, Long)}
     */
    @Test
    void testGetAllAttestation2() throws Exception {
        when(attestationConformityService.getAllAttestation(Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/vehicles/{licensePlate}/technicalVisits/{technicalVisitId}/attestations", "License Plate", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(attestationConformityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link AttestationConformityController#deleteAttestation(String, Long, Long)}
     */
    @Test
    void testDeleteAttestation() throws Exception {
        doNothing().when(attestationConformityService)
                .delete(Mockito.<String>any(), Mockito.<Long>any(), Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/api/v1/vehicles/{licensePlate}/technicalVisits/{technicalVisitId}/attestations/{attestationId}",
                "License Plate", 1L, 1L);
        MockMvcBuilders.standaloneSetup(attestationConformityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Attestation conformity deleted successfully"));
    }

    /**
     * Method under test:
     * {@link AttestationConformityController#deleteAttestation(String, Long, Long)}
     */
    @Test
    void testDeleteAttestation2() throws Exception {
        doNothing().when(attestationConformityService)
                .delete(Mockito.<String>any(), Mockito.<Long>any(), Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/api/v1/vehicles/{licensePlate}/technicalVisits/{technicalVisitId}/attestations/{attestationId}",
                "License Plate", 1L, 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(attestationConformityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Attestation conformity deleted successfully"));
    }

    /**
     * Method under test:
     * {@link AttestationConformityController#getAttestationById(String, Long, Long)}
     */
    @Test
    void testGetAttestationById() throws Exception {
        when(attestationConformityService.getById(Mockito.<String>any(), Mockito.<Long>any(), Mockito.<Long>any()))
                .thenReturn(new AttestationConformityDtoResponse("Vehicle License Plate", 1L, 1L,
                        "The characteristics of someone or something"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/vehicles/{licensePlate}/technicalVisits/{technicalVisitId}/attestations/{attestationId}",
                "License Plate", 1L, 1L);
        MockMvcBuilders.standaloneSetup(attestationConformityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"vehicleLicensePlate\":\"Vehicle License Plate\",\"technicalVisitId\":1,\"attestationId\":1,\"description\":\"The"
                                        + " characteristics of someone or something\"}"));
    }

    /**
     * Method under test:
     * {@link AttestationConformityController#updateAttestation(String, Long, Long, AttestationConformityDtoRequest)}
     */
    @Test
    void testUpdateAttestation() throws Exception {
        when(attestationConformityService.update(Mockito.<String>any(), Mockito.<Long>any(), Mockito.<Long>any(),
                Mockito.<AttestationConformityDtoRequest>any()))
                .thenReturn(new AttestationConformityDtoResponse("Vehicle License Plate", 1L, 1L,
                        "The characteristics of someone or something"));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/v1/vehicles/{licensePlate}/technicalVisits/{technicalVisitId}/attestations/{attestationId}",
                        "License Plate", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper
                .writeValueAsString(new AttestationConformityDtoRequest("The characteristics of someone or something")));
        MockMvcBuilders.standaloneSetup(attestationConformityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"vehicleLicensePlate\":\"Vehicle License Plate\",\"technicalVisitId\":1,\"attestationId\":1,\"description\":\"The"
                                        + " characteristics of someone or something\"}"));
    }
}
