package org.redlich.beers;

import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Service {

    @Inject
    @Database(provider = "beerRepository", value = DatabaseType.DOCUMENT)
    private BeerRepository beerRepository;

    @Inject
    @Database(provider = "brewerRepository", value = DatabaseType.DOCUMENT)
    private BrewerRepository brewerRepository;

    public BeerRepository getBeerRepository() {
        return beerRepository;
        }

    public BrewerRepository getBrewerRepository() {
        return brewerRepository;
        }
    }
