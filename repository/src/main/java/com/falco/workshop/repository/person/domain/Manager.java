package com.falco.workshop.repository.person.domain;

public class Manager {
    private final String id;
    private final String orgUnitId;
    private final String firstName;
    private final String lastName;

    public Manager(String id, String orgUnitId, String firstName, String lastName) {
        this.id = id;
        this.orgUnitId = orgUnitId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOrgUnitId() {
        return orgUnitId;
    }
}
