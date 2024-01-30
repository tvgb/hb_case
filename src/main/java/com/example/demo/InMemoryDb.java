package com.example.demo;

import java.util.HashMap;

public class InMemoryDb {
    private HashMap<String, Lanesoknad> db;

    public InMemoryDb() {
        System.out.println("\n ----- IN MEMORY DB CREATED -----\n");
        db = new HashMap<String, Lanesoknad>();
    }

    public String add(Lanesoknad lanesoknad) {
        String guid = java.util.UUID.randomUUID().toString();
        db.put(guid, lanesoknad);

        return guid;
    }

    public Lanesoknad get(String guid) {
        return db.get(guid);
    }

    public boolean delete(String guid) {
        return db.remove(guid) != null;
    }

    public String[] getLaanesoknadIds() {
        return db.keySet().toArray(new String[0]);
    }
}
