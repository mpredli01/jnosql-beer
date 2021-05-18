package org.redlich.beers;

public class BeerBuilder {
    private int id;
    private String name;
    private BeerType type;
    private int brewer_id;
    private double abv;

    public BeerBuilder id(int id) {
        this.id = id;
        return this;
    }

    public BeerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BeerBuilder type(BeerType type) {
        this.type = type;
        return this;
    }

    public BeerBuilder brewer_id(int brewer_id) {
        this.brewer_id = brewer_id;
        return this;
    }

    public BeerBuilder abv(double abv) {
        this.abv = abv;
        return this;
    }

    public Beer build() {
        return new Beer(id, name, type, brewer_id, abv);
    }
}