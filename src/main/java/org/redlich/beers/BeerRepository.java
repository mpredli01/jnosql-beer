package org.redlich.beers;

import jakarta.nosql.mapping.Repository;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.Stream;

@ApplicationScoped
public interface BeerRepository extends Repository<Beer, Integer> {

    Stream<Beer> findAll();


    Stream<Beer> findByName(String beer);

    Stream<Beer> findByBrewerId(int brewer_id);

}
