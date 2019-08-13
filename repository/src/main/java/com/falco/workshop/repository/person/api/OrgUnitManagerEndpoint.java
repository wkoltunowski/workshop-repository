package com.falco.workshop.repository.person.api;

import com.falco.workshop.repository.person.domain.FindingManagerService;
import com.falco.workshop.repository.person.domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrgUnitManagerEndpoint {
    @Autowired
    private FindingManagerService findManagerService;

    @Autowired
    private FindingManagerService findManagerServiceCache;

    public ManagerJS findOrgUnitManager(String orgUnitId, Boolean noCache) {
        Manager manager = findManagerService(noCache).findByOrgUnitOrDefault(orgUnitId);
        return new ManagerJS(
                manager.getId(),
                orgUnitId,
                manager.getFirstName(),
                manager.getLastName());
    }

    private FindingManagerService findManagerService(Boolean noCache) {
        if (noCache) {
            return findManagerService;
        }
        return findManagerServiceCache;
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
