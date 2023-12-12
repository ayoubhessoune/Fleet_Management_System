package com.gl.parcauto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gl.parcauto.dto.request.VacationDtoRequest;
import com.gl.parcauto.dto.response.VacationDtoResponse;
import com.gl.parcauto.entity.Driver;
import com.gl.parcauto.entity.Role;
import com.gl.parcauto.entity.User;
import com.gl.parcauto.entity.Vacation;
import com.gl.parcauto.repository.DriverRepository;
import com.gl.parcauto.repository.VacationRepository;
import com.gl.parcauto.service.VacationService;
import com.gl.parcauto.service.impl.VacationServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {VacationController.class})
@ExtendWith(SpringExtension.class)
class VacationControllerDiffblueTest {
    @Autowired
    private VacationController vacationController;

    @MockBean
    private VacationService vacationService;

    /**
     * Method under test:
     * {@link VacationController#createVacation(String, VacationDtoRequest)}
     */
    @Test
    void testCreateVacation() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.gl.parcauto.dto.request.VacationDtoRequest["start"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");

        User user = new User();
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setRole(role);
        user.setUsername("janedoe");

        Driver driver = new Driver();
        driver.setCin("Cin");
        driver.setDateOfBirth(LocalDate.of(1970, 1, 1));
        driver.setDriverConsumptionReports(new HashSet<>());
        driver.setDriverLicenses(new HashSet<>());
        driver.setFirstName("Jane");
        driver.setFuelConsumptionRecords(new HashSet<>());
        driver.setLastName("Doe");
        driver.setTrips(new HashSet<>());
        driver.setUser(user);
        driver.setVacations(new HashSet<>());

        Vacation vacation = new Vacation();
        vacation.setDriver(driver);
        vacation.setEnd(LocalDate.of(1970, 1, 1).atStartOfDay());
        vacation.setId(1L);
        vacation.setStart(LocalDate.of(1970, 1, 1).atStartOfDay());
        VacationRepository vacationRepository = mock(VacationRepository.class);
        when(vacationRepository.save(Mockito.<Vacation>any())).thenReturn(vacation);

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("Name");

        User user2 = new User();
        user2.setId(1L);
        user2.setPassword("iloveyou");
        user2.setRole(role2);
        user2.setUsername("janedoe");

        Driver driver2 = new Driver();
        driver2.setCin("Cin");
        driver2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        driver2.setDriverConsumptionReports(new HashSet<>());
        driver2.setDriverLicenses(new HashSet<>());
        driver2.setFirstName("Jane");
        driver2.setFuelConsumptionRecords(new HashSet<>());
        driver2.setLastName("Doe");
        driver2.setTrips(new HashSet<>());
        driver2.setUser(user2);
        driver2.setVacations(new HashSet<>());
        Optional<Driver> ofResult = Optional.of(driver2);
        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        VacationController vacationController = new VacationController(
                new VacationServiceImpl(vacationRepository, driverRepository));
        LocalDateTime start = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<VacationDtoResponse> actualCreateVacationResult = vacationController.createVacation("Cin",
                new VacationDtoRequest(start, LocalDate.of(1970, 1, 1).atStartOfDay()));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(vacationRepository).save(Mockito.<Vacation>any());
        VacationDtoResponse body = actualCreateVacationResult.getBody();
        assertEquals("00:00", body.end().toLocalTime().toString());
        assertEquals("00:00", body.start().toLocalTime().toString());
        assertEquals("Cin", body.driverCin());
        assertEquals(1, actualCreateVacationResult.getHeaders().size());
        assertEquals(1L, body.id().longValue());
        assertEquals(201, actualCreateVacationResult.getStatusCodeValue());
        assertTrue(actualCreateVacationResult.hasBody());
    }

