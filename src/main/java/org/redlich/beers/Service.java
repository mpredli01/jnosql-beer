package org.redlich.beers;

import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/*/
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
 */

@ApplicationScoped
public class Service {

    @Inject
    @Database(provider = "beerDb", value = DatabaseType.DOCUMENT)
    private BeerRepository beerRepository;

    @Inject
    @Database(provider = "brewerDb", value = DatabaseType.DOCUMENT)
    private BrewerRepository brewerRepository;

    public BeerRepository getBeerRepository() {
        return beerRepository;
        }

    public BrewerRepository getBrewerRepository() {
        return brewerRepository;
        }
    }
