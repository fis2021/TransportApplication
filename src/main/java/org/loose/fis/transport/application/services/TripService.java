package org.loose.fis.transport.application.services;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import org.dizitart.no2.objects.ObjectRepository;
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

}
