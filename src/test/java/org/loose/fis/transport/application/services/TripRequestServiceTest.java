package org.loose.fis.transport.application.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.model.TripRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TripRequestServiceTest {
    public static final String ceva = "ceva";
    public static final int cevaInt = 1;
    public static final String altceva = "altceva";
    public static final int altcevaInt = 2;

    @AfterEach
    void tearDown() {
        System.out.println("After each");
        TripService.getdatab().close();
        TripRequestService.getdatab().close();
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
        TripRequestService.initDatabase();
        VehicleService.initDatabase();
    }

    @Test
    @DisplayName("Database is initialized, and there are no trips")
    void testDatabaseIsInitializedAndNoTripIsPersisted() {
        assertThat(TripRequestService.Lista()).isNotNull();
        assertThat(TripRequestService.Lista()).isEmpty();
    }

    @Test
    @DisplayName("Trip is successfully persisted to Database")
    void testTripRequestIsAddedToDatabase() {
        Trip k=new Trip(ceva,cevaInt,ceva,ceva,cevaInt,ceva,cevaInt);
        TripRequestService.addRequest(k,ceva);
        assertThat(TripRequestService.Lista()).isNotEmpty();
        assertThat(TripRequestService.Lista()).size().isEqualTo(1);
        TripRequest t = TripRequestService.Lista().get(0);
        assertThat(t).isNotNull();
        assertThat(t.getTrip()).isNotNull();
        assertThat(t.getTrip().getVehicleType()).isEqualTo(ceva);
        assertThat(t.getTrip().getSpace()).isEqualTo(cevaInt);
        assertThat(t.getTrip().getDate()).isEqualTo(ceva);
        assertThat(t.getTrip().getPrice()).isEqualTo(cevaInt);
        assertThat(t.getTrip().getRoute()).isEqualTo(ceva);
        assertThat(t.getTrip().getTime()).isEqualTo(ceva);
        assertThat(t.getName()).isEqualTo(ceva);
    }

    @Test
    void testApprove()
    {
        Trip k=new Trip(ceva,cevaInt,ceva,ceva,cevaInt,ceva,cevaInt);
        TripRequestService.addRequest(k,ceva);
        assertThat(TripRequestService.Lista()).isNotEmpty();
        assertThat(TripRequestService.Lista()).size().isEqualTo(1);
        TripRequestService.Approve();
        TripRequest t = TripRequestService.Lista().get(0);
        assertThat(t.getApproved()).isEqualTo(1);
    }
    @Test
    void testApproveAndNotApproved()
    {
        Trip k=new Trip(ceva,cevaInt,ceva,ceva,cevaInt,ceva,cevaInt);
        TripRequestService.addRequest(k,ceva);
        TripRequestService.addRequest(k,ceva);
        assertThat(TripRequestService.Lista()).isNotEmpty();
        assertThat(TripRequestService.Lista()).size().isEqualTo(2);
        TripRequestService.Approve();
        TripRequest t = TripRequestService.Lista().get(0);
        assertThat(t.getApproved()).isEqualTo(1);
        t = TripRequestService.Lista().get(1);
        assertThat(t.getApproved()).isEqualTo(2);
    }
    @Test
    void testDeny()
    {
        Trip k=new Trip(ceva,cevaInt,ceva,ceva,cevaInt,ceva,cevaInt);
        TripRequestService.addRequest(k,ceva);
        assertThat(TripRequestService.Lista()).isNotEmpty();
        assertThat(TripRequestService.Lista()).size().isEqualTo(1);
        TripRequestService.Deny();
        TripRequest t = TripRequestService.Lista().get(0);
        assertThat(t.getApproved()).isEqualTo(0);
    }
    @Test
    void testApprovedAndDenied()
    {
        Trip k=new Trip(ceva,cevaInt,ceva,ceva,cevaInt,ceva,cevaInt);
        TripRequestService.addRequest(k,ceva);
        TripRequestService.addRequest(k,ceva);
        assertThat(TripRequestService.Lista()).isNotEmpty();
        assertThat(TripRequestService.Lista()).size().isEqualTo(2);
        TripRequestService.Approve();
        TripRequestService.Deny();
        TripRequest t = TripRequestService.Lista().get(0);
        assertThat(t.getApproved()).isEqualTo(1);
        t = TripRequestService.Lista().get(1);
        assertThat(t.getApproved()).isEqualTo(0);
    }
}