    /**
     * Method under test: {@link VacationController#getVacationsForDriver(String)}
     */
    @Test
    void testGetVacationsForDriver() throws Exception {
        when(vacationService.getVacationsForDriver(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/drivers/{cin}/vacations", "Cin");
        MockMvcBuilders.standaloneSetup(vacationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link VacationController#updateVacation(String, Long, VacationDtoRequest)}
     */
    @Test
    void testUpdateVacation() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.gl.parcauto.dto.request.VacationDtoRequest["start"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");

        User user = new User();
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setRole(role);
        user.setUsername("janedoe");

        Driver driver = new Driver();
        driver.setCin("Cin");
        driver.setDateOfBirth(LocalDate.of(1970, 1, 1));
        driver.setDriverConsumptionReports(new HashSet<>());
        driver.setDriverLicenses(new HashSet<>());
        driver.setFirstName("Jane");
        driver.setFuelConsumptionRecords(new HashSet<>());
        driver.setLastName("Doe");
        driver.setTrips(new HashSet<>());
        driver.setUser(user);
        driver.setVacations(new HashSet<>());

        Vacation vacation = new Vacation();
        vacation.setDriver(driver);
        vacation.setEnd(LocalDate.of(1970, 1, 1).atStartOfDay());
        vacation.setId(1L);
        vacation.setStart(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Vacation> ofResult = Optional.of(vacation);

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("Name");

        User user2 = new User();
        user2.setId(1L);
        user2.setPassword("iloveyou");
        user2.setRole(role2);
        user2.setUsername("janedoe");

        Driver driver2 = new Driver();
        driver2.setCin("Cin");
        driver2.setDateOfBirth(LocalDate.of(1970, 1, 1));
        driver2.setDriverConsumptionReports(new HashSet<>());
        driver2.setDriverLicenses(new HashSet<>());
        driver2.setFirstName("Jane");
        driver2.setFuelConsumptionRecords(new HashSet<>());
        driver2.setLastName("Doe");
        driver2.setTrips(new HashSet<>());
        driver2.setUser(user2);
        driver2.setVacations(new HashSet<>());

        Vacation vacation2 = new Vacation();
        vacation2.setDriver(driver2);
        vacation2.setEnd(LocalDate.of(1970, 1, 1).atStartOfDay());
        vacation2.setId(1L);
        vacation2.setStart(LocalDate.of(1970, 1, 1).atStartOfDay());
        VacationRepository vacationRepository = mock(VacationRepository.class);
        when(vacationRepository.save(Mockito.<Vacation>any())).thenReturn(vacation2);
        when(vacationRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Role role3 = new Role();
        role3.setId(1L);
        role3.setName("Name");

        User user3 = new User();
        user3.setId(1L);
        user3.setPassword("iloveyou");
        user3.setRole(role3);
        user3.setUsername("janedoe");

        Driver driver3 = new Driver();
        driver3.setCin("Cin");
        driver3.setDateOfBirth(LocalDate.of(1970, 1, 1));
        driver3.setDriverConsumptionReports(new HashSet<>());
        driver3.setDriverLicenses(new HashSet<>());
        driver3.setFirstName("Jane");
        driver3.setFuelConsumptionRecords(new HashSet<>());
        driver3.setLastName("Doe");
        driver3.setTrips(new HashSet<>());
        driver3.setUser(user3);
        driver3.setVacations(new HashSet<>());
        Optional<Driver> ofResult2 = Optional.of(driver3);
        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        VacationController vacationController = new VacationController(
                new VacationServiceImpl(vacationRepository, driverRepository));
        LocalDateTime start = LocalDate.of(1970, 1, 1).atStartOfDay();
        ResponseEntity<VacationDtoResponse> actualUpdateVacationResult = vacationController.updateVacation("Cin", 1L,
                new VacationDtoRequest(start, LocalDate.of(1970, 1, 1).atStartOfDay()));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(vacationRepository).findById(Mockito.<Long>any());
        verify(vacationRepository).save(Mockito.<Vacation>any());
        VacationDtoResponse body = actualUpdateVacationResult.getBody();
        assertEquals("00:00", body.end().toLocalTime().toString());
        assertEquals("00:00", body.start().toLocalTime().toString());
        assertEquals("Cin", body.driverCin());
        assertEquals(1L, body.id().longValue());
        assertEquals(200, actualUpdateVacationResult.getStatusCodeValue());
        assertTrue(actualUpdateVacationResult.hasBody());
        assertTrue(actualUpdateVacationResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link VacationController#deleteVacation(String, Long)}
     */
    @Test
    void testDeleteVacation() throws Exception {
        doNothing().when(vacationService).delete(Mockito.<String>any(), Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/drivers/{cin}/vacations/{vacationId}", "Cin", 1L);
        MockMvcBuilders.standaloneSetup(vacationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Vacation deleted successfully"));
    }

    /**
     * Method under test: {@link VacationController#deleteVacation(String, Long)}
     */
    @Test
    void testDeleteVacation2() throws Exception {
        doNothing().when(vacationService).delete(Mockito.<String>any(), Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/drivers/{cin}/vacations/{vacationId}", "Cin", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(vacationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Vacation deleted successfully"));
    }

    /**
     * Method under test: {@link VacationController#getVacationById(String, Long)}
     */
    @Test
    void testGetVacationById() throws Exception {
        LocalDateTime start = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(vacationService.getById(Mockito.<String>any(), Mockito.<Long>any()))
                .thenReturn(new VacationDtoResponse(1L, start, LocalDate.of(1970, 1, 1).atStartOfDay(), "Driver Cin"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/drivers/{cin}/vacations/{vacationId}", "Cin", 1L);
        MockMvcBuilders.standaloneSetup(vacationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"start\":[1970,1,1,0,0],\"end\":[1970,1,1,0,0],\"driverCin\":\"Driver Cin\"}"));
    }
}
