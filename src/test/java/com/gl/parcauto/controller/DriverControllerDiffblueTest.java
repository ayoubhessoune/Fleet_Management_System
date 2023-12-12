package com.gl.parcauto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.parcauto.auth.IAuthenticationFacade;
import com.gl.parcauto.dto.request.DriverDtoRequest;
import com.gl.parcauto.dto.request.RoleDtoRequest;
import com.gl.parcauto.dto.request.UserDtoRequest;
import com.gl.parcauto.dto.response.DriverDtoResponse;
import com.gl.parcauto.entity.Driver;
import com.gl.parcauto.entity.Role;
import com.gl.parcauto.entity.User;
import com.gl.parcauto.repository.DriverRepository;
import com.gl.parcauto.repository.RoleRepository;
import com.gl.parcauto.repository.UserRepository;
import com.gl.parcauto.repository.VacationRepository;
import com.gl.parcauto.repository.VehicleRepository;
import com.gl.parcauto.service.DriverService;
import com.gl.parcauto.service.impl.AvailabilityServiceImpl;
import com.gl.parcauto.service.impl.DriverServiceImpl;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DriverController.class})
@ExtendWith(SpringExtension.class)
class DriverControllerDiffblueTest {
    @Autowired
    private DriverController driverController;

    @MockBean
    private DriverService driverService;

    /**
     * Method under test: {@link DriverController#getAllDrivers()}
     */
    @Test
    void testGetAllDrivers() throws Exception {
        when(driverService.getAllDrivers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/drivers");
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link DriverController#getAvailableDriversBetweenDates(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetAvailableDriversBetweenDates() throws Exception {
        when(driverService.getAvailableDriversBetweenDates(Mockito.<LocalDateTime>any(), Mockito.<LocalDateTime>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/drivers");
        MockHttpServletRequestBuilder paramResult = getResult.param("endTrip",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("startTrip",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DriverController#getTripsOfDriver(String)}
     */
    @Test
    void testGetTripsOfDriver() throws Exception {
        when(driverService.getTripsOfDriver(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/drivers/{cin}/trips", "Cin");
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link DriverController#updateDriver(String, DriverDtoRequest)}
     */
    @Test
    void testUpdateDriver() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.gl.parcauto.dto.request.DriverDtoRequest["dateOfBirth"])
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
        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.save(Mockito.<Driver>any())).thenReturn(driver2);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        AvailabilityServiceImpl availabilityService = new AvailabilityServiceImpl(mock(VacationRepository.class),
                mock(DriverRepository.class), mock(VehicleRepository.class));

        UserRepository userRepository = mock(UserRepository.class);
        IAuthenticationFacade authenticationFacade = mock(IAuthenticationFacade.class);
        DriverController driverController = new DriverController(
                new DriverServiceImpl(driverRepository, availabilityService, userRepository, authenticationFacade,
                        new BCryptPasswordEncoder(), mock(RoleRepository.class)));
        ResponseEntity<DriverDtoResponse> actualUpdateDriverResult = driverController.updateDriver("Cin",
                new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.of(1970, 1, 1)));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(driverRepository).save(Mockito.<Driver>any());
        DriverDtoResponse body = actualUpdateDriverResult.getBody();
        assertEquals("1970-01-01", body.dateOfBirth().toString());
        assertEquals("Cin", body.cin());
        assertEquals("Doe", body.lastName());
        assertEquals("Jane", body.firstName());
        assertEquals(200, actualUpdateDriverResult.getStatusCodeValue());
        assertTrue(actualUpdateDriverResult.hasBody());
        assertTrue(actualUpdateDriverResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link DriverController#createDriver(DriverDtoRequest)}
     */
    @Test
    void testCreateDriver() throws Exception {
        when(driverService.getAllDrivers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/v1/drivers")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new DriverDtoRequest("Cin", "Jane", "Doe", null)));
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DriverController#deleteDriverUserAccount(String)}
     */
    @Test
    void testDeleteDriverUserAccount() throws Exception {
        doNothing().when(driverService).deleteDriverUserAccount(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/drivers/{cin}/user", "Cin");
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User account of driver deleted successfully"));
    }

    /**
     * Method under test: {@link DriverController#deleteDriverUserAccount(String)}
     */
    @Test
    void testDeleteDriverUserAccount2() throws Exception {
        doNothing().when(driverService).deleteDriverUserAccount(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/drivers/{cin}/user", "Cin");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User account of driver deleted successfully"));
    }

    /**
     * Method under test: {@link DriverController#deleteDriver(String)}
     */
    @Test
    void testDeleteDriver() throws Exception {
        doNothing().when(driverService).delete(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/drivers/{cin}", "Cin");
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Driver deleted successfully"));
    }

    /**
     * Method under test: {@link DriverController#deleteDriver(String)}
     */
    @Test
    void testDeleteDriver2() throws Exception {
        doNothing().when(driverService).delete(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/drivers/{cin}", "Cin");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Driver deleted successfully"));
    }

    /**
     * Method under test: {@link DriverController#getDriverById(String)}
     */
    @Test
    void testGetDriverById() throws Exception {
        when(driverService.getById(Mockito.<String>any()))
                .thenReturn(new DriverDtoResponse("Cin", "Jane", "Doe", LocalDate.of(1970, 1, 1)));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/drivers/{cin}", "Cin");
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"cin\":\"Cin\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"dateOfBirth\":[1970,1,1]}"));
    }

    /**
     * Method under test:
     * {@link DriverController#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    void testUpdateUserAccountForDriver() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/api/v1/drivers/{cin}/user", "Cin")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(
                objectMapper.writeValueAsString(new UserDtoRequest("janedoe", "iloveyou", new RoleDtoRequest("Name"))));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}
