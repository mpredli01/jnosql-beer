package org.redlich.beers;

import jakarta.nosql.document.DocumentManager;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@ApplicationScoped
public class ManagerProducer {

    @Inject
    @ConfigProperty(name = "db1")
    private DocumentManager manager;

    @ApplicationScoped
    @Produces
    public DocumentManager getManager() {
        return manager;
        }

    public void close(@Disposes @Any DocumentManager manager) {
        manager.close();
        }
    }
