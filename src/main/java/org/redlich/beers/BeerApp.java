package org.redlich.beers;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class BeerApp {

    private static final Logger LOGGER = Logger.getLogger(BeerApp.class.getName());

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Service service = container.select(Service.class).get();

            BeerRepository beerRepository = service.getBeerRepository();
            BrewerRepository brewerRepository = service.getBrewerRepository();

            BeerService beerService = container.select(BeerService.class).get();
            BrewerService brewerService = container.select(BrewerService.class).get();

            Stream<Beer> beerList = beerRepository.findAll();
            List<Brewer> brewerList = brewerRepository.findAll();

            long noOfBeers = beerList.count();
            int noOfBrewers = brewerList.size();

            System.out.println("--------------------\n");
            System.out.println("There are " + noOfBeers + " beers in the database");
            System.out.println("There are " + noOfBrewers + " brewers in the database");
            System.out.println("--------------------\n");

            Brewer readington = Brewer.builder()
                    .id(noOfBrewers + 1)
                    .name("Readington Brewery")
                    .city("Readington")
                    .state("New Jersey")
                    .build();
            brewerRepository.save(readington);

            Brewer boulevard = Brewer.builder()
                    .id(noOfBrewers + 2)
                    .name("Boulevard Brewing")
                    .city("Kansas City")
                    .state("Missouri")
                    .build();
            brewerService.insert(boulevard);

            /*/ this code block is under construction and does not work as is
            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            template.insert(brewer);
            System.out.println(template.find(Beer.class, 1));
            /*/

            System.out.println("--------------------\n");
            System.out.println("Finding a brewer by name...");
            List<Brewer> brewers = brewerRepository.findByName("Boulevard Brewing");
            System.out.println(brewers);
            System.out.println("--------------------\n");

            System.out.println("Obtaining the brewerId...");
            int brewer_id = brewers.get(0).getId();
            System.out.println("The brewerId = " + brewer_id);
            System.out.println("--------------------\n");

            System.out.println("Adding two new beers from the brewer using the brewerId (" + brewer_id + ")...");
            Beer heady = Beer.builder()
                    .id((int) noOfBeers + 1)
                    .name("Tank 7")
                    .type(BeerType.ALE)
                    .brewer_id(brewer_id)
                    .abv(8.5)
                    .build();
            beerRepository.save(heady);

            Beer focal = Beer.builder()
                    .id((int) noOfBeers + 2)
                    .name("The Calling IPA")
                    .type(BeerType.IPA)
                    .brewer_id(brewer_id)
                    .abv(8.5)
                    .build();
            beerService.insert(focal);

            System.out.println("--------------------\n");

            System.out.println("Finding a beer by name...");
            Stream<Beer> beerStream1 = beerRepository.findByName("Pumking");
            beerStream1.forEach(beer -> System.out.println("Here is the beer I found: " + beer.getName()));
            System.out.println("--------------------\n");

            System.out.println("Finding varieties of beer by brewerId (" + brewer_id + ") " + brewers.get(0).getName() + "...");
            Stream<Beer> byBrewerId = beerRepository.findByBrewerId(brewer_id);
            byBrewerId.forEach(beerByBrewer -> System.out.println("Here are the beers I found: " + beerByBrewer));
            System.out.println("--------------------\n");

            System.out.println("Finding brewers by city and state...");
            Stream<Brewer> brewerStream = brewerService.findByCityAndState("New Orleans", "Louisiana");
            brewerStream.forEach(brewer -> System.out.println(brewer));
            System.out.println("--------------------\n");

            System.out.println("Finding beer by ABV greater than 8.0...");
            Stream<Beer> beerStream = beerService.findByAbv(8.0);
            beerStream.forEach(beered -> System.out.println(beered));
            System.out.println("--------------------\n");

            /*/ work in progress for updating a beer or a brewer
            System.out.println("The brewerId still remains as: " + brewer_id);
            brewerRepository.update(alchemist);
            /*/

            /*/ uncomment this section to delete a beer from the database
            System.out.println("Deleting beer by beer_id...");
            beerRepository.deleteById(21);
            /*/

            /*/ uncomment this section to delete a brewer from the database
            System.out.println("Deleting brewer by brewer_id");
            brewerRepository.deleteById(28);
            /*/
            }
        catch(IndexOutOfBoundsException exception) {
            System.out.println("EXCEPTION:");
            System.out.println("The cause was: " + exception.getCause());
            System.out.println("The message is: " + exception.getMessage());
            }
        }

    private BeerApp() {
        }
    }
