/*
 * Craft Beer Database Application
 * This demo application is featured in the `Getting Started with Jakarta NoSQL and MongoDB presentation`
 * @author Michael P. Redlich
 */

package org.redlich.beers;

/*/
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
/*/
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.List;
import java.util.stream.Stream;

import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;

import static jakarta.nosql.document.DocumentQuery.select;

public class BeerApp {

    // private static final Logger LOGGER = Logger.getLogger(BeerApp.class.getName());

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            Service service = container.select(Service.class).get();

            BeerRepository beerRepository = service.getBeerRepository();
            BrewerRepository brewerRepository = service.getBrewerRepository();

            BeerService beerService = container.select(BeerService.class).get();
            BrewerService brewerService = container.select(BrewerService.class).get();

            Stream<Beer> beerList = beerRepository.findAll();
            List<Brewer> brewerList = brewerRepository.findAll();

            System.out.println();
            System.out.println("*------------------------------------------------------------*");
            System.out.println("* Craft Beer Database                                        *");
            System.out.println("* A demonstration on how to use Jakarta NoSQL and MongoDB    *");
            System.out.println("*------------------------------------------------------------*");
            System.out.println();

            long noOfBeers = beerList.count();
            int noOfBrewers = brewerList.size();

            System.out.println("* First, let's get some current statistics on the `Beer` and `Brewer` collections:");
            System.out.println("There are " + noOfBeers + " beers in the `Beer` collection of the database");
            System.out.println("There are " + noOfBrewers + " brewers in the `Brewer` collection of the database" + "\n");

            Brewer brewer01 = Brewer.builder()
                    .id(noOfBrewers + 1)
                    .name("Readington Brewery")
                    .city("Readington")
                    .state("New Jersey")
                    .build();
            // brewerRepository.save(brewer01);

            Brewer brewer02 = Brewer.builder()
                    .id(noOfBrewers + 2)
                    .name("Boulevard Brewing")
                    .city("Kansas City")
                    .state("Missouri")
                    .build();
            // brewerService.insert(brewer02);

            System.out.println("* Let's find a specific brewer by name, say, Boulevard Brewing:");
            List<Brewer> brewers = brewerRepository.findByName("Boulevard Brewing");
            int brewer_id = brewers.get(0).getId();
            String brewerName = brewers.get(0).getName();
            System.out.println(brewers);
            System.out.println();

            System.out.println("* Let's obtain the brewerId of " + brewerName + ":");
            System.out.println("The brewerId of " + brewerName + " is " + brewer_id);
            System.out.println();

            System.out.println("* Let's add two new beers from " + brewerName + " using its brewerId (" + brewer_id + "):\n");
            Beer beer01 = Beer.builder()
                    .id((int) noOfBeers + 1)
                    .name("Bourbon Barrel Quad")
                    .type(BeerType.ALE)
                    .brewer_id(brewer_id)
                    .abv(12.2)
                    .build();
            // beerRepository.save(beer01);

            Beer beer02 = Beer.builder()
                    .id((int) noOfBeers + 2)
                    .name("Whiskey Barrel Stout")
                    .type(BeerType.STOUT)
                    .brewer_id(brewer_id)
                    .abv(11.8)
                    .build();
            // beerService.insert(beer02);

            System.out.println("* Let's find varieties of beer by " + brewerName + " using its brewerId (" + brewer_id + "):");
            Stream<Beer> byBrewerId = beerRepository.findByBrewerId(brewer_id);
            byBrewerId.forEach(beerByBrewer -> System.out.println(beerByBrewer));
            System.out.println();

            System.out.println("* Let's find a specific beer by name, say, Pumking:");
            Stream<Beer> beerStream1 = beerRepository.findByName("Pumking");
            beerStream1.forEach(beer -> System.out.println(beer));
            System.out.println();

            System.out.println("* Let's find brewers by city and state, say, New Orleans, Louisiana:");
            Stream<Brewer> brewerStream = brewerService.findByCityAndState("New Orleans", "Louisiana");
            brewerStream.forEach(brewer -> System.out.println(brewer));
            System.out.println();

            System.out.println("* Let's find beers by ABV that greater than 8.0%:");
            Stream<Beer> beerStream = beerService.findByAbv(8.0);
            beerStream.forEach(beered -> System.out.println(beered));
            System.out.println();

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

            System.out.println("* Let's find brewers from Flemington, New Jersey:");
            DocumentQuery query = select()
                    .from("Brewer")
                    .where("city")
                    .eq("Flemington")
                    .and("state")
                    .eq("New Jersey")
                    .build();
            DocumentTemplate template = container.select(DocumentTemplate.class).get();
            Stream<Brewer> brewerStream2 = template.select(query);
            brewerStream2.forEach(brewered -> System.out.println(brewered));
            System.out.println();

            System.out.println("* Let's find the second beer in the `Beer` collection and the fourth brewer from the `Brewer` collection:");
            System.out.println(template.find(Beer.class, 2));
            System.out.println(template.find(Brewer.class, 4));
            System.out.println();
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
