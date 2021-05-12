package org.loose.fis.transport.application.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.transport.application.exceptions.VehicleExists;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.model.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TripServiceTest {
    public static final String ceva = "ceva";
    public static final int cevaInt = 1;
    public static final String altceva = "altceva";
    public static final int altcevaInt = 2;

    @AfterEach
    void tearDown() {
        System.out.println("After each");
        TripService.getdatab().close();
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
        TripService.initDatabase();
        VehicleService.initDatabase();
    }

    @Test
    @DisplayName("Database is initialized, and there are no trips")
    void testDatabaseIsInitializedAndNoTripIsPersisted() {
        assertThat(TripService.Lista()).isNotNull();
        assertThat(TripService.Lista()).isEmpty();
    }

    @Test
    @DisplayName("Trip is successfully persisted to Database")
    void testTripIsAddedToDatabase() {
        TripService.addTrip(ceva,cevaInt, ceva,ceva, cevaInt, ceva);
        assertThat(TripService.Lista()).isNotEmpty();
        assertThat(TripService.Lista()).size().isEqualTo(1);
        Trip t = TripService.Lista().get(0);
        assertThat(t).isNotNull();
        assertThat(t.getVehicleType()).isEqualTo(ceva);
        assertThat(t.getSpace()).isEqualTo(cevaInt);
        assertThat(t.getDate()).isEqualTo(ceva);
        assertThat(t.getPrice()).isEqualTo(cevaInt);
        assertThat(t.getRoute()).isEqualTo(ceva);
        assertThat(t.getTime()).isEqualTo(ceva);
    }

    @Test
    void testTripCanBeAddedTwice() {
            TripService.addTrip(ceva,cevaInt, ceva,ceva, cevaInt, ceva);
            TripService.addTrip(ceva,cevaInt, ceva,ceva, cevaInt, ceva);
            assertThat(TripService.Lista().toArray().length).isEqualTo(2);
    }

    @Test
    void testDeleteVehicle(){
        TripService.addTrip(ceva,cevaInt, ceva,ceva, cevaInt, ceva);
        TripService.deleteTrip(ceva);
        assertThat(TripService.Lista()).isEmpty();
    }
    @Test
    void testSearchById(){
        TripService.addTrip(ceva,cevaInt, ceva,ceva, cevaInt, ceva);
        Trip t=TripService.searchById(1);
        assertThat(t).isNotNull();
        assertThat(t.getVehicleType()).isEqualTo(ceva);
        assertThat(t.getSpace()).isEqualTo(cevaInt);
        assertThat(t.getDate()).isEqualTo(ceva);
        assertThat(t.getPrice()).isEqualTo(cevaInt);
        assertThat(t.getRoute()).isEqualTo(ceva);
        assertThat(t.getTime()).isEqualTo(ceva);
        assertThat(t.getId()).isEqualTo(1);
    }


}