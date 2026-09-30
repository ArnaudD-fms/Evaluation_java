package fr.fms.trainingsales.model;

import java.math.BigDecimal;

/**
 * Représente une formation.
 */
public class Training {
    private int id;
    private String name;
    private String description;
    private int duration;
    private boolean remote;
    private BigDecimal price;

    public Training(String name, String description, int duration, boolean remote, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.remote = remote;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "---------------------------"
                + "\n" + name
                + "\n" + description
                + "\n" + "Durée : " + duration + " jours"
                + "\n" + "Prix : " + price + "€"
                + "\n" + (remote ? "A distance" : "En présentiel")
                + "\n" + "---------------------------";
    }
}
