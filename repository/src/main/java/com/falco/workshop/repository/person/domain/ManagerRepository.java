package com.falco.workshop.repository.person.domain;

import java.util.Optional;

public interface ManagerRepository {
    Optional<Manager> findByOrgUnitId(String orgUnitId);
}
