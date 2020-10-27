package ru.bmstu.iu9.lab3;

import scala.Serializable;

public class Flight implements Serializable {
    private final Airport origin,destination;
    private final boolean isCancelled;

    public Flight(Airport origin,Airport destination){
        this.origin=origin;
        this.destination=destination;
        isCancelled=false;
    }

    public Flight(){
        isCancelled=true;
        origin=null;
        destination=null;
    }
}
