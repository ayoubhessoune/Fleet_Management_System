package com.gl.parcauto.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class AttestationConformityTest {
    @Test
    void testGetId() {
        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setId(1L);
        assertEquals(1L, attestationConformity.getId());
    }

    @Test
    void testSetId() {
        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setId(1L);
        assertEquals(1L, attestationConformity.getId());
    }

    @Test
    void testGetDescription() {
        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setDescription("Test Description");
        assertEquals("Test Description", attestationConformity.getDescription());
    }

    @Test
    void testSetDescription() {
        AttestationConformity attestationConformity = new AttestationConformity();
        attestationConformity.setDescription("Test Description");
        assertEquals("Test Description", attestationConformity.getDescription());
    }

    @Test
    void testGetTechnicalVisit() {
        AttestationConformity attestationConformity = new AttestationConformity();
        TechnicalVisit technicalVisit = new TechnicalVisit();
        attestationConformity.setTechnicalVisit(technicalVisit);
        assertSame(technicalVisit, attestationConformity.getTechnicalVisit());
    }

    @Test
    void testSetTechnicalVisit() {
        AttestationConformity attestationConformity = new AttestationConformity();
        TechnicalVisit technicalVisit = new TechnicalVisit();
        attestationConformity.setTechnicalVisit(technicalVisit);
        assertSame(technicalVisit, attestationConformity.getTechnicalVisit());
    }
}
