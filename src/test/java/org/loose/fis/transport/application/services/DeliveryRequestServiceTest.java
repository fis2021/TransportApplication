package org.loose.fis.transport.application.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.transport.application.exceptions.VehicleExists;
import org.loose.fis.transport.application.model.DeliveryRequest;
import org.loose.fis.transport.application.model.Vehicle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class DeliveryRequestServiceTest {

    public static final String ceva = "ceva";

    @AfterEach
    void tearDown() {
        System.out.println("After each");
        DeliveryRequestService.getdatab().close();
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
        DeliveryRequestService.initDatabase();
        VehicleService.initDatabase();
    }

    @Test
    @DisplayName("Database is initialized, and there are no delivery requests")
    void testDatabaseIsInitializedAndNoDeliveryRequestIsPersisted() {
        assertThat(DeliveryRequestService.Lista()).isNotNull();
        assertThat(DeliveryRequestService.Lista()).isEmpty();
    }

    @Test
    @DisplayName("Delivery request is successfully persisted to Database")
    void testDeliveryRequestIsAddedToDatabase() throws Exception{
        DeliveryRequestService.addRequest(ceva, ceva, ceva, ceva, ceva);
        assertThat(DeliveryRequestService.Lista()).isNotEmpty();
        assertThat(DeliveryRequestService.Lista()).size().isEqualTo(1);
        DeliveryRequest deliveryRequest = DeliveryRequestService.Lista().get(0);
        assertThat(deliveryRequest).isNotNull();
        assertThat(deliveryRequest.getPickupAddress()).isEqualTo(ceva);
        assertThat(deliveryRequest.getVehicleType()).isEqualTo(ceva);
        assertThat(deliveryRequest.getDeliveryAddress()).isEqualTo(ceva);
        assertThat(deliveryRequest.getAdditionalInformation()).isEqualTo(ceva);
        assertThat(deliveryRequest.getCustomerName()).isEqualTo(ceva);
    }

    @Test
    void testApprove() throws Exception{
        DeliveryRequestService.addRequest(ceva, ceva, ceva, ceva, ceva);
        DeliveryRequestService.Approve();
        DeliveryRequest deliveryRequest = DeliveryRequestService.Lista().get(0);
        assertThat(deliveryRequest.getStatus()).isEqualTo("Approved");
    }

    @Test
    void testDeny() throws Exception{
        DeliveryRequestService.addRequest(ceva, ceva, ceva, ceva, ceva);
        DeliveryRequestService.Deny();
        DeliveryRequest deliveryRequest = DeliveryRequestService.Lista().get(0);
        assertThat(deliveryRequest.getStatus()).isEqualTo("Denied");
    }


}