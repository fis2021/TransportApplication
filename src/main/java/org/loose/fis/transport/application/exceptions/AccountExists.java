package org.loose.fis.transport.application.exceptions;

public class AccountExists extends Exception {

    private String username;

    public AccountExists(String username) {
        super(String.format("An account with the username %s already exists!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}