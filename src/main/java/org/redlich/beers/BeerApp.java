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

            // Brewer brewer = new Brewer("{ name }", "{ city }", "{ state }");
            // brewerRepository.save(brewer);

            System.out.println("----------\n");
            System.out.println("Finding a brewer by name...");
            List<Brewer> brewers = brewerRepository.findByName("Weyerbacher");
            System.out.println(brewers);

            System.out.println("Obtaining the brewer_id...");
            String brewer_id = brewers.get(0).getId();
            brewer_id = "ObjectId(" + brewer_id + ")";
            System.out.println("The brewer_id = " + brewer_id);
            System.out.println("----------\n");

            System.out.println("Adding a new beer from the brewer using the brewer_id...");
            Beer beer = new Beer("Blithering Idiot", Beer.BeerType.ALE, brewer_id,11.1);
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
