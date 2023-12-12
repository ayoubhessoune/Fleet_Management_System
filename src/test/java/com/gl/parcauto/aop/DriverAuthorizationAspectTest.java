package com.gl.parcauto.aop;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gl.parcauto.auth.IAuthenticationFacade;
import com.gl.parcauto.entity.Driver;
import com.gl.parcauto.entity.Role;
import com.gl.parcauto.entity.User;
import com.gl.parcauto.exception.AppException;
import com.gl.parcauto.exception.ResourceNotFoundException;
import com.gl.parcauto.repository.DriverRepository;
import com.gl.parcauto.repository.UserRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DriverAuthorizationAspect.class})
@ExtendWith(SpringExtension.class)
class DriverAuthorizationAspectTest {
    @Autowired
    private DriverAuthorizationAspect driverAuthorizationAspect;

    @MockBean
    private DriverRepository driverRepository;

    @MockBean
    private IAuthenticationFacade iAuthenticationFacade;

    @MockBean
    private UserRepository userRepository;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link DriverAuthorizationAspect#getDriverById()}
     *   <li>{@link DriverAuthorizationAspect#getLicenseById()}
     *   <li>{@link DriverAuthorizationAspect#getLicensesForDriver()}
     *   <li>{@link DriverAuthorizationAspect#getTripsOfDriver()}
     *   <li>{@link DriverAuthorizationAspect#getVacationById()}
     *   <li>{@link DriverAuthorizationAspect#getVacationsForDriver()}
     *   <li>{@link DriverAuthorizationAspect#updateDriver()}
     * </ul>
     */
    @Test
    void testGetDriverById() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     DriverAuthorizationAspect.authenticationFacade
        //     DriverAuthorizationAspect.driverRepository
        //     DriverAuthorizationAspect.userRepository

        DriverAuthorizationAspect driverAuthorizationAspect = new DriverAuthorizationAspect(mock(DriverRepository.class),
                mock(UserRepository.class), mock(IAuthenticationFacade.class));
        driverAuthorizationAspect.getDriverById();
        driverAuthorizationAspect.getLicenseById();
        driverAuthorizationAspect.getLicensesForDriver();
        driverAuthorizationAspect.getTripsOfDriver();
        driverAuthorizationAspect.getVacationById();
        driverAuthorizationAspect.getVacationsForDriver();
        driverAuthorizationAspect.updateDriver();
    }

    /**
     * Method under test:
     * {@link DriverAuthorizationAspect#checkDriverAuthorization(String)}
     */
    @Test
    void testCheckDriverAuthorization() {
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

        User user2 = new User();
        user2.setId(1L);
        user2.setPassword("iloveyou");
        user2.setRole(role2);
        user2.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user2);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult2);
        when(iAuthenticationFacade.getAuthentication())
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertThrows(AppException.class, () -> driverAuthorizationAspect.checkDriverAuthorization("Cin"));
        verify(iAuthenticationFacade).getAuthentication();
        verify(userRepository).findByUsername(Mockito.<String>any());
        verify(driverRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link DriverAuthorizationAspect#checkDriverAuthorization(String)}
     */
    @Test
    void testCheckDriverAuthorization2() {
        when(driverRepository.findById(Mockito.<String>any()))
                .thenThrow(new AppException(HttpStatus.CONTINUE, "An error occurred"));

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");

        User user = new User();
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setRole(role);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        when(iAuthenticationFacade.getAuthentication())
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertThrows(AppException.class, () -> driverAuthorizationAspect.checkDriverAuthorization("Cin"));
        verify(iAuthenticationFacade).getAuthentication();
        verify(userRepository).findByUsername(Mockito.<String>any());
        verify(driverRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link DriverAuthorizationAspect#checkDriverAuthorization(String)}
     */
    @Test
    void testCheckDriverAuthorization3() {
        Optional<Driver> emptyResult = Optional.empty();
        when(driverRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");

        User user = new User();
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setRole(role);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUsername(Mockito.<String>any())).thenReturn(ofResult);
        when(iAuthenticationFacade.getAuthentication())
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertThrows(ResourceNotFoundException.class, () -> driverAuthorizationAspect.checkDriverAuthorization("Cin"));
        verify(iAuthenticationFacade).getAuthentication();
        verify(userRepository).findByUsername(Mockito.<String>any());
        verify(driverRepository).findById(Mockito.<String>any());
    }
}
