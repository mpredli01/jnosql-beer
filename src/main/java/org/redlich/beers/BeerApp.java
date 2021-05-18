package org.redlich.beers;

import java.util.List;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
/*
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
 */

import java.util.logging.Logger;

public class BeerApp {

    private static final Logger LOGGER = Logger.getLogger(BeerApp.class.getName());

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Service service = container.select(Service.class).get();

            BeerRepository beerRepository = service.getBeerRepository();
            BrewerRepository brewerRepository = service.getBrewerRepository();

            /// Brewer brewer = Brewer.builder().withId(3).withName("{}").withCity("{}").withState("{}").builder();
            Brewer brewer = new BrewerBuilder().id(3).name("River Horse").city("Ewing").state("New Jersey").build();
            brewerRepository.save(brewer);

            System.out.println("----------\n");
            System.out.println("Finding a brewer by name...");
            List<Brewer> brewers = brewerRepository.findByName("River Horse");
            System.out.println(brewers);

            System.out.println("Obtaining the brewer_id...");
            int brewer_id = brewers.get(0).getId();
            System.out.println("The brewer_id = " + brewer_id);
            System.out.println("----------\n");

            System.out.println("Adding a new beer from the brewer using the brewer_id...");
            Beer beer = Beer.builder().id(3)
                    .name("Oatmeal Milk Stout").type(BeerType.STOUT)
                    .brewer_id(brewer_id).abv(6.7)
                    .build();
            beerRepository.save(beer);
            System.out.println("----------\n");

            System.out.println("Finding the newly inserted beer by name...");
            System.out.println(beerRepository.findByName("Oatmeal Milk Stout"));
            System.out.println("----------\n");
        }
    }

    private BeerApp() {
    }
}
