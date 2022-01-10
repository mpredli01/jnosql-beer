package org.redlich.beers;

import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.mapping.Database;
import jakarta.nosql.mapping.DatabaseType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ManagerProducer {

    @Inject
    @ConfigProperty(name = "db1")
    private DocumentCollectionManager manager;

    /// @ApplicationScoped
    @Produces
    @Database(provider = "beerManager", value = DatabaseType.DOCUMENT)
    public DocumentCollectionManager getManager() {
        return manager;
        }

    /*/
    @ApplicationScoped
    @Produces
    @Database(provider = "brewerRepository", value = DatabaseType.DOCUMENT)
    public DocumentCollectionManager getBrewerDoc() {
        return beerDoc;
        }
    /*/

    public void close(@Disposes @Any DocumentCollectionManager manager) {
        manager.close();
        }
    }
