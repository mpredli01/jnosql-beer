# Complete Changelog

## Jakarta NoSQL 1.0.0-b5

### pom.xml file

* Change `<maven.compiler.source>17</maven.compiler.source>` to `<maven.compiler.source>17</maven.compiler.source>`
* Change `<maven.compiler.target>17</maven.compiler.target>` to `<maven.compiler.target>17</maven.compiler.target>`
* Change `<jakarta.nosql.version>1.0.0-b4</jakarta.nosql.version>` to `<jakarta.nosql.version>1.0.0-b5</jakarta.nosql.version>`
* Change `<jakartaee.version>8.0.0</jakartaee.version>` to `<jakartaee.version>10.0.0</jakartaee.version>`
* Bump `<microprofile.config.version>3.0.1</microprofile.config.version>` to `<microprofile.config.version>3.0.2</microprofile.config.version>`                                                             
* Change `<smallrye.config.version>2.8.1</smallrye.config.version>` to `<smallrye.config.version>3.1.1</smallrye.config.version>`
* Change `<yasson.version>2.0.3</yasson.version>` to `<yasson.version>3.0.0</yasson.version>`
* Change `<jboss.weld.se.version>3.1.8.Final</jboss.weld.se.version>` to `<jboss.weld.se.version>5.1.0.Final</jboss.weld.se.version>`
* Change `<artifactId>mongodb-driver</artifactId>` to `<artifactId>jnosql-mongodb-driver</artifactId>`
* Change `<artifactId>mapping-document</artifactId>` to `<artifactId>jnosql-mapping-document</artifactId>`

### ManagerProducer.java

* Change import `javax.enterprise.context.ApplicationScoped;` to `jakarta.enterprise.context.ApplicationScoped;`
* Change import `javax.enterprise.inject.Any;` to `jakarta.enterprise.inject.Any;`
* Change import `javax.enterprise.inject.Disposes;` to `jakarta.enterprise.inject.Disposes;`
* Change import `javax.enterprise.inject.Produces;` to`jakarta.enterprise.inject.Produces;`
* Chnage import `javax.inject.Inject;` to `jakarta.inject.Inject;` 
* Change `DocumentCollectionManager` to `DocumentManager`

### BeerRepository.java

* Change import `jakarta.nosql.mapping.Repository` to `jakarta.data.repository.Repository`

### BrewerRepository.java

* Change import `jakarta.nosql.mapping.Repository` to `jakarta.data.repository.Repository`
* 