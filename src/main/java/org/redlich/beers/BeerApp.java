package org.redlich.beers;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
/*/
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
 */

import jakarta.nosql.mapping.document.DocumentTemplate;

public class BeerApp {

    private static final Logger LOGGER = Logger.getLogger(BeerApp.class.getName());

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Service service = container.select(Service.class).get();

            BeerRepository beerRepository = service.getBeerRepository();
            BrewerRepository brewerRepository = service.getBrewerRepository();

            BeerService beerService = container.select(BeerService.class).get();
            BrewerService brewerService = container.select(BrewerService.class).get();

            List<Beer> beerList = beerRepository.findAll();
            List<Brewer> brewerList = brewerRepository.findAll();

            int noOfBeers = beerList.size();
            int noOfBrewers = brewerList.size();

            System.out.println("--------------------\n");
            System.out.println("There are " + noOfBeers + " beers in the database");
            System.out.println("There are " + noOfBrewers + " brewers in the database");
            System.out.println("--------------------\n");

            Brewer second = Brewer.builder()
                    .id(noOfBrewers + 1)
                    .name("Second Line Brewing")
                    .city("New Orleans")
                    .state("Louisiana")
                    .build();
            brewerRepository.save(second);

            Brewer lakefront = Brewer.builder()
                    .id(noOfBrewers + 2)
                    .name("Lakefront Brewery")
                    .city("Milwaukee")
                    .state("Wisconsin")
                    .build();
            brewerService.insert(lakefront);

            /*/ this code block is under construction and does not work as is
            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            template.insert(brewer);
            System.out.println(template.find(Beer.class, 1));
             */

            System.out.println("--------------------\n");
            System.out.println("Finding a brewer by name...");
            List<Brewer> brewers = brewerRepository.findByName("Lakefront Brewery");
            System.out.println(brewers);
            System.out.println("--------------------\n");

            System.out.println("Obtaining the brewerId...");
            int brewer_id = brewers.get(0).getId();
            System.out.println("The brewerId = " + brewer_id);
            System.out.println("--------------------\n");

            System.out.println("Adding two new beers from the brewer using the brewerId...");
            Beer pumpkin = Beer.builder()
                    .id(noOfBeers + 1)
                    .name("Brandy Barrel-Aged Pumpkin Imperial Ale")
                    .type(BeerType.ALE)
                    .brewer_id(brewer_id)
                    .abv(13.4)
                    .build();
            beerRepository.save(pumpkin);

            Beer newgrist = Beer.builder()
                    .id(noOfBeers + 2)
                    .name("New Grist Gose with Lime")
                    .type(BeerType.GOSE)
                    .brewer_id(brewer_id)
                    .abv(5.1)
                    .build();
            beerService.insert(newgrist);
            System.out.println("--------------------\n");

            System.out.println("Finding a beer by name...");
            List<Beer> beer = beerRepository.findByName("Pumking");
            System.out.println(beer);
            System.out.println("--------------------\n");

            System.out.println("Finding varieties of beer by brewerId...");
            List<Beer> beers = beerRepository.findByBrewerId(brewer_id);
            System.out.println(beers);
            System.out.println("--------------------\n");

            System.out.println("Finding brewers by city and state...");
            Stream<Brewer> brewerStream = brewerService.findByCityAndState("New Orleans", "Louisiana");
            brewerStream.forEach(brewer -> System.out.println(brewer));
            System.out.println("--------------------\n");
            
            System.out.println("Finding beer by ABV greater than 8.0...");
            Stream<Beer> beerStream = beerService.findByAbv(8.0);
            beerStream.forEach(beered -> System.out.println(beered));
            System.out.println("--------------------\n");

            /*/
            System.out.println("Deleting by beer_id...");
            beerRepository.deleteById(9);
            /*/

            }
        }

    private BeerApp() {
        }
    }
