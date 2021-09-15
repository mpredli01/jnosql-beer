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

            List<Beer> beerList = beerRepository.findAll();
            List<Brewer> brewerList = brewerRepository.findAll();

            int noOfBeers = beerList.size();
            int noOfBrewers = brewerList.size();

            System.out.println("--------------------\n");
            System.out.println("There are " + noOfBeers + " beers in the database");
            System.out.println("There are " + noOfBrewers + " brewers in the database");
            System.out.println("--------------------\n");

            Brewer storm = Brewer.builder()
                    .id(noOfBrewers + 1)
                    .name("Newport Storm Brewing")
                    .city("Newport")
                    .state("Rhode Island")
                    .build();
            brewerRepository.save(storm);

            Brewer oddbird = Brewer.builder()
                    .id(noOfBrewers + 2)
                    .name("Odd Bird Brewing")
                    .city("Stockton")
                    .state("New Jersey")
                    .build();
            brewerService.insert(oddbird);

            /*/ this code block is under construction and does not work as is
            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            template.insert(brewer);
            System.out.println(template.find(Beer.class, 1));
             */

            System.out.println("--------------------\n");
            System.out.println("Finding a brewer by name...");
            List<Brewer> brewers = brewerRepository.findByName("Odd Bird Brewing");
            System.out.println(brewers);
            System.out.println("--------------------\n");

            System.out.println("Obtaining the brewerId...");
            int brewer_id = brewers.get(0).getId();
            System.out.println("The brewerId = " + brewer_id);
            System.out.println("--------------------\n");

            System.out.println("Adding two new beers from the brewer using the brewerId...");
            Beer esb = Beer.builder()
                    .id(noOfBeers + 1)
                    .name("ESB (Extra Stockton Bitter)")
                    .type(BeerType.ALE)
                    .brewer_id(brewer_id)
                    .abv(4.3)
                    .build();
            beerRepository.save(esb);

            Beer extra = Beer.builder()
                    .id(noOfBeers + 2)
                    .name("Extraordinary Machine")
                    .type(BeerType.IPA)
                    .brewer_id(brewer_id)
                    .abv(6.4)
                    .build();
            beerService.insert(extra);
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
            beerRepository.deleteById(9);
            /*/

            }
        }

    private BeerApp() {
        }
    }
