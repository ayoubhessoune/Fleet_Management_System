package com.gl.parcauto.mapper;

import com.gl.parcauto.dto.response.VehicleConsumptionReportDtoResponse;
import com.gl.parcauto.entity.VehicleConsumptionReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleConsumptionReportMapper {
    VehicleConsumptionReportMapper INSTANCE = Mappers.getMapper(VehicleConsumptionReportMapper.class);
    @Mappings({
            @Mapping(target = "vehicleLicensePlate", source = "report.vehicle.licensePlate"),
            @Mapping(target = "vehicleType", source = "report.vehicle.type")
    })
    VehicleConsumptionReportDtoResponse toDto(VehicleConsumptionReport report);
}
