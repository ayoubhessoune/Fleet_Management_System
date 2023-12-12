package com.gl.parcauto.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gl.parcauto.auth.IAuthenticationFacade;
import com.gl.parcauto.dto.request.DriverDtoRequest;
import com.gl.parcauto.dto.request.RoleDtoRequest;
import com.gl.parcauto.dto.request.UserDtoRequest;
import com.gl.parcauto.dto.response.DriverDtoResponse;
import com.gl.parcauto.dto.response.DriverUserDtoResponse;
import com.gl.parcauto.dto.response.TripDtoResponse;
import com.gl.parcauto.entity.Driver;
import com.gl.parcauto.entity.DriverConsumptionReport;
import com.gl.parcauto.entity.DriverLicense;
import com.gl.parcauto.entity.FuelConsumptionRecord;
import com.gl.parcauto.entity.Role;
import com.gl.parcauto.entity.Trip;
import com.gl.parcauto.entity.User;
import com.gl.parcauto.entity.Vacation;
import com.gl.parcauto.exception.AppException;
import com.gl.parcauto.exception.ResourceNotFoundException;
import com.gl.parcauto.repository.DriverRepository;
import com.gl.parcauto.repository.RoleRepository;
import com.gl.parcauto.repository.UserRepository;
import com.gl.parcauto.service.AvailabilityService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DriverServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DriverServiceImplTest {
    @MockBean
    private AvailabilityService availabilityService;

    @MockBean
    private DriverRepository driverRepository;

    @Autowired
    private DriverServiceImpl driverServiceImpl;

    @MockBean
    private IAuthenticationFacade iAuthenticationFacade;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link DriverServiceImpl#create(DriverDtoRequest)}
     */
    @Test
    void testCreate() {
        when(driverRepository.existsByCin(Mockito.<String>any())).thenReturn(true);
        assertThrows(AppException.class,
                () -> driverServiceImpl.create(new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.of(1970, 1, 1))));
        verify(driverRepository).existsByCin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#create(DriverDtoRequest)}
     */
    @Test
    void testCreate2() {
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
        when(driverRepository.existsByCin(Mockito.<String>any())).thenReturn(false);
        when(driverRepository.save(Mockito.<Driver>any())).thenReturn(driver);
        DriverDtoResponse actualCreateResult = driverServiceImpl
                .create(new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.of(1970, 1, 1)));
        verify(driverRepository).existsByCin(Mockito.<String>any());
        verify(driverRepository).save(Mockito.<Driver>any());
        assertEquals("1970-01-01", actualCreateResult.dateOfBirth().toString());
        assertEquals("Cin", actualCreateResult.cin());
        assertEquals("Doe", actualCreateResult.lastName());
        assertEquals("Jane", actualCreateResult.firstName());
    }

    /**
     * Method under test: {@link DriverServiceImpl#create(DriverDtoRequest)}
     */
    @Test
    void testCreate3() {
        when(driverRepository.existsByCin(Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        assertThrows(ResourceNotFoundException.class,
                () -> driverServiceImpl.create(new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.of(1970, 1, 1))));
        verify(driverRepository).existsByCin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#create(DriverDtoRequest)}
     */
    @Test
    void testCreate4() {
        when(driverRepository.existsByCin((String) any())).thenReturn(true);
        when(driverRepository.save((Driver) any())).thenReturn(new Driver());
        assertThrows(AppException.class,
                () -> driverServiceImpl.create(new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.ofEpochDay(1L))));
        verify(driverRepository).existsByCin((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#create(DriverDtoRequest)}
     */
    @Test
    void testCreate5() {
        when(driverRepository.existsByCin((String) any())).thenReturn(false);
        when(driverRepository.save((Driver) any())).thenReturn(new Driver());
        DriverDtoResponse actualCreateResult = driverServiceImpl
                .create(new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.ofEpochDay(1L)));
        assertNull(actualCreateResult.cin());
        assertNull(actualCreateResult.lastName());
        assertNull(actualCreateResult.firstName());
        assertNull(actualCreateResult.dateOfBirth());
        verify(driverRepository).existsByCin((String) any());
        verify(driverRepository).save((Driver) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#create(DriverDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreate6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.dto.request.DriverDtoRequest.cin()" because "driverDto" is null
        //       at com.gl.parcauto.service.impl.DriverServiceImpl.create(DriverServiceImpl.java:46)
        //   See https://diff.blue/R013 to resolve this issue.

        when(driverRepository.existsByCin((String) any())).thenReturn(true);
        when(driverRepository.save((Driver) any())).thenReturn(new Driver());
        driverServiceImpl.create(null);
    }

    /**
     * Method under test: {@link DriverServiceImpl#create(DriverDtoRequest)}
     */
    @Test
    void testCreate7() {
        when(driverRepository.existsByCin((String) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(driverRepository.save((Driver) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        assertThrows(ResourceNotFoundException.class,
                () -> driverServiceImpl.create(new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.ofEpochDay(1L))));
        verify(driverRepository).existsByCin((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#create(DriverDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreate8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.gl.parcauto.exception.ResourceNotFoundException: Resource Name not found with Field Name : '42'
        //       at com.gl.parcauto.mapper.DriverMapperImpl.driverToDriverDtoResponse(DriverMapperImpl.java:49)
        //       at com.gl.parcauto.service.impl.DriverServiceImpl.create(DriverServiceImpl.java:55)
        //   See https://diff.blue/R013 to resolve this issue.

        when(driverRepository.existsByCin((String) any())).thenReturn(false);
        when(driverRepository.save((Driver) any())).thenReturn(mock(Driver.class));
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        driverServiceImpl.create(new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.ofEpochDay(1L)));
    }

    /**
     * Method under test: {@link DriverServiceImpl#getById(String)}
     */
    @Test
    void testGetById() {
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
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        DriverDtoResponse actualById = driverServiceImpl.getById("Cin");
        verify(driverRepository).findById(Mockito.<String>any());
        assertEquals("1970-01-01", actualById.dateOfBirth().toString());
        assertEquals("Cin", actualById.cin());
        assertEquals("Doe", actualById.lastName());
        assertEquals("Jane", actualById.firstName());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getById(String)}
     */
    @Test
    void testGetById2() {
        Optional<Driver> emptyResult = Optional.empty();
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.getById("Cin"));
        verify(driverRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getById(String)}
     */
    @Test
    void testGetById3() {
        when(driverRepository.findById(Mockito.<String>any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class, () -> driverServiceImpl.getById("Cin"));
        verify(driverRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getById(String)}
     */
    @Test
    void testGetById4() {
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        DriverDtoResponse actualById = driverServiceImpl.getById("Cin");
        assertNull(actualById.cin());
        assertNull(actualById.lastName());
        assertNull(actualById.firstName());
        assertNull(actualById.dateOfBirth());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getById(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetById5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.gl.parcauto.exception.ResourceNotFoundException: Resource Name not found with Field Name : '42'
        //       at com.gl.parcauto.mapper.DriverMapperImpl.driverToDriverDtoResponse(DriverMapperImpl.java:49)
        //       at com.gl.parcauto.service.impl.DriverServiceImpl.getById(DriverServiceImpl.java:67)
        //   See https://diff.blue/R013 to resolve this issue.

        Driver driver = mock(Driver.class);
        when(driver.getCin()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(driver.getFirstName()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(driver.getLastName()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        when(driver.getDateOfBirth()).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42"));
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        driverServiceImpl.getById("Cin");
    }

    /**
     * Method under test: {@link DriverServiceImpl#getById(String)}
     */
    @Test
    void testGetById6() {
        when(driverRepository.findById((String) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.getById("Cin"));
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getAllDrivers()}
     */
    @Test
    void testGetAllDrivers() {
        when(driverRepository.findAll()).thenReturn(new ArrayList<>());
        List<DriverDtoResponse> actualAllDrivers = driverServiceImpl.getAllDrivers();
        verify(driverRepository).findAll();
        assertTrue(actualAllDrivers.isEmpty());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getAllDrivers()}
     */
    @Test
    void testGetAllDrivers2() {
        when(driverRepository.findAll()).thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class, () -> driverServiceImpl.getAllDrivers());
        verify(driverRepository).findAll();
    }

    /**
     * Method under test: {@link DriverServiceImpl#getAllDrivers()}
     */
    @Test
    void testGetAllDrivers3() {
        when(driverRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(driverServiceImpl.getAllDrivers().isEmpty());
        verify(driverRepository).findAll();
    }

    /**
     * Method under test:
     * {@link DriverServiceImpl#getAvailableDriversBetweenDates(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetAvailableDriversBetweenDates() {
        LocalDateTime start = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(AppException.class,
                () -> driverServiceImpl.getAvailableDriversBetweenDates(start, LocalDate.of(1970, 1, 1).atStartOfDay()));
    }

    /**
     * Method under test: {@link DriverServiceImpl#getAvailableDriversBetweenDates(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetAvailableDriversBetweenDates2() {
        LocalDateTime start = LocalDateTime.of(1, 1, 1, 1, 1);
        assertThrows(AppException.class,
                () -> driverServiceImpl.getAvailableDriversBetweenDates(start, LocalDateTime.of(1, 1, 1, 1, 1)));
    }

    /**
     * Method under test: {@link DriverServiceImpl#getAvailableDriversBetweenDates(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetAvailableDriversBetweenDates3() {
        assertThrows(AppException.class,
                () -> driverServiceImpl.getAvailableDriversBetweenDates(null, LocalDateTime.of(1, 1, 1, 1, 1)));
    }

    /**
     * Method under test: {@link DriverServiceImpl#getAvailableDriversBetweenDates(LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetAvailableDriversBetweenDates4() {
        assertThrows(AppException.class,
                () -> driverServiceImpl.getAvailableDriversBetweenDates(LocalDateTime.of(1, 1, 1, 1, 1), null));
    }

    /**
     * Method under test: {@link DriverServiceImpl#getTripsOfDriver(String)}
     */
    @Test
    void testGetTripsOfDriver() {
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
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        List<TripDtoResponse> actualTripsOfDriver = driverServiceImpl.getTripsOfDriver("Cin");
        verify(driverRepository).findById(Mockito.<String>any());
        assertTrue(actualTripsOfDriver.isEmpty());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getTripsOfDriver(String)}
     */
    @Test
    void testGetTripsOfDriver2() {
        Role role = new Role();
        role.setId(1L);
        role.setName("Name");

        User user = new User();
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setRole(role);
        user.setUsername("janedoe");

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("Name");

        User user2 = new User();
        user2.setId(1L);
        user2.setPassword("iloveyou");
        user2.setRole(role2);
        user2.setUsername("janedoe");
        Driver.DriverBuilder cinResult = Driver.builder().cin("Cin");
        Driver.DriverBuilder dateOfBirthResult = cinResult.dateOfBirth(LocalDate.of(1970, 1, 1));
        Driver.DriverBuilder driverConsumptionReportsResult = dateOfBirthResult.driverConsumptionReports(new HashSet<>());
        Driver.DriverBuilder firstNameResult = driverConsumptionReportsResult.driverLicenses(new HashSet<>())
                .firstName("Jane");
        Driver.DriverBuilder lastNameResult = firstNameResult.fuelConsumptionRecords(new HashSet<>()).lastName("Doe");
        Driver.DriverBuilder userResult = lastNameResult.trips(new HashSet<>()).user(user2);
        Driver buildResult = userResult.vacations(new HashSet<>()).build();
        buildResult.setCin("Cin");
        buildResult.setDateOfBirth(LocalDate.of(1970, 1, 1));
        buildResult.setDriverConsumptionReports(new HashSet<>());
        buildResult.setDriverLicenses(new HashSet<>());
        buildResult.setFirstName("Jane");
        buildResult.setFuelConsumptionRecords(new HashSet<>());
        buildResult.setLastName("Doe");
        buildResult.setTrips(new HashSet<>());
        buildResult.setUser(user);
        buildResult.setVacations(new HashSet<>());
        Optional<Driver> ofResult = Optional.of(buildResult);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        List<TripDtoResponse> actualTripsOfDriver = driverServiceImpl.getTripsOfDriver("Cin");
        verify(driverRepository).findById(Mockito.<String>any());
        assertTrue(actualTripsOfDriver.isEmpty());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getTripsOfDriver(String)}
     */
    @Test
    void testGetTripsOfDriver3() {
        Optional<Driver> emptyResult = Optional.empty();
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.getTripsOfDriver("Cin"));
        verify(driverRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getTripsOfDriver(String)}
     */
    @Test
    void testGetTripsOfDriver4() {
        when(driverRepository.findById(Mockito.<String>any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class, () -> driverServiceImpl.getTripsOfDriver("Cin"));
        verify(driverRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getTripsOfDriver(String)}
     */
    @Test
    void testGetTripsOfDriver5() {
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        assertTrue(driverServiceImpl.getTripsOfDriver("Cin").isEmpty());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getTripsOfDriver(String)}
     */
    @Test
    void testGetTripsOfDriver6() {
        LocalDate dateOfBirth = LocalDate.ofEpochDay(1L);
        HashSet<DriverLicense> driverLicenses = new HashSet<>();
        HashSet<Vacation> vacations = new HashSet<>();
        HashSet<Trip> trips = new HashSet<>();
        HashSet<FuelConsumptionRecord> fuelConsumptionRecords = new HashSet<>();
        HashSet<DriverConsumptionReport> driverConsumptionReports = new HashSet<>();
        when(driverRepository.findById((String) any()))
                .thenReturn(Optional.of(new Driver("Cin", "Jane", "Doe", dateOfBirth, driverLicenses, vacations, trips,
                        fuelConsumptionRecords, driverConsumptionReports, new User())));
        assertTrue(driverServiceImpl.getTripsOfDriver("Cin").isEmpty());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getTripsOfDriver(String)}
     */
    @Test
    void testGetTripsOfDriver7() {
        Driver driver = mock(Driver.class);
        when(driver.getTrips()).thenReturn(new HashSet<>());
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        assertTrue(driverServiceImpl.getTripsOfDriver("Cin").isEmpty());
        verify(driverRepository).findById((String) any());
        verify(driver).getTrips();
    }

    /**
     * Method under test: {@link DriverServiceImpl#getTripsOfDriver(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTripsOfDriver8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.orElseThrow(java.util.function.Supplier)" because the return value of "com.gl.parcauto.repository.DriverRepository.findById(Object)" is null
        //       at com.gl.parcauto.service.impl.DriverServiceImpl.getTripsOfDriver(DriverServiceImpl.java:89)
        //   See https://diff.blue/R013 to resolve this issue.

        when(driverRepository.findById((String) any())).thenReturn(null);
        driverServiceImpl.getTripsOfDriver("Cin");
    }

    /**
     * Method under test: {@link DriverServiceImpl#getTripsOfDriver(String)}
     */
    @Test
    void testGetTripsOfDriver9() {
        when(driverRepository.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.getTripsOfDriver("Cin"));
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#getTripsOfDriver(String)}
     */
    @Test
    void testGetTripsOfDriver10() {
        Driver driver = mock(Driver.class);
        when(driver.getTrips()).thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        assertThrows(AppException.class, () -> driverServiceImpl.getTripsOfDriver("Cin"));
        verify(driverRepository).findById((String) any());
        verify(driver).getTrips();
    }

    /**
     * Method under test:
     * {@link DriverServiceImpl#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    void testUpdateUserAccountForDriver() {
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
        when(driverRepository.save(Mockito.<Driver>any())).thenReturn(driver2);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Role role3 = new Role();
        role3.setId(1L);
        role3.setName("Name");

        User user3 = new User();
        user3.setId(1L);
        user3.setPassword("iloveyou");
        user3.setRole(role3);
        user3.setUsername("janedoe");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user3);

        Role role4 = new Role();
        role4.setId(1L);
        role4.setName("Name");
        Optional<Role> ofResult2 = Optional.of(role4);
        when(roleRepository.findByName(Mockito.<String>any())).thenReturn(ofResult2);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        DriverUserDtoResponse actualUpdateUserAccountForDriverResult = driverServiceImpl.updateUserAccountForDriver("Cin",
                new UserDtoRequest("janedoe", "iloveyou", new RoleDtoRequest("Name")));
        verify(roleRepository).findByName(Mockito.<String>any());
        verify(driverRepository).findById(Mockito.<String>any());
        verify(driverRepository).save(Mockito.<Driver>any());
        verify(userRepository).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
        assertEquals("Cin", actualUpdateUserAccountForDriverResult.cin());
        assertEquals("Doe", actualUpdateUserAccountForDriverResult.lastName());
        assertEquals("Jane", actualUpdateUserAccountForDriverResult.firstName());
        assertEquals("Name", actualUpdateUserAccountForDriverResult.roleName());
        assertEquals("janedoe", actualUpdateUserAccountForDriverResult.username());
        assertEquals(1L, actualUpdateUserAccountForDriverResult.userId().longValue());
    }

    /**
     * Method under test:
     * {@link DriverServiceImpl#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    void testUpdateUserAccountForDriver2() {
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
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("Name");
        Optional<Role> ofResult2 = Optional.of(role2);
        when(roleRepository.findByName(Mockito.<String>any())).thenReturn(ofResult2);
        when(passwordEncoder.encode(Mockito.<CharSequence>any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class, () -> driverServiceImpl.updateUserAccountForDriver("Cin",
                new UserDtoRequest("janedoe", "iloveyou", new RoleDtoRequest("Name"))));
        verify(roleRepository).findByName(Mockito.<String>any());
        verify(driverRepository).findById(Mockito.<String>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test:
     * {@link DriverServiceImpl#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    void testUpdateUserAccountForDriver3() {
        Optional<Driver> emptyResult = Optional.empty();
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.updateUserAccountForDriver("Cin",
                new UserDtoRequest("janedoe", "iloveyou", new RoleDtoRequest("Name"))));
        verify(driverRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    void testUpdateUserAccountForDriver4() {
        when(driverRepository.save((Driver) any())).thenReturn(new Driver());
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        when(userRepository.save((User) any())).thenReturn(new User());
        when(roleRepository.findByName((String) any())).thenReturn(Optional.of(new Role()));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        DriverUserDtoResponse actualUpdateUserAccountForDriverResult = driverServiceImpl.updateUserAccountForDriver("Cin",
                new UserDtoRequest("janedoe", "iloveyou", new RoleDtoRequest("Name")));
        assertNull(actualUpdateUserAccountForDriverResult.cin());
        assertNull(actualUpdateUserAccountForDriverResult.username());
        assertNull(actualUpdateUserAccountForDriverResult.userId());
        assertNull(actualUpdateUserAccountForDriverResult.roleName());
        assertNull(actualUpdateUserAccountForDriverResult.lastName());
        assertNull(actualUpdateUserAccountForDriverResult.firstName());
        verify(driverRepository).save((Driver) any());
        verify(driverRepository).findById((String) any());
        verify(userRepository).save((User) any());
        verify(roleRepository).findByName((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    void testUpdateUserAccountForDriver5() {
        when(driverRepository.save((Driver) any())).thenReturn(new Driver());
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        when(userRepository.save((User) any())).thenReturn(new User());
        when(roleRepository.findByName((String) any())).thenReturn(Optional.of(new Role()));
        when(passwordEncoder.encode((CharSequence) any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        assertThrows(AppException.class, () -> driverServiceImpl.updateUserAccountForDriver("Cin",
                new UserDtoRequest("janedoe", "iloveyou", new RoleDtoRequest("Name"))));
        verify(driverRepository).findById((String) any());
        verify(roleRepository).findByName((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserAccountForDriver6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.gl.parcauto.exception.ResourceNotFoundException: Resource Name not found with Field Name : '42'
        //       at com.gl.parcauto.mapper.DriverMapperImpl.driverUserId(DriverMapperImpl.java:101)
        //       at com.gl.parcauto.mapper.DriverMapperImpl.driverToDriverUserDtoResponse(DriverMapperImpl.java:72)
        //       at com.gl.parcauto.service.impl.DriverServiceImpl.updateUserAccountForDriver(DriverServiceImpl.java:137)
        //   See https://diff.blue/R013 to resolve this issue.

        when(driverRepository.save((Driver) any())).thenReturn(mock(Driver.class));
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        when(userRepository.save((User) any())).thenReturn(new User());
        when(roleRepository.findByName((String) any())).thenReturn(Optional.of(new Role()));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        driverServiceImpl.updateUserAccountForDriver("Cin",
                new UserDtoRequest("janedoe", "iloveyou", new RoleDtoRequest("Name")));
    }

    /**
     * Method under test: {@link DriverServiceImpl#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    void testUpdateUserAccountForDriver7() {
        Driver driver = mock(Driver.class);
        doThrow(new AppException(HttpStatus.CONTINUE, "An error occurred")).when(driver).setUser((User) any());
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.save((Driver) any())).thenReturn(mock(Driver.class));
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        when(userRepository.save((User) any())).thenReturn(new User());
        when(roleRepository.findByName((String) any())).thenReturn(Optional.of(new Role()));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertThrows(AppException.class, () -> driverServiceImpl.updateUserAccountForDriver("Cin",
                new UserDtoRequest("janedoe", "iloveyou", new RoleDtoRequest("Name"))));
        verify(driverRepository).findById((String) any());
        verify(driver).setUser((User) any());
        verify(userRepository).save((User) any());
        verify(roleRepository).findByName((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    void testUpdateUserAccountForDriver8() {
        when(driverRepository.save((Driver) any())).thenReturn(mock(Driver.class));
        when(driverRepository.findById((String) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new AppException(HttpStatus.CONTINUE, "An error occurred");

        when(userRepository.save((User) any())).thenReturn(new User());
        when(roleRepository.findByName((String) any())).thenReturn(Optional.of(new Role()));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.updateUserAccountForDriver("Cin",
                new UserDtoRequest("janedoe", "iloveyou", new RoleDtoRequest("Name"))));
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    void testUpdateUserAccountForDriver9() {
        Driver driver = mock(Driver.class);
        doThrow(new AppException(HttpStatus.CONTINUE, "An error occurred")).when(driver).setUser((User) any());
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.save((Driver) any())).thenReturn(mock(Driver.class));
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        when(userRepository.save((User) any())).thenReturn(new User());
        when(roleRepository.findByName((String) any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.updateUserAccountForDriver("Cin",
                new UserDtoRequest("janedoe", "iloveyou", new RoleDtoRequest("Name"))));
        verify(driverRepository).findById((String) any());
        verify(roleRepository).findByName((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserAccountForDriver10() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.dto.request.RoleDtoRequest.name()" because the return value of "com.gl.parcauto.dto.request.UserDtoRequest.role()" is null
        //       at com.gl.parcauto.service.impl.DriverServiceImpl.updateUserAccountForDriver(DriverServiceImpl.java:115)
        //   See https://diff.blue/R013 to resolve this issue.

        Driver driver = mock(Driver.class);
        doThrow(new AppException(HttpStatus.CONTINUE, "An error occurred")).when(driver).setUser((User) any());
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.save((Driver) any())).thenReturn(mock(Driver.class));
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        when(userRepository.save((User) any())).thenReturn(new User());
        when(roleRepository.findByName((String) any())).thenReturn(Optional.of(new Role()));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        driverServiceImpl.updateUserAccountForDriver("Cin", new UserDtoRequest("janedoe", "iloveyou", null));
    }

    /**
     * Method under test: {@link DriverServiceImpl#updateUserAccountForDriver(String, UserDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserAccountForDriver11() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.gl.parcauto.dto.request.UserDtoRequest.role()" because "userDtoRequest" is null
        //       at com.gl.parcauto.service.impl.DriverServiceImpl.updateUserAccountForDriver(DriverServiceImpl.java:115)
        //   See https://diff.blue/R013 to resolve this issue.

        Driver driver = mock(Driver.class);
        doThrow(new AppException(HttpStatus.CONTINUE, "An error occurred")).when(driver).setUser((User) any());
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.save((Driver) any())).thenReturn(mock(Driver.class));
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        when(userRepository.save((User) any())).thenReturn(new User());
        when(roleRepository.findByName((String) any())).thenReturn(Optional.of(new Role()));
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        driverServiceImpl.updateUserAccountForDriver("Cin", null);
    }

    /**
     * Method under test: {@link DriverServiceImpl#deleteDriverUserAccount(String)}
     */
    @Test
    void testDeleteDriverUserAccount() {
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
        when(driverRepository.save(Mockito.<Driver>any())).thenReturn(driver2);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Role role3 = new Role();
        role3.setId(1L);
        role3.setName("Name");

        User user3 = new User();
        user3.setId(1L);
        user3.setPassword("iloveyou");
        user3.setRole(role3);
        user3.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user3);
        doNothing().when(userRepository).delete(Mockito.<User>any());
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        driverServiceImpl.deleteDriverUserAccount("Cin");
        verify(userRepository).delete(Mockito.<User>any());
        verify(driverRepository).findById(Mockito.<String>any());
        verify(userRepository).findById(Mockito.<Long>any());
        verify(driverRepository).save(Mockito.<Driver>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#deleteDriverUserAccount(String)}
     */
    @Test
    void testDeleteDriverUserAccount2() {
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
        when(driverRepository.save(Mockito.<Driver>any())).thenReturn(driver2);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Role role3 = new Role();
        role3.setId(1L);
        role3.setName("Name");

        User user3 = new User();
        user3.setId(1L);
        user3.setPassword("iloveyou");
        user3.setRole(role3);
        user3.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user3);
        doThrow(new AppException(HttpStatus.CONTINUE, "An error occurred")).when(userRepository)
                .delete(Mockito.<User>any());
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        assertThrows(AppException.class, () -> driverServiceImpl.deleteDriverUserAccount("Cin"));
        verify(userRepository).delete(Mockito.<User>any());
        verify(driverRepository).findById(Mockito.<String>any());
        verify(userRepository).findById(Mockito.<Long>any());
        verify(driverRepository).save(Mockito.<Driver>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#deleteDriverUserAccount(String)}
     */
    @Test
    void testDeleteDriverUserAccount3() {
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        assertThrows(AppException.class, () -> driverServiceImpl.deleteDriverUserAccount("Cin"));
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#deleteDriverUserAccount(String)}
     */
    @Test
    void testDeleteDriverUserAccount4() {
        when(driverRepository.save((Driver) any())).thenReturn(new Driver());
        LocalDate dateOfBirth = LocalDate.ofEpochDay(1L);
        HashSet<DriverLicense> driverLicenses = new HashSet<>();
        HashSet<Vacation> vacations = new HashSet<>();
        HashSet<Trip> trips = new HashSet<>();
        HashSet<FuelConsumptionRecord> fuelConsumptionRecords = new HashSet<>();
        HashSet<DriverConsumptionReport> driverConsumptionReports = new HashSet<>();
        when(driverRepository.findById((String) any()))
                .thenReturn(Optional.of(new Driver("User not found for driver with ID %s", "Jane", "Doe", dateOfBirth,
                        driverLicenses, vacations, trips, fuelConsumptionRecords, driverConsumptionReports, new User())));
        doNothing().when(userRepository).delete((User) any());
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(new User()));
        driverServiceImpl.deleteDriverUserAccount("Cin");
        verify(driverRepository).save((Driver) any());
        verify(driverRepository).findById((String) any());
        verify(userRepository).findById((Long) any());
        verify(userRepository).delete((User) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#deleteDriverUserAccount(String)}
     */
    @Test
    void testDeleteDriverUserAccount5() {
        when(driverRepository.save((Driver) any())).thenReturn(new Driver());
        LocalDate dateOfBirth = LocalDate.ofEpochDay(1L);
        HashSet<DriverLicense> driverLicenses = new HashSet<>();
        HashSet<Vacation> vacations = new HashSet<>();
        HashSet<Trip> trips = new HashSet<>();
        HashSet<FuelConsumptionRecord> fuelConsumptionRecords = new HashSet<>();
        HashSet<DriverConsumptionReport> driverConsumptionReports = new HashSet<>();
        when(driverRepository.findById((String) any()))
                .thenReturn(Optional.of(new Driver("User not found for driver with ID %s", "Jane", "Doe", dateOfBirth,
                        driverLicenses, vacations, trips, fuelConsumptionRecords, driverConsumptionReports, new User())));
        doThrow(new AppException(HttpStatus.CONTINUE, "An error occurred")).when(userRepository).delete((User) any());
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(new User()));
        assertThrows(AppException.class, () -> driverServiceImpl.deleteDriverUserAccount("Cin"));
        verify(driverRepository).save((Driver) any());
        verify(driverRepository).findById((String) any());
        verify(userRepository).findById((Long) any());
        verify(userRepository).delete((User) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#deleteDriverUserAccount(String)}
     */
    @Test
    void testDeleteDriverUserAccount6() {
        User user = mock(User.class);
        when(user.getId()).thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        LocalDate dateOfBirth = LocalDate.ofEpochDay(1L);
        HashSet<DriverLicense> driverLicenses = new HashSet<>();
        HashSet<Vacation> vacations = new HashSet<>();
        HashSet<Trip> trips = new HashSet<>();
        HashSet<FuelConsumptionRecord> fuelConsumptionRecords = new HashSet<>();
        Optional<Driver> ofResult = Optional.of(new Driver("User not found for driver with ID %s", "Jane", "Doe",
                dateOfBirth, driverLicenses, vacations, trips, fuelConsumptionRecords, new HashSet<>(), user));
        when(driverRepository.save((Driver) any())).thenReturn(new Driver());
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        doNothing().when(userRepository).delete((User) any());
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(new User()));
        assertThrows(AppException.class, () -> driverServiceImpl.deleteDriverUserAccount("Cin"));
        verify(driverRepository).findById((String) any());
        verify(user).getId();
    }

    /**
     * Method under test: {@link DriverServiceImpl#deleteDriverUserAccount(String)}
     */
    @Test
    void testDeleteDriverUserAccount7() {
        Driver driver = mock(Driver.class);
        when(driver.getUser()).thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.save((Driver) any())).thenReturn(new Driver());
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        new AppException(HttpStatus.CONTINUE, "An error occurred");

        doNothing().when(userRepository).delete((User) any());
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(new User()));
        assertThrows(AppException.class, () -> driverServiceImpl.deleteDriverUserAccount("Cin"));
        verify(driverRepository).findById((String) any());
        verify(driver).getUser();
    }

    /**
     * Method under test: {@link DriverServiceImpl#deleteDriverUserAccount(String)}
     */
    @Test
    void testDeleteDriverUserAccount8() {
        when(driverRepository.save((Driver) any())).thenReturn(new Driver());
        when(driverRepository.findById((String) any())).thenReturn(Optional.empty());
        new AppException(HttpStatus.CONTINUE, "An error occurred");

        new AppException(HttpStatus.CONTINUE, "An error occurred");

        doNothing().when(userRepository).delete((User) any());
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(new User()));
        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.deleteDriverUserAccount("Cin"));
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate() {
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
        when(driverRepository.save(Mockito.<Driver>any())).thenReturn(driver2);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        DriverDtoResponse actualUpdateResult = driverServiceImpl.update("Cin",
                new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.of(1970, 1, 1)));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(driverRepository).save(Mockito.<Driver>any());
        assertEquals("1970-01-01", actualUpdateResult.dateOfBirth().toString());
        assertEquals("Cin", actualUpdateResult.cin());
        assertEquals("Doe", actualUpdateResult.lastName());
        assertEquals("Jane", actualUpdateResult.firstName());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate2() {
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
        when(driverRepository.save(Mockito.<Driver>any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(AppException.class,
                () -> driverServiceImpl.update("Cin", new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.of(1970, 1, 1))));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(driverRepository).save(Mockito.<Driver>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate3() {
        Optional<Driver> emptyResult = Optional.empty();
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class,
                () -> driverServiceImpl.update("Cin", new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.of(1970, 1, 1))));
        verify(driverRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate4() {
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
        when(driverRepository.save(Mockito.<Driver>any())).thenReturn(driver2);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        DriverDtoResponse actualUpdateResult = driverServiceImpl.update("Cin",
                new DriverDtoRequest(null, "Jane", "Doe", LocalDate.of(1970, 1, 1)));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(driverRepository).save(Mockito.<Driver>any());
        assertEquals("1970-01-01", actualUpdateResult.dateOfBirth().toString());
        assertEquals("Cin", actualUpdateResult.cin());
        assertEquals("Doe", actualUpdateResult.lastName());
        assertEquals("Jane", actualUpdateResult.firstName());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate5() {
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
        when(driverRepository.save(Mockito.<Driver>any())).thenReturn(driver2);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        DriverDtoResponse actualUpdateResult = driverServiceImpl.update("Cin",
                new DriverDtoRequest("", "Jane", "Doe", LocalDate.of(1970, 1, 1)));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(driverRepository).save(Mockito.<Driver>any());
        assertEquals("1970-01-01", actualUpdateResult.dateOfBirth().toString());
        assertEquals("Cin", actualUpdateResult.cin());
        assertEquals("Doe", actualUpdateResult.lastName());
        assertEquals("Jane", actualUpdateResult.firstName());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate6() {
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
        when(driverRepository.save(Mockito.<Driver>any())).thenReturn(driver2);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        DriverDtoResponse actualUpdateResult = driverServiceImpl.update("Cin",
                new DriverDtoRequest("Cin", null, "Doe", LocalDate.of(1970, 1, 1)));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(driverRepository).save(Mockito.<Driver>any());
        assertEquals("1970-01-01", actualUpdateResult.dateOfBirth().toString());
        assertEquals("Cin", actualUpdateResult.cin());
        assertEquals("Doe", actualUpdateResult.lastName());
        assertEquals("Jane", actualUpdateResult.firstName());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate7() {
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
        when(driverRepository.save(Mockito.<Driver>any())).thenReturn(driver2);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        DriverDtoResponse actualUpdateResult = driverServiceImpl.update("Cin",
                new DriverDtoRequest("Cin", "Jane", null, LocalDate.of(1970, 1, 1)));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(driverRepository).save(Mockito.<Driver>any());
        assertEquals("1970-01-01", actualUpdateResult.dateOfBirth().toString());
        assertEquals("Cin", actualUpdateResult.cin());
        assertEquals("Doe", actualUpdateResult.lastName());
        assertEquals("Jane", actualUpdateResult.firstName());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate8() {
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
        when(driverRepository.save(Mockito.<Driver>any())).thenReturn(driver2);
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        DriverDtoResponse actualUpdateResult = driverServiceImpl.update("Cin",
                new DriverDtoRequest("Cin", "Jane", "Doe", null));
        verify(driverRepository).findById(Mockito.<String>any());
        verify(driverRepository).save(Mockito.<Driver>any());
        assertEquals("1970-01-01", actualUpdateResult.dateOfBirth().toString());
        assertEquals("Cin", actualUpdateResult.cin());
        assertEquals("Doe", actualUpdateResult.lastName());
        assertEquals("Jane", actualUpdateResult.firstName());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate9() {
        when(driverRepository.save((Driver) any())).thenReturn(new Driver());
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        DriverDtoResponse actualUpdateResult = driverServiceImpl.update("Cin",
                new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.ofEpochDay(1L)));
        assertNull(actualUpdateResult.cin());
        assertNull(actualUpdateResult.lastName());
        assertNull(actualUpdateResult.firstName());
        assertNull(actualUpdateResult.dateOfBirth());
        verify(driverRepository).save((Driver) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate10() {
        when(driverRepository.save((Driver) any())).thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        assertThrows(AppException.class,
                () -> driverServiceImpl.update("Cin", new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.ofEpochDay(1L))));
        verify(driverRepository).save((Driver) any());
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate11() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.gl.parcauto.exception.ResourceNotFoundException: Resource Name not found with Field Name : '42'
        //       at com.gl.parcauto.mapper.DriverMapperImpl.driverToDriverDtoResponse(DriverMapperImpl.java:49)
        //       at com.gl.parcauto.service.impl.DriverServiceImpl.update(DriverServiceImpl.java:200)
        //   See https://diff.blue/R013 to resolve this issue.

        when(driverRepository.save((Driver) any())).thenReturn(mock(Driver.class));
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        driverServiceImpl.update("Cin", new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.ofEpochDay(1L)));
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate12() {
        Driver driver = mock(Driver.class);
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42")).when(driver).setCin((String) any());
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42")).when(driver)
                .setDateOfBirth((LocalDate) any());
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42")).when(driver)
                .setFirstName((String) any());
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42")).when(driver)
                .setLastName((String) any());
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.save((Driver) any())).thenReturn(mock(Driver.class));
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        assertThrows(ResourceNotFoundException.class,
                () -> driverServiceImpl.update("Cin", new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.ofEpochDay(1L))));
        verify(driverRepository).findById((String) any());
        verify(driver).setCin((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate13() {
        when(driverRepository.save((Driver) any())).thenReturn(mock(Driver.class));
        when(driverRepository.findById((String) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        assertThrows(ResourceNotFoundException.class,
                () -> driverServiceImpl.update("Cin", new DriverDtoRequest("Cin", "Jane", "Doe", LocalDate.ofEpochDay(1L))));
        verify(driverRepository).findById((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#update(String, DriverDtoRequest)}
     */
    @Test
    void testUpdate14() {
        Driver driver = mock(Driver.class);
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42")).when(driver).setCin((String) any());
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42")).when(driver)
                .setDateOfBirth((LocalDate) any());
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42")).when(driver)
                .setFirstName((String) any());
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", "42")).when(driver)
                .setLastName((String) any());
        Optional<Driver> ofResult = Optional.of(driver);
        when(driverRepository.save((Driver) any())).thenReturn(mock(Driver.class));
        when(driverRepository.findById((String) any())).thenReturn(ofResult);
        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        new ResourceNotFoundException("Resource Name", "Field Name", "42");

        assertThrows(ResourceNotFoundException.class,
                () -> driverServiceImpl.update("Cin", new DriverDtoRequest("", "Jane", "Doe", LocalDate.ofEpochDay(1L))));
        verify(driverRepository).findById((String) any());
        verify(driver).setFirstName((String) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#delete(String)}
     */
    @Test
    void testDelete() {
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
        doNothing().when(driverRepository).delete(Mockito.<Driver>any());
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        driverServiceImpl.delete("Cin");
        verify(driverRepository).delete(Mockito.<Driver>any());
        verify(driverRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#delete(String)}
     */
    @Test
    void testDelete2() {
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
        doThrow(new AppException(HttpStatus.CONTINUE, "An error occurred")).when(driverRepository)
                .delete(Mockito.<Driver>any());
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(AppException.class, () -> driverServiceImpl.delete("Cin"));
        verify(driverRepository).delete(Mockito.<Driver>any());
        verify(driverRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#delete(String)}
     */
    @Test
    void testDelete3() {
        Optional<Driver> emptyResult = Optional.empty();
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.delete("Cin"));
        verify(driverRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#delete(String)}
     */
    @Test
    void testDelete4() {
        doNothing().when(driverRepository).delete((Driver) any());
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        driverServiceImpl.delete("Cin");
        verify(driverRepository).findById((String) any());
        verify(driverRepository).delete((Driver) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#delete(String)}
     */
    @Test
    void testDelete5() {
        doThrow(new AppException(HttpStatus.CONTINUE, "An error occurred")).when(driverRepository).delete((Driver) any());
        when(driverRepository.findById((String) any())).thenReturn(Optional.of(new Driver()));
        assertThrows(AppException.class, () -> driverServiceImpl.delete("Cin"));
        verify(driverRepository).findById((String) any());
        verify(driverRepository).delete((Driver) any());
    }

    /**
     * Method under test: {@link DriverServiceImpl#delete(String)}
     */
    @Test
    void testDelete6() {
        doNothing().when(driverRepository).delete((Driver) any());
        when(driverRepository.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.delete("Cin"));
        verify(driverRepository).findById((String) any());
    }
}
