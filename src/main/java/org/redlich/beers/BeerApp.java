package org.redlich.beers;

import jakarta.nosql.mapping.document.DocumentTemplate;

import java.util.List;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

/*/
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

            BeerService beerService = container.select(BeerService.class).get();
            BrewerService brewerService = container.select(BrewerService.class).get();

            /*/
            Brewer loneeagle = Brewer.builder()
                    .id(5)
                    .name("Lone Eagle Brewing")
                    .city("Flemington")
                    .state("New Jersey")
                    .build();
            brewerRepository.save(loneeagle);

            Brewer duclaw = Brewer.builder()
                    .id(6)
                    .name("DuClaw")
                    .city("Rosedale")
                    .state("Maryland")
                    .build();
            brewerService.insert(duclaw);
            */

            Brewer brewer = Brewer.builder()
                    .id(7)
                    .name("Czig Meister")
                    .city("Hackettstown")
                    .state("New Jersey")
                    .build();

            /// System.out.println("TEMPLATE");
            /// DocumentTemplate template = container.select(DocumentTemplate.class).get();
            /// template.insert(brewer);
            /// System.out.println(template.find(Beer.class, 1));
            
            System.out.println("--------------------\n");
            System.out.println("Finding a brewer by name...");
            List<Brewer> brewers = brewerRepository.findByName("Southern Tier");
            System.out.println(brewers);

            System.out.println("Obtaining the brewerId...");
            int brewer_id = brewers.get(0).getId();
            System.out.println("The brewerId = " + brewer_id);
            System.out.println("--------------------\n");

            /*/
            System.out.println("Adding a new beer from the brewer using the brewerId...");
            Beer beer = Beer.builder()
                    .id(4)
                    .name("Warlock Imperial Stout")
                    .type(BeerType.STOUT)
                    .brewer_id(brewer_id)
                    .abv(8.6)
                    .build();
            beerRepository.save(beer);
            System.out.println("--------------------\n");
             */

            System.out.println("Finding a beer by name...");
            List<Beer> beer = beerRepository.findByName("Warlock Imperial Stout");
            System.out.println(beer);
            System.out.println("--------------------\n");

            System.out.println("Finding varieties of beer by brewerId...");
            List<Beer> beers = beerRepository.findByBrewerId(brewer_id);
            System.out.println(beers);
            System.out.println("--------------------\n");
            }
        }

    private BeerApp() {
        }
    }
