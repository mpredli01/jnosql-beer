package org.redlich.beers;

import java.util.stream.Stream;

/*/
import jakarta.nosql.mapping.Repository;
import jakarta.nosql.mapping.Page;
import jakarta.nosql.mapping.Pagination;
/*/
import jakarta.data.repository.Repository;
import jakarta.data.repository.Page;
import jakarta.data.repository.Pageable;

// import javax.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface BeerRepository extends Repository { // <Beer, String> {

    Stream<Beer> findAll();

    Page<Beer> findAll(Pageable pagination);

    Stream<Beer> findByName(String beer);

    Stream<Beer> findByBrewerId(int brewer_id);

    void deleteById(int id);
}
