package org.redlich.beers;

import jakarta.nosql.mapping.Repository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public interface BrewerRepository extends Repository<Brewer, String> {

    List<Brewer> findAll();

    List<Brewer> findByName(String brewer);

    void deleteById(int id);

    void update(Brewer brewer);
    }
