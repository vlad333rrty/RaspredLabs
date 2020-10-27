package ru.bmstu.iu9.lab3;

import scala.Serializable;

public class Airport implements Serializable {
    private final String name;
    private final long id;
    public Airport(long id,String name){
        this.id=id;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
