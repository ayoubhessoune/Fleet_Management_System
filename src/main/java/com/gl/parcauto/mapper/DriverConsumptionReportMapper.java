package com.gl.parcauto.mapper;

import com.gl.parcauto.dto.response.DriverConsumptionReportDtoResponse;
import com.gl.parcauto.entity.DriverConsumptionReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DriverConsumptionReportMapper {
    DriverConsumptionReportMapper INSTANCE = Mappers.getMapper(DriverConsumptionReportMapper.class);
    @Mappings({
            @Mapping(target = "driverCin", source = "report.driver.cin")
    })
    DriverConsumptionReportDtoResponse toDto(DriverConsumptionReport report);
}
