package org.loose.fis.transport.application.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.transport.application.exceptions.AccountExists;
import org.loose.fis.transport.application.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.transport.application.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    public static final String ADMIN = "admin";

    @AfterEach
    void tearDown() {
        System.out.println("After each");
        UserService.getdatab().close();
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
        UserService.initDatabase();
    }




    @Test
    @DisplayName("Database is initialized, and there are no users")
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.Lista()).isNotNull();
        assertThat(UserService.Lista()).isEmpty();
    }

    @Test
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException {
        UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN);
        assertThat(UserService.Lista()).isNotEmpty();
        assertThat(UserService.Lista()).size().isEqualTo(1);
        User user = UserService.Lista().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(ADMIN);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(ADMIN, ADMIN));
        assertThat(user.getRole()).isEqualTo(ADMIN);
        assertThat(user.getName()).isEqualTo(ADMIN);
        assertThat(user.getAddress()).isEqualTo(ADMIN);
        assertThat(user.getEmail()).isEqualTo(ADMIN);
    }

    @Test
    @DisplayName("User can not be added twice")
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN);
            UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN);
        });
    }
    @Test
    void testUserIsAddedAndFound() throws UsernameAlreadyExistsException {
        UserService.addUser(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, ADMIN);
        assertThrows(AccountExists.class, () -> {
            UserService.checkUsernameAndPassword(ADMIN, ADMIN);
                });
    }
    @Test
    void testEncode(){
    assertThat(UserService.encodePassword(ADMIN,ADMIN)).isEqualTo(UserService.encodePassword(ADMIN,ADMIN));
    }

}