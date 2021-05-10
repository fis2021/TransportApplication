package org.loose.fis.transport.application.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.transport.application.model.Trip;

import static org.loose.fis.transport.application.services.FileSystemService.getPathToFile;

public class RequestsService {
    private static ObjectRepository<Trip> requestsRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("requests.db").toFile())
                .openOrCreate("test", "test");

        requestsRepository = database.getRepository(Trip.class);
    }

    public static ObservableList<Trip> Lista()
    {
        ObservableList<Trip>list= FXCollections.observableArrayList();;

        for (Trip k : requestsRepository.find()) {
            list.add(k);
        }
        return list;
    }
}
