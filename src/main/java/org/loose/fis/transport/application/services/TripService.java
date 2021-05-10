package org.loose.fis.transport.application.services;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.transport.application.exceptions.VehicleExists;
import org.loose.fis.transport.application.model.Trip;

import java.security.AccessControlException;
import java.util.Objects;

import static org.loose.fis.transport.application.services.FileSystemService.getPathToFile;


public class TripService {

    private static ObjectRepository<Trip> tripRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("trips.db").toFile())
                .openOrCreate("test", "test");

        tripRepository = database.getRepository(Trip.class);
    }
    public static ObservableList<Trip> Lista()
    {
        ObservableList<Trip>list= FXCollections.observableArrayList();;

        for (Trip k : tripRepository.find()) {
            list.add(k);
        }
        return list;
    }
    public static void addTrip(String vehicleType, int space, String date, String time, int price, String route) {
            tripRepository.insert(new Trip(vehicleType, space, date, time, price, route, TripService.Lista().toArray().length + 1));
            VehicleService.decrementAvailableVehicles(vehicleType);
    }
    public static void deleteTrip(String vehicleType) {
        for (Trip k : tripRepository.find()) {
            if(k.getVehicleType().equals(vehicleType))
                tripRepository.remove(k);
        }
    VehicleService.incrementAvailableVehicles(vehicleType);
    }

    public static Trip searchById(int id){
        for (Trip k : tripRepository.find()){
            if(k.getId() == id)
                return k;
        }
        return null;
    }

}
