package ru.bmstu.iu9.lab3.core;

import scala.Serializable;

public class Flight implements Serializable {
    private final Airport origin,destination;
    private final boolean isCancelled;
    private final double delay;

    public Flight(Airport origin,Airport destination,double delay){
        this.origin=origin;
        this.destination=destination;
        isCancelled=false;
        this.delay=delay;
    }

    public Flight(){
        isCancelled=true;
        origin=null;
        destination=null;
        delay=0;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public double getDelay() {
        return delay;
    }
}
