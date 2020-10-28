package ru.bmstu.iu9.lab3.core;

import scala.Serializable;

public class Flight implements Serializable {
    private final int originId,destinationId;
    private final boolean isCancelled;
    private final double delay;

    public Flight(int originId,int destinationId,double delay){
        this.originId=originId;
        this.destinationId=destinationId;
        isCancelled=false;
        this.delay=delay;
    }

    public Flight(int originId,int destinationId){
        isCancelled=true;
        this.originId=originId;
        this.destinationId=destinationId;
        delay=0;
    }

    public int getOriginId() {
        return originId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public double getDelay() {
        return delay;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "originId=" + originId +
                ", destinationId=" + destinationId +
                ", isCancelled=" + isCancelled +
                ", delay=" + delay +
                '}';
    }
}
