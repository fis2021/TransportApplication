package org.loose.fis.transport.application.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.transport.application.exceptions.VehicleExists;
import org.loose.fis.transport.application.model.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VehicleServiceTest {
    public static final String ceva = "ceva";
    public static final int cevaInt = 1;
    public static final String altceva = "altceva";
    public static final int altcevaInt = 2;

    @AfterEach
    void tearDown() {
        System.out.println("After each");
        VehicleService.getdatab().close();
    }
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        VehicleService.initDatabase();
    }

    @Test
    @DisplayName("Database is initialized, and there are no vehicles")
    void testDatabaseIsInitializedAndNoVehicleIsPersisted() {
        assertThat(VehicleService.Lista()).isNotNull();
        assertThat(VehicleService.Lista()).isEmpty();
    }

    @Test
    @DisplayName("Vehicle is successfully persisted to Database")
    void testVehicleIsAddedToDatabase() throws VehicleExists {
        VehicleService.addVehicle(ceva, ceva, cevaInt, cevaInt, cevaInt);
        assertThat(VehicleService.Lista()).isNotEmpty();
        assertThat(VehicleService.Lista()).size().isEqualTo(1);
        Vehicle vehicle = VehicleService.Lista().get(0);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getVehicleType()).isEqualTo(ceva);
        assertThat(vehicle.getTransportType()).isEqualTo(ceva);
        assertThat(vehicle.getSpace()).isEqualTo(cevaInt);
        assertThat(vehicle.getNumberOfVehicles()).isEqualTo(cevaInt);
        assertThat(vehicle.getAvailableVehicles()).isEqualTo(cevaInt);
    }

    @Test
    @DisplayName("Vehicle can not be added twice")
    void testVehicleCanNotBeAddedTwice() {
        assertThrows(VehicleExists.class, () -> {
            VehicleService.addVehicle(ceva, ceva, cevaInt, cevaInt, cevaInt);
            VehicleService.addVehicle(ceva, ceva, cevaInt, cevaInt, cevaInt);
        });
    }

    @Test
    void testEditVehicle() throws VehicleExists {
        VehicleService.addVehicle(ceva, ceva, cevaInt, cevaInt, cevaInt);
        VehicleService.editVehicle(ceva, altceva, altcevaInt, altcevaInt, altcevaInt);
        assertThat(VehicleService.Lista()).isNotEmpty();
        assertThat(VehicleService.Lista()).size().isEqualTo(1);
        Vehicle vehicle = VehicleService.Lista().get(0);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getVehicleType()).isEqualTo(ceva);
        assertThat(vehicle.getTransportType()).isEqualTo(altceva);
        assertThat(vehicle.getSpace()).isEqualTo(altcevaInt);
        assertThat(vehicle.getNumberOfVehicles()).isEqualTo(altcevaInt);
        assertThat(vehicle.getAvailableVehicles()).isEqualTo(altcevaInt);
    }

    @Test
    void testDeleteVehicle() throws VehicleExists{
        VehicleService.addVehicle(ceva, ceva, cevaInt, cevaInt, cevaInt);
        VehicleService.deleteVehicle(ceva);
        assertThat(VehicleService.Lista()).isEmpty();
    }

    @Test
    void testIncrementAvailableVehicles() throws VehicleExists{
        VehicleService.addVehicle(ceva, ceva, cevaInt, cevaInt, cevaInt);
        VehicleService.incrementAvailableVehicles(ceva);
        Vehicle vehicle = VehicleService.Lista().get(0);
        assertThat(vehicle.getAvailableVehicles()).isEqualTo(cevaInt + 1);
    }

    @Test
    void testDecrementAvailableVehicles() throws VehicleExists{
        VehicleService.addVehicle(ceva, ceva, cevaInt, cevaInt, altcevaInt);
        VehicleService.decrementAvailableVehicles(ceva);
        Vehicle vehicle = VehicleService.Lista().get(0);
        assertThat(vehicle.getAvailableVehicles()).isEqualTo(altcevaInt - 1);
    }

}