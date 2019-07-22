package com.falco.workshop.repository.person.domain;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class FindManagerService {
    private static final String MAIN_ORGUNIT_ID = "123";
    private final ManagerRepository personRepository;
    private final Cache<String, Manager> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .maximumSize(10000)
            .build();

    public FindManagerService(ManagerRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Manager findByOrgUnitOrDefault(String orgUnitId) {
        try {
            return cache.get(orgUnitId, () -> personRepository.tryFindByOrgUnitId(orgUnitId).orElse(findDefaultManager()));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Manager findDefaultManager() {
        return personRepository.tryFindByOrgUnitId(MAIN_ORGUNIT_ID).orElseThrow(RuntimeException::new);
    }
}
