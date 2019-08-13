package com.falco.workshop.repository.person.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindingManagerService {
    private static final String MAIN_ORGUNIT_ID = "123";
    @Autowired
    private final ManagerRepository managerRepository;


    public FindingManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Manager findByOrgUnitOrDefault(String orgUnitId) {
        return managerRepository.findByOrgUnitId(orgUnitId).orElse(findDefaultManager());
    }


    private Manager findDefaultManager() {
        return managerRepository.findByOrgUnitId(MAIN_ORGUNIT_ID).orElseThrow(RuntimeException::new);
    }
}
