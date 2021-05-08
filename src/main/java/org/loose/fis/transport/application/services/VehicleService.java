package org.loose.fis.transport.application.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.transport.application.exceptions.AccountExists;
import org.loose.fis.transport.application.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.transport.application.exceptions.VehicleExists;
import org.loose.fis.transport.application.model.Vehicle;

import java.security.AccessControlException;
import java.util.Objects;

import static org.loose.fis.transport.application.services.FileSystemService.getPathToFile;

public class VehicleService {

    private static ObjectRepository<Vehicle> vehicleRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("vehicles.db").toFile())
                .openOrCreate("test", "test");

        vehicleRepository = database.getRepository(Vehicle.class);
    }
    public static void addVehicle(String vehicleType, String transportType, int space, int numberOfVehicles, int availableVehicles) throws VehicleExists {
        try {
            vehicleRepository.insert(new Vehicle(vehicleType, transportType, space, numberOfVehicles, availableVehicles));
        }
        catch(UniqueConstraintException e)
        {
            throw new VehicleExists();
        }
    }
    public static void editVehicle(String vehicleType, String transportType, int space, int numberOfVehicles, int availableVehicles)
    {
        for (Vehicle k:
             vehicleRepository.find()) {
            if(k.getVehicleType().equals(vehicleType)){
            k.setTransportType(transportType);
            k.setAvailableVehicles(availableVehicles);
            k.setSpace(space);
            k.setNumberOfVehicles(numberOfVehicles);
                vehicleRepository.update(k);
        }}
    }
    public static void deleteVehicle(String vehicleType) {
        for (Vehicle k : vehicleRepository.find()) {
            if(k.getVehicleType().equals(vehicleType))
                vehicleRepository.remove(k);
        }
    }
    public static ObservableList<Vehicle> Lista()
    {
        ObservableList<Vehicle>list= FXCollections.observableArrayList();;

        for (Vehicle vehicle : vehicleRepository.find()) {
            list.add(vehicle);
        }
        return list;
    }

}
