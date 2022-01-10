package org.redlich.beers;

import java.util.stream.Stream;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;
import static jakarta.nosql.document.DocumentQuery.select;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class BrewerService {

    @Inject
    @ConfigProperty(name = "db1")
    private DocumentTemplate template;

    public Brewer insert(Brewer brewer) {
        return template.insert(brewer);
        }

    public Stream<Beer> findByName(String name) {
        DocumentQuery query = select()
                .from("Brewer")
                .where("name")
                .eq(name)
                .build();
        return template.select(query);
        }

    public Stream<Brewer> findByCityAndState(String city, String state) {
        DocumentQuery query = select()
                .from("Brewer")
                .where("city")
                .eq(city)
                .and("state")
                .eq(state)
                .build();
        return template.select(query);
        }
    }
