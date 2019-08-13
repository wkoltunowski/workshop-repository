package com.falco.workshop.repository.person.infrastructure;

import com.falco.workshop.repository.person.domain.Manager;
import com.falco.workshop.repository.person.domain.ManagerRepository;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
public class ManagerRepositoryCache implements ManagerRepository {
    private final ManagerRepository managerRepository;
    private final Cache<String, Optional<Manager>> cache;

    @Autowired
    public ManagerRepositoryCache(ManagerRepository managerRepository,
                                  @Value("manager_repository.cache") String cacheSpec) {
        this.managerRepository = managerRepository;
        this.cache = CacheBuilder.from(cacheSpec).build();
    }

    @Override
    public Optional<Manager> findByOrgUnitId(String orgUnitId) {
        try {
            return cache.get(orgUnitId, () -> managerRepository.findByOrgUnitId(orgUnitId));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
