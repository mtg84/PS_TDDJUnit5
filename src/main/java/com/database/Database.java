package com.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    List<List<String>> queriedData = new ArrayList<>();
    private Map<String, String> registeredUsers = new HashMap<>();

    public boolean login(Credentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        return registeredUsers.keySet().contains(username) &&
                registeredUsers.get(username).equals(password);
    }

    public List<List<String>> queryFlightsDatabase() {
        return queriedData;
    }

}
