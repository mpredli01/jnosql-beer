package org.redlich.beers;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class Brewer {
    @Id
    private int id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String state;

    public Brewer() {
        this.id = 0;
        this.name = "{ brewer name }";
        this.city = "{ brewer city }";
        this.state = "{ brewer state }";
        }

    public Brewer(int id, String name, String city, String state) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        }

    public int getId() {
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