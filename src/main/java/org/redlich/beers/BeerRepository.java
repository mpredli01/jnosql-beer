package org.redlich.beers;

import java.util.List;

import jakarta.nosql.mapping.Repository;

import javax.enterprise.context.ApplicationScoped;
// import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public interface BeerRepository extends Repository<Beer, String> {

    List<Beer> findByName(String beer);

    List<Beer> findByBrewerId(int brewerId);
}