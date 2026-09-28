package fr.fms.trainingsales.model;

import java.util.Date;

public class Order {
    private int id;
    private Date date;

    public Order(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
