package org.loose.fis.transport.application.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.transport.application.exceptions.VehicleExists;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.model.TripRequest;
import org.loose.fis.transport.application.model.Vehicle;

import static org.loose.fis.transport.application.services.FileSystemService.getPathToFile;

public class TripRequestService {
    private static ObjectRepository<TripRequest> requestsRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("tripRequesturi.db").toFile())
                .openOrCreate("test", "test");

        requestsRepository = database.getRepository(TripRequest.class);
    }

    public static ObservableList<TripRequest> Lista()
    {
        ObservableList<TripRequest>list= FXCollections.observableArrayList();

        for (TripRequest k : requestsRepository.find()) {
            list.add(k);
        }
        return list;
    }
    public static void Approve()
    {
        for (TripRequest k:
                requestsRepository.find()) {
            if(k.getApproved()==2){
                k.setApproved(1);
                requestsRepository.update(k); break;
            }}
    }
    public static void Deny()
    {
        for (TripRequest k:
                requestsRepository.find()) {
            if(k.getApproved()==2){
                k.setApproved(0);
                requestsRepository.update(k); break;
            }}
    }
    public static void addRequest(Trip r, String name) throws UniqueConstraintException {
            requestsRepository.insert(new TripRequest(new Trip(r.getVehicleType(), r.getSpace(), r.getDate(), r.getTime(), r.getPrice(), r.getRoute(), r.getId()), name ,2,TripRequestService.Lista().toArray().length+100000));

    }
}
