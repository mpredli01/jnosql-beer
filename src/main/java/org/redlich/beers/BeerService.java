package org.redlich.beers;

import java.util.stream.Stream;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;
import static jakarta.nosql.document.DocumentQuery.select;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class BeerService {

    @Inject
    @ConfigProperty(name = "db1")
    private DocumentTemplate template;

    public Beer insert(Beer beer) {
        return template.insert(beer);
        }

    public Stream<Beer> findByName(String name) {
        DocumentQuery query = select()
                .from("Beer")
                .where("name")
                .eq(name)
                .build();
        return template.select(query);
        }

    public Stream<Beer> findByBrewerId(int brewer_id) {
        DocumentQuery query = select()
                .from("Beer")
                .where("brewer_id")
                .eq(brewer_id)
                .build();
        return template.select(query);
        }

    public Stream<Beer> findByAbv(double abv) {
        DocumentQuery query = select()
                .from("Beer")
                .where("abv")
                .gt(abv)
                .build();
        return template.select(query);
        }
    }
