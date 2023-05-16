package com.gl.parcauto.dto.response;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gl.parcauto.entity.AttestationConformity} entity
 */
public record AttestationConformityDtoResponse(
        String vehicleLicensePlate,
        Long technicalVisitId,
        Long attestationId,
        String description) implements Serializable {
}