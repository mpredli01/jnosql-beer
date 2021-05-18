package org.redlich.beers;

import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/*
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
 */

@ApplicationScoped
public class ManagerProducer {

    @Inject
    @ConfigProperty(name = "db1")
    private DocumentCollectionManager beerDoc;

    @ApplicationScoped
    @Produces
    @Database(provider = "beerDb", value = DatabaseType.DOCUMENT)
    public DocumentCollectionManager getBeerDoc() {
        return beerDoc;
    }

    @ApplicationScoped
    @Produces
    @Database(provider = "brewerDb", value = DatabaseType.DOCUMENT)
    public DocumentCollectionManager getBrewerDoc() {
        return beerDoc;
    }

    public void close(@Disposes @Any DocumentCollectionManager manager) {
        manager.close();
    }
}
