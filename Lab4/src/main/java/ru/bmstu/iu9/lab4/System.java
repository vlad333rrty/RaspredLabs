package ru.bmstu.iu9.lab4;
import akka.actor.ActorSystem;

public class System {
    private static final String ACTOR_NAME="System actor";

    public System(){
        ActorSystem system=ActorSystem.create(ACTOR_NAME);

    }
}
