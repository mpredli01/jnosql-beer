# jnosql-beers

A Jakarta NoSQL and MongoDB application with Java SE using Document API with MongoDB as driver implementation.

![MongoDB Project](http://www.jnosql.org/img/logos/mongodb.png)


**Mongodb**: MongoDB is a free and open-source cross-platform document-oriented database program. Classified as a NoSQL database program, MongoDB uses JSON-like documents with schemas.

To run this project a MongoDB instance is required, so you can use either a local installation or using Docker.

## Manual installation

Follow the instructions in: https://docs.mongodb.com/manual/installation/

## Execute the application

`mvn clean compile`
`mvn exec:java -Dexec.mainClass=org.redlich.beers.BeerApp`

