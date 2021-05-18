package org.redlich.beers;

public class BrewerBuilder {
    private int id;
    private String name;
    private String city;
    private String state;

    public BrewerBuilder id(int id) {
        this.id = id;
        return this;
    }

    public BrewerBuilder name(String name) {
        this.name = name;
        return this;
    }

    public BrewerBuilder city(String city) {
        this.city = city;
        return this;
    }

    public BrewerBuilder state(String state) {
        this.state = state;
        return this;
    }

    public Brewer build() {
        return new Brewer(id, name, city, state);
    }
}