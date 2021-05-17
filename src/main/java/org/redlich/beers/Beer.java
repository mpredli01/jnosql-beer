package org.redlich.beers;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class Beer {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private BeerType type;

    @Column
    private String brewer_id;

    @Column
    private double abv;

    public Beer() {
        name = "{ beer name }";
        type = BeerType.ALE;
        brewer_id = "{ brewer_id }";
        abv = 10.0;
        }

    public Beer(String name, BeerType type, String brewer_id, double abv) {
        this .name = name;
        this.type = type;
        this.brewer_id = brewer_id;
        this.abv = abv;
        }

    public String getId() {
        return id;
        }

    public String getName() {
        return name;
        }

    public BeerType getType() {
        return type;
        }

    public String getBrewerId () {
        return brewer_id;
        }

    public double getAbv() {
        return abv;
        }

    public enum BeerType {
        ALE,
        STOUT,
        PORTER,
        IPA,
        APA,
        GOSE,
        SAISON,
        LAMBIC,
        KOLSCH,
        PILSNER,
        MARZEN
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