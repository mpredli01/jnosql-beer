package org.redlich.beers;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class Brewer {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String state;

    public Brewer() {
        name = "{ brewer name }";
        city = "{ brewer city }";
        state = "{ brewer state }";
        }

    public Brewer(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
        }

    public String getId() {
        return id;
        }

    public String getName() {
        return name;
        }

    public String getCity() {
        return city;
        }

    public String getState() {
        return state;
        }

    @Override
    public String toString() {
        return "Brewer { " +
                "id = '" + getId() + '\'' +
                ", name = '" + getName() + '\'' +
                ", city = '" + getCity() + '\'' +
                ", state = '" + getState() + '\'' +
                " }\n";
        }
    }