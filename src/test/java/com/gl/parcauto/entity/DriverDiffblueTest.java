package com.gl.parcauto.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class DriverDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Driver#Driver()}
     *   <li>{@link Driver#setCin(String)}
     *   <li>{@link Driver#setDateOfBirth(LocalDate)}
     *   <li>{@link Driver#setDriverConsumptionReports(Set)}
     *   <li>{@link Driver#setDriverLicenses(Set)}
     *   <li>{@link Driver#setFirstName(String)}
     *   <li>{@link Driver#setFuelConsumptionRecords(Set)}
     *   <li>{@link Driver#setLastName(String)}
     *   <li>{@link Driver#setTrips(Set)}
     *   <li>{@link Driver#setUser(User)}
     *   <li>{@link Driver#setVacations(Set)}
     *   <li>{@link Driver#getCin()}
     *   <li>{@link Driver#getDateOfBirth()}
     *   <li>{@link Driver#getDriverConsumptionReports()}
     *   <li>{@link Driver#getDriverLicenses()}
     *   <li>{@link Driver#getFirstName()}
     *   <li>{@link Driver#getFuelConsumptionRecords()}
     *   <li>{@link Driver#getLastName()}
     *   <li>{@link Driver#getTrips()}
     *   <li>{@link Driver#getUser()}
     *   <li>{@link Driver#getVacations()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Driver actualDriver = new Driver();
        actualDriver.setCin("Cin");
        LocalDate dateOfBirth = LocalDate.of(1970, 1, 1);
        actualDriver.setDateOfBirth(dateOfBirth);
        HashSet<DriverConsumptionReport> driverConsumptionReports = new HashSet<>();
        actualDriver.setDriverConsumptionReports(driverConsumptionReports);
        HashSet<DriverLicense> driverLicenses = new HashSet<>();
        actualDriver.setDriverLicenses(driverLicenses);
        actualDriver.setFirstName("Jane");
        HashSet<FuelConsumptionRecord> fuelConsumptionRecords = new HashSet<>();
        actualDriver.setFuelConsumptionRecords(fuelConsumptionRecords);
        actualDriver.setLastName("Doe");
        HashSet<Trip> trips = new HashSet<>();
        actualDriver.setTrips(trips);
        Role role = new Role();
        role.setId(1L);
        role.setName("Name");
        User user = new User();
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setRole(role);
        user.setUsername("janedoe");
        actualDriver.setUser(user);
        HashSet<Vacation> vacations = new HashSet<>();
        actualDriver.setVacations(vacations);
        String actualCin = actualDriver.getCin();
        LocalDate actualDateOfBirth = actualDriver.getDateOfBirth();
        Set<DriverConsumptionReport> actualDriverConsumptionReports = actualDriver.getDriverConsumptionReports();
        Set<DriverLicense> actualDriverLicenses = actualDriver.getDriverLicenses();
        String actualFirstName = actualDriver.getFirstName();
        Set<FuelConsumptionRecord> actualFuelConsumptionRecords = actualDriver.getFuelConsumptionRecords();
        String actualLastName = actualDriver.getLastName();
        Set<Trip> actualTrips = actualDriver.getTrips();
        User actualUser = actualDriver.getUser();
        assertEquals("Cin", actualCin);
        assertEquals("Doe", actualLastName);
        assertEquals("Jane", actualFirstName);
        assertSame(user, actualUser);
        assertSame(driverConsumptionReports, actualDriverConsumptionReports);
        assertSame(driverLicenses, actualDriverLicenses);
        assertSame(fuelConsumptionRecords, actualFuelConsumptionRecords);
        assertSame(trips, actualTrips);
        assertSame(vacations, actualDriver.getVacations());
        assertSame(dateOfBirth, actualDateOfBirth);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link Driver#Driver(String, String, String, LocalDate, Set, Set, Set, Set, Set, User)}
     *   <li>{@link Driver#setCin(String)}
     *   <li>{@link Driver#setDateOfBirth(LocalDate)}
     *   <li>{@link Driver#setDriverConsumptionReports(Set)}
     *   <li>{@link Driver#setDriverLicenses(Set)}
     *   <li>{@link Driver#setFirstName(String)}
     *   <li>{@link Driver#setFuelConsumptionRecords(Set)}
     *   <li>{@link Driver#setLastName(String)}
     *   <li>{@link Driver#setTrips(Set)}
     *   <li>{@link Driver#setUser(User)}
     *   <li>{@link Driver#setVacations(Set)}
     *   <li>{@link Driver#getCin()}
     *   <li>{@link Driver#getDateOfBirth()}
     *   <li>{@link Driver#getDriverConsumptionReports()}
     *   <li>{@link Driver#getDriverLicenses()}
     *   <li>{@link Driver#getFirstName()}
     *   <li>{@link Driver#getFuelConsumptionRecords()}
     *   <li>{@link Driver#getLastName()}
     *   <li>{@link Driver#getTrips()}
     *   <li>{@link Driver#getUser()}
     *   <li>{@link Driver#getVacations()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        LocalDate dateOfBirth = LocalDate.of(1970, 1, 1);
        HashSet<DriverLicense> driverLicenses = new HashSet<>();
        HashSet<Vacation> vacations = new HashSet<>();
        HashSet<Trip> trips = new HashSet<>();
        HashSet<FuelConsumptionRecord> fuelConsumptionRecords = new HashSet<>();
        HashSet<DriverConsumptionReport> driverConsumptionReports = new HashSet<>();

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");

        User user = new User();
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setRole(role);
        user.setUsername("janedoe");
        Driver actualDriver = new Driver("Cin", "Jane", "Doe", dateOfBirth, driverLicenses, vacations, trips,
                fuelConsumptionRecords, driverConsumptionReports, user);
        actualDriver.setCin("Cin");
        LocalDate dateOfBirth2 = LocalDate.of(1970, 1, 1);
        actualDriver.setDateOfBirth(dateOfBirth2);
        HashSet<DriverConsumptionReport> driverConsumptionReports2 = new HashSet<>();
        actualDriver.setDriverConsumptionReports(driverConsumptionReports2);
        HashSet<DriverLicense> driverLicenses2 = new HashSet<>();
        actualDriver.setDriverLicenses(driverLicenses2);
        actualDriver.setFirstName("Jane");
        HashSet<FuelConsumptionRecord> fuelConsumptionRecords2 = new HashSet<>();
        actualDriver.setFuelConsumptionRecords(fuelConsumptionRecords2);
        actualDriver.setLastName("Doe");
        HashSet<Trip> trips2 = new HashSet<>();
        actualDriver.setTrips(trips2);
        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("Name");
        User user2 = new User();
        user2.setId(1L);
        user2.setPassword("iloveyou");
        user2.setRole(role2);
        user2.setUsername("janedoe");
        actualDriver.setUser(user2);
        HashSet<Vacation> vacations2 = new HashSet<>();
        actualDriver.setVacations(vacations2);
        String actualCin = actualDriver.getCin();
        LocalDate actualDateOfBirth = actualDriver.getDateOfBirth();
        Set<DriverConsumptionReport> actualDriverConsumptionReports = actualDriver.getDriverConsumptionReports();
        Set<DriverLicense> actualDriverLicenses = actualDriver.getDriverLicenses();
        String actualFirstName = actualDriver.getFirstName();
        Set<FuelConsumptionRecord> actualFuelConsumptionRecords = actualDriver.getFuelConsumptionRecords();
        String actualLastName = actualDriver.getLastName();
        Set<Trip> actualTrips = actualDriver.getTrips();
        User actualUser = actualDriver.getUser();
        assertEquals("Cin", actualCin);
        assertEquals("Doe", actualLastName);
        assertEquals("Jane", actualFirstName);
        assertSame(user2, actualUser);
        assertSame(driverConsumptionReports2, actualDriverConsumptionReports);
        assertSame(driverLicenses2, actualDriverLicenses);
        assertSame(fuelConsumptionRecords2, actualFuelConsumptionRecords);
        assertSame(trips2, actualTrips);
        assertSame(vacations2, actualDriver.getVacations());
        assertSame(dateOfBirth2, actualDateOfBirth);
    }
}
