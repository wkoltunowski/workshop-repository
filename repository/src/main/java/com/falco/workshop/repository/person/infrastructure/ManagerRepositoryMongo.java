package com.falco.workshop.repository.person.infrastructure;

import com.falco.workshop.repository.person.domain.Manager;
import com.falco.workshop.repository.person.domain.ManagerRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ManagerRepositoryMongo implements ManagerRepository {
    private final ManagerDocumentRepository managerDocumentRepository;

    public ManagerRepositoryMongo(ManagerDocumentRepository managerDocumentRepository) {
        this.managerDocumentRepository = managerDocumentRepository;
    }

    public Optional<Manager> findByOrgUnitId(String orgUnitId) {
        return managerDocumentRepository.findByOrgUnitId(orgUnitId);
    }

    interface ManagerDocumentRepository extends MongoRepository<Manager, String> {
        Optional<Manager> findByOrgUnitId(String orgUnitId);
    }
}
