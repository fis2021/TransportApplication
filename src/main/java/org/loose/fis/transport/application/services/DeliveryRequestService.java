package org.loose.fis.transport.application.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.transport.application.exceptions.VehicleExists;
import org.loose.fis.transport.application.model.DeliveryRequest;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.model.TripRequest;

import static org.loose.fis.transport.application.services.FileSystemService.getPathToFile;

public class DeliveryRequestService {
    private static ObjectRepository<DeliveryRequest> deliveriesRepository;
    private static Nitrite database;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("deliveryRequests.db").toFile())
                .openOrCreate("test", "test");

        deliveriesRepository = database.getRepository(DeliveryRequest.class);
    }

    public static Nitrite getdatab()
    {
        return database;
    }

    public static ObservableList<DeliveryRequest> Lista()
    {
        ObservableList<DeliveryRequest>list= FXCollections.observableArrayList();;

        for (DeliveryRequest k : deliveriesRepository.find()) {
            list.add(k);
        }
        return list;
    }

    public static void addRequest(String pickupAddress, String vehicleType, String deliveryAddress, String additionalInformation, String name) throws Exception {
        try {
            deliveriesRepository.insert(new DeliveryRequest(pickupAddress, vehicleType, deliveryAddress, additionalInformation, name,2,DeliveryRequestService.Lista().toArray().length+100));
        }catch(UniqueConstraintException e)
        {
            throw new Exception();
        }
    }
    public static void Approve()
    {
        for (DeliveryRequest k:
                deliveriesRepository.find()) {
            if(k.getApproved()==2){
                k.setApproved(1);
                VehicleService.decrementAvailableVehicles(k.getVehicleType());
                deliveriesRepository.update(k); break;
            }}
    }
    public static void Deny()
    {
        for (DeliveryRequest k:
                deliveriesRepository.find()) {
            if(k.getApproved()==2){
                k.setApproved(0);
                deliveriesRepository.update(k); break;
            }}
    }
}
