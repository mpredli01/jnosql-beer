package org.redlich.beers;

import java.util.List;

import jakarta.nosql.mapping.Repository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface BrewerRepository extends Repository<Brewer, String> {

    List<Brewer> findAll();

    List<Brewer> findByName(String brewer);
}
