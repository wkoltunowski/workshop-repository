package com.falco.workshop.repository.person;

import com.falco.workshop.repository.person.domain.FindingManagerService;
import com.falco.workshop.repository.person.infrastructure.ManagerRepositoryCache;
import com.falco.workshop.repository.person.infrastructure.ManagerRepositoryMongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public FindingManagerService findingManagerService(ManagerRepositoryMongo managerRepository) {
        return new FindingManagerService(managerRepository);
    }

    @Bean
    public FindingManagerService findingManagerServiceCached(ManagerRepositoryCache cache) {
        return new FindingManagerService(cache);
    }

}
