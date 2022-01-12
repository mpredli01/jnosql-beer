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

@ApplicationScoped
public class ManagerProducer {

    @Inject
    @ConfigProperty(name = "db1")
    private DocumentCollectionManager manager;

    @ApplicationScoped
    @Produces
    @Database(provider = "beerManager", value = DatabaseType.DOCUMENT)
    public DocumentCollectionManager getManager() {
        return manager;
        }


    public void close(@Disposes @Any DocumentCollectionManager manager) {
        manager.close();
        }
    }
