package com.falco.workshop.repository.person.api;

import com.falco.workshop.repository.person.domain.FindManagerService;
import com.falco.workshop.repository.person.domain.Manager;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrgUnitManagerEndpoint {
    private FindManagerService findManagerService;

    public ManagerJS findOrgUnitManager(String orgUnitId, Boolean noCache) {
        Manager manager = findManagerService.findByOrgUnitOrDefault(orgUnitId, noCache);
        return new ManagerJS(
                manager.getId(),
                orgUnitId,
                manager.getFirstName(),
                manager.getLastName());
    }

    public static class ManagerJS {
        private final String id;
        private final String orgUnitId;
        private final String firstName;
        private final String lastName;

        ManagerJS(String id, String orgUnitId, String firstName, String lastName) {
            this.id = id;
            this.orgUnitId = orgUnitId;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}