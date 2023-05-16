package com.gl.parcauto.controller;

import com.gl.parcauto.dto.request.AttestationConformityDtoRequest;
import com.gl.parcauto.dto.response.AttestationConformityDtoResponse;
import com.gl.parcauto.service.AttestationConformityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles/{licensePlate}/technicalVisits/{technicalVisitId}/attestations")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class AttestationConformityController {
    private final AttestationConformityService service;
    @PostMapping
    public ResponseEntity<AttestationConformityDtoResponse> createAttestation(
            @PathVariable(name = "licensePlate") String licensePlate,
            @PathVariable(name = "technicalVisitId") Long technicalVisitId,
            @Valid @RequestBody AttestationConformityDtoRequest request) {
        AttestationConformityDtoResponse response = service.createAttestationConformity(licensePlate, technicalVisitId, request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{attestationId}")
    public ResponseEntity<AttestationConformityDtoResponse> getAttestationById(
            @PathVariable(name = "licensePlate") String licensePlate,
            @PathVariable(name = "technicalVisitId") Long technicalVisitId,
            @PathVariable(name = "attestationId") Long attestationId) {
        AttestationConformityDtoResponse response = service.getById(licensePlate, technicalVisitId, attestationId);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<AttestationConformityDtoResponse>> getAllAttestation(
            @PathVariable(name = "licensePlate") String licensePlate,
            @PathVariable(name = "technicalVisitId") Long technicalVisitId) {
        List<AttestationConformityDtoResponse> responses = service.getAllAttestation(licensePlate, technicalVisitId);
        return ResponseEntity.ok(responses);
    }
    @PutMapping("/{attestationId}")
    public ResponseEntity<AttestationConformityDtoResponse> updateAttestation(
            @PathVariable(name = "licensePlate") String licensePlate,
            @PathVariable(name = "technicalVisitId") Long technicalVisitId,
            @PathVariable(name = "attestationId") Long attestationId,
            @Valid @RequestBody AttestationConformityDtoRequest request) {
        AttestationConformityDtoResponse response = service.update(licensePlate, technicalVisitId, attestationId, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{attestationId}")
    public ResponseEntity<String> deleteAttestation(
            @PathVariable(name = "licensePlate") String licensePlate,
            @PathVariable(name = "technicalVisitId") Long technicalVisitId,
            @PathVariable(name = "attestationId") Long attestationId) {
        service.delete(licensePlate, technicalVisitId, attestationId);
        return ResponseEntity.ok("Attestation conformity deleted successfully");
    }
}
