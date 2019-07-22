package com.falco.workshop.repository.person.domain;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class FindManagerService {
    private static final String MAIN_ORGUNIT_ID = "123";
    @Autowired
    private final ManagerRepository managerRepository;

    private final Cache<String, Manager> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .maximumSize(10000)
            .build();

    public FindManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Manager findByOrgUnitOrDefault(String orgUnitId, Boolean noCache) {
        if (noCache)
            return findInCache(orgUnitId);
        else
            return managerRepository.findByOrgUnitId(orgUnitId).orElse(findDefaultManager());
    }

    private Manager findInCache(String orgUnitId) {
        try {
            return cache.get(orgUnitId, () -> managerRepository.findByOrgUnitId(orgUnitId).orElse(findDefaultManager()));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Manager findDefaultManager() {
        return managerRepository.findByOrgUnitId(MAIN_ORGUNIT_ID).orElseThrow(RuntimeException::new);
    }
}
