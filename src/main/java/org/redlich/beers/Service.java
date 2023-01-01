package org.redlich.beers;

/*/
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
/*/
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Service {

    @Inject
    private BeerRepository beerRepository;

    @Inject
    private BrewerRepository brewerRepository;

    public BeerRepository getBeerRepository() {
        return beerRepository;
    }

    public BrewerRepository getBrewerRepository() {
        return brewerRepository;
    }
}
