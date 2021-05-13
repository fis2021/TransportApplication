package org.loose.fis.transport.application.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.loose.fis.transport.application.exceptions.AccountExists;
import org.loose.fis.transport.application.exceptions.CouldNotWriteUsersException;
import org.loose.fis.transport.application.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.transport.application.model.Trip;
import org.loose.fis.transport.application.model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.loose.fis.transport.application.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static Nitrite database;

    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static Nitrite getdatab()
    {
        return database;
    }

    public static ObservableList<User> Lista()
    {
        ObservableList<User>list= FXCollections.observableArrayList();;

        for (User k : userRepository.find()) {
            list.add(k);
        }
        return list;
    }

    public static void checkUsernameAndPassword(String username,String password) throws AccountExists {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())&&Objects.equals(encodePassword(username,password), user.getPassword()))
                throw new AccountExists(username);
        }
    }

    public static void addUser(String username, String password, String role, String name, String address, String email) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role, name, address, email));
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}
