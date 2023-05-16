package com.gl.parcauto.mapper;

import com.gl.parcauto.dto.request.AttestationConformityDtoRequest;
import com.gl.parcauto.dto.response.AttestationConformityDtoResponse;
import com.gl.parcauto.entity.AttestationConformity;
import com.gl.parcauto.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttestationConformityMapper {
    AttestationConformityMapper INSTANCE = Mappers.getMapper(AttestationConformityMapper.class);
    AttestationConformity toEntity(AttestationConformityDtoRequest dtoRequest);
    AttestationConformityDtoResponse toDto(AttestationConformity attestationConformity);
}
