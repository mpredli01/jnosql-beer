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

            /*/
            TODO: get number of documents in the collection and increment by one for the id() method
             */
            Brewer busch = Brewer.builder()
                    .id(12)
                    .name("Anheuser-Busch")
                    .city("St. Louis")
                    .state("Missouri")
                    .build();
            brewerRepository.save(busch);

            Brewer conclave = Brewer.builder()
                    .id(13)
                    .name("Conclave Brewing")
                    .city("Flemington")
                    .state("New Jersey")
                    .build();
            brewerService.insert(conclave);

            /*/ this code block is under construction and does not work as is
            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            template.insert(brewer);
            System.out.println(template.find(Beer.class, 1));
             */

            System.out.println("--------------------\n");
            System.out.println("Finding a brewer by name...");
            List<Brewer> brewers = brewerRepository.findByName("Anheuser-Busch");
            System.out.println(brewers);
            System.out.println("--------------------\n");

            System.out.println("Obtaining the brewerId...");
            int brewer_id = brewers.get(0).getId();
            System.out.println("The brewerId = " + brewer_id);
            System.out.println("--------------------\n");

            System.out.println("Adding two new beers from the brewer using the brewerId...");
            Beer budweiser = Beer.builder()
                    .id(6)
                    .name("Budweiser")
                    .type(BeerType.LAGER)
                    .brewer_id(brewer_id)
                    .abv(6.0)
                    .build();
            beerRepository.save(budweiser);

            Beer michelob = Beer.builder()
                    .id(7)
                    .name("Michelob Ultra")
                    .type(BeerType.LAGER)
                    .brewer_id(brewer_id)
                    .abv(4.2)
                    .build();
            beerService.insert(michelob);
            System.out.println("--------------------\n");

            System.out.println("Finding a beer by name...");
            List<Beer> beer = beerRepository.findByName("Pumking");
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
