package ru.bmstu.iu9.lab3.core;

import scala.Serializable;

public class Airport implements Serializable {
    private final String name;
    private final int id;
    public Airport(int id,String name){
        this.id=id;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "(" +
                "name='" + name + '\'' +
                ", id=" + id +
                ')';
    }
}
