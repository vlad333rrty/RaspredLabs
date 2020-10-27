package ru.bmstu.iu9.lab3;

import scala.Serializable;

public class Flight implements Serializable {
    

    private final Airport origin,destination;

    public Flight(Airport origin,Airport destination){
        this.origin=origin;
        this.destination=destination;
    }
}
