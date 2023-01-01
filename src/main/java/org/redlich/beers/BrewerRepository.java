package org.redlich.beers;

import java.util.List;

// import jakarta.nosql.mapping.Repository;
import jakarta.data.repository.Repository;

// import javax.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface BrewerRepository extends Repository { // <Brewer, String> {

    List<Brewer> findAll();

    List<Brewer> findByName(String brewer);

    void deleteById(int id);

    void update(Brewer brewer);
    }
