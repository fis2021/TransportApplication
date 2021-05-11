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

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("deliveryRequests.db").toFile())
                .openOrCreate("test", "test");

        deliveriesRepository = database.getRepository(DeliveryRequest.class);
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
            deliveriesRepository.insert(new DeliveryRequest(pickupAddress, vehicleType, deliveryAddress, additionalInformation, name));
        }catch(UniqueConstraintException e)
        {
            throw new Exception();
        }
    }
}
