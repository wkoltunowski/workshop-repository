package com.falco.workshop.repository.person.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ManagerRepository {
    private final ManagerDocumentRepository managerDocumentRepository;

    public ManagerRepository(ManagerDocumentRepository managerDocumentRepository) {
        this.managerDocumentRepository = managerDocumentRepository;
    }

    Optional<Manager> findByOrgUnitId(String orgUnitId) {
        return managerDocumentRepository.findByOrgUnitId(orgUnitId);
    }

    interface ManagerDocumentRepository extends MongoRepository<Manager, String> {
        Optional<Manager> findByOrgUnitId(String orgUnitId);
    }
}
