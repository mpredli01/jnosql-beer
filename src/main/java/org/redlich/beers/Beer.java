package org.redlich.beers;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class Beer {
    @Id
    private int id;

    @Column
    private String name;

    @Column
    private BeerType type;

    @Column
    private int brewer_id;

    @Column
    private double abv;

    public Beer() {
        id = 0;
        name = "{ beer name }";
        type = BeerType.ALE;
        brewer_id = 0;
        abv = 10.0;
        }

    public Beer(int id, String name, BeerType type, int brewer_id, double abv) {
        this.id = id;
        this .name = name;
        this.type = type;
        this.brewer_id = brewer_id;
        this.abv = abv;
        }

    public int getId() {
        return id;
        }

    public String getName() {
        return name;
        }

    public BeerType getType() {
        return type;
        }

    public int getBrewerId () {
        return brewer_id;
        }

    public double getAbv() {
        return abv;
        }


    @Override
    public String toString() {
        return "Beer { " +
                "id = '" + getId() + '\'' +
                ", name = '" + getName() + '\'' +
                ", type = '" + getType() + '\'' +
                ", brewer_id = '" + getBrewerId() + '\'' +
                ", abv = '" + getAbv() + '\'' +
                " }\n";
        }
    }