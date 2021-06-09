package org.redlich.beers;

import java.util.List;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
/*/
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
 */

import jakarta.nosql.mapping.document.DocumentTemplate;
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

            Brewer evil = Brewer.builder()
                    .id(11)
                    .name("Evil Genius Beer Company")
                    .city("Philadelphia")
                    .state("Pennsylvania")
                    .build();
            brewerRepository.save(evil);

            /*/
            Brewer strange = Brewer.builder()
                    .id(10)
                    .name("Strange Days Brewing")
                    .city("Kansas City")
                    .state("Missouri")
                    .build();
            brewerService.insert(strange);
             */

            /*/ this code block is under construction and does not work as is
            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            template.insert(brewer);
            System.out.println(template.find(Beer.class, 1));
             */

            System.out.println("--------------------\n");
            System.out.println("Finding a brewer by name...");
            List<Brewer> brewers = brewerRepository.findByName("Evil Genius Beer Company");
            System.out.println(brewers);
            System.out.println("--------------------\n");

            System.out.println("Obtaining the brewerId...");
            int brewer_id = brewers.get(0).getId();
            System.out.println("The brewerId = " + brewer_id);
            System.out.println("--------------------\n");

            System.out.println("Adding a new beer from the brewer using the brewerId...");
            Beer purple = Beer.builder()
                    .id(5)
                    .name("Purple Monkey Dishwasher")
                    .type(BeerType.PORTER)
                    .brewer_id(brewer_id)
                    .abv(6.7)
                    .build();
            beerRepository.save(purple);
            System.out.println("--------------------\n");

            System.out.println("Finding a beer by name...");
            List<Beer> beer = beerRepository.findByName("Full Slab");
            System.out.println(beer);
            System.out.println("--------------------\n");

            System.out.println("Finding varieties of beer by brewerId...");
            List<Beer> beers = beerRepository.findByBrewerId(brewer_id);
            System.out.println(beers);
            System.out.println("--------------------\n");

            /*/
            System.out.println("Deleting by beer_id...");
            beerRepository.deleteById(5);
             */

            }
        }

    private BeerApp() {
        }
    }
