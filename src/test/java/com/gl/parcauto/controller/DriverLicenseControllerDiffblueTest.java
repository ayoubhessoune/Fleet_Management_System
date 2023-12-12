package com.gl.parcauto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gl.parcauto.dto.request.DriverLicenseDtoRequest;
import com.gl.parcauto.dto.response.DriverLicenseDtoResponse;
import com.gl.parcauto.entity.Driver;
import com.gl.parcauto.entity.DriverLicense;
import com.gl.parcauto.entity.DriverLicenseType;
import com.gl.parcauto.entity.Role;
import com.gl.parcauto.entity.User;
import com.gl.parcauto.repository.DriverLicenseRepository;
import com.gl.parcauto.repository.DriverRepository;
import com.gl.parcauto.service.DriverLicenseService;
import com.gl.parcauto.service.impl.DriverLicenseServiceImpl;

import java.time.LocalDate;
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

@ContextConfiguration(classes = {DriverLicenseController.class})
@ExtendWith(SpringExtension.class)
class DriverLicenseControllerDiffblueTest {
    @Autowired
    private DriverLicenseController driverLicenseController;

    @MockBean
    private DriverLicenseService driverLicenseService;

    /**
     * Method under test:
     * {@link DriverLicenseController#createDriverLicense(String, DriverLicenseDtoRequest)}
     */
    @Test
    void testCreateDriverLicense() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.gl.parcauto.dto.request.DriverLicenseDtoRequest["deliveryDate"])
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
        Optional<Driver> ofResult = Optional.of(driver);
        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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

        DriverLicense driverLicense = new DriverLicense();
        driverLicense.setDeliveryDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        driverLicense.setDriver(driver2);
        driverLicense.setId(1L);
        driverLicense.setLicenseType(DriverLicenseType.A);
        DriverLicenseRepository driverLicenseRepository = mock(DriverLicenseRepository.class);
        when(driverLicenseRepository.save(Mockito.<DriverLicense>any())).thenReturn(driverLicense);
        DriverLicenseController driverLicenseController = new DriverLicenseController(
                new DriverLicenseServiceImpl(driverRepository, driverLicenseRepository));
        ResponseEntity<DriverLicenseDtoResponse> actualCreateDriverLicenseResult = driverLicenseController
                .createDriverLicense("Cin",
                        new DriverLicenseDtoRequest(LocalDate.of(1970, 1, 1).atStartOfDay(), DriverLicenseType.A));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(driverLicenseRepository).save(Mockito.<DriverLicense>any());
        DriverLicenseDtoResponse body = actualCreateDriverLicenseResult.getBody();
        assertEquals("00:00", body.deliveryDate().toLocalTime().toString());
        assertEquals("Cin", body.driverCin());
        assertEquals(1, actualCreateDriverLicenseResult.getHeaders().size());
        assertEquals(1L, body.id().longValue());
        assertEquals(201, actualCreateDriverLicenseResult.getStatusCodeValue());
        assertEquals(DriverLicenseType.A, body.licenseType());
        assertTrue(actualCreateDriverLicenseResult.hasBody());
    }

    /**
     * Method under test:
     * {@link DriverLicenseController#getLicensesForDriver(String)}
     */
    @Test
    void testGetLicensesForDriver() throws Exception {
        when(driverLicenseService.getLicensesForDriver(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/drivers/{cin}/driverLicenses",
                "Cin");
        MockMvcBuilders.standaloneSetup(driverLicenseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link DriverLicenseController#updateLicense(String, Long, DriverLicenseDtoRequest)}
     */
    @Test
    void testUpdateLicense() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.gl.parcauto.dto.request.DriverLicenseDtoRequest["deliveryDate"])
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
        Optional<Driver> ofResult = Optional.of(driver);
        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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

        DriverLicense driverLicense = new DriverLicense();
        driverLicense.setDeliveryDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        driverLicense.setDriver(driver2);
        driverLicense.setId(1L);
        driverLicense.setLicenseType(DriverLicenseType.A);
        Optional<DriverLicense> ofResult2 = Optional.of(driverLicense);

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

        DriverLicense driverLicense2 = new DriverLicense();
        driverLicense2.setDeliveryDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        driverLicense2.setDriver(driver3);
        driverLicense2.setId(1L);
        driverLicense2.setLicenseType(DriverLicenseType.A);
        DriverLicenseRepository driverLicenseRepository = mock(DriverLicenseRepository.class);
        when(driverLicenseRepository.save(Mockito.<DriverLicense>any())).thenReturn(driverLicense2);
        when(driverLicenseRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        DriverLicenseController driverLicenseController = new DriverLicenseController(
                new DriverLicenseServiceImpl(driverRepository, driverLicenseRepository));
        ResponseEntity<DriverLicenseDtoResponse> actualUpdateLicenseResult = driverLicenseController.updateLicense("Cin",
                1L, new DriverLicenseDtoRequest(LocalDate.of(1970, 1, 1).atStartOfDay(), DriverLicenseType.A));
        verify(driverLicenseRepository).findById(Mockito.<Long>any());
        verify(driverRepository).findById(Mockito.<String>any());
        verify(driverLicenseRepository).save(Mockito.<DriverLicense>any());
        DriverLicenseDtoResponse body = actualUpdateLicenseResult.getBody();
        assertEquals("1970-01-01", body.deliveryDate().toLocalDate().toString());
        assertEquals("Cin", body.driverCin());
        assertEquals(1L, body.id().longValue());
        assertEquals(200, actualUpdateLicenseResult.getStatusCodeValue());
        assertEquals(DriverLicenseType.A, body.licenseType());
        assertTrue(actualUpdateLicenseResult.hasBody());
        assertTrue(actualUpdateLicenseResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link DriverLicenseController#deleteLicense(String, Long)}
     */
    @Test
    void testDeleteLicense() throws Exception {
        doNothing().when(driverLicenseService).delete(Mockito.<String>any(), Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/drivers/{cin}/driverLicenses/{licenseId}", "Cin", 1L);
        MockMvcBuilders.standaloneSetup(driverLicenseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Driver license deleted successfully"));
    }

    /**
     * Method under test:
     * {@link DriverLicenseController#deleteLicense(String, Long)}
     */
    @Test
    void testDeleteLicense2() throws Exception {
        doNothing().when(driverLicenseService).delete(Mockito.<String>any(), Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/drivers/{cin}/driverLicenses/{licenseId}", "Cin", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(driverLicenseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Driver license deleted successfully"));
    }

    /**
     * Method under test:
     * {@link DriverLicenseController#getLicenseById(String, Long)}
     */
    @Test
    void testGetLicenseById() throws Exception {
        when(driverLicenseService.getById(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(
                new DriverLicenseDtoResponse(1L, LocalDate.of(1970, 1, 1).atStartOfDay(), DriverLicenseType.A, "Driver Cin"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/drivers/{cin}/driverLicenses/{licenseId}", "Cin", 1L);
        MockMvcBuilders.standaloneSetup(driverLicenseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"deliveryDate\":[1970,1,1,0,0],\"licenseType\":\"A\",\"driverCin\":\"Driver Cin\"}"));
    }
}
