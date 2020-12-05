package ru.bmstu.iu9.lab4;
import akka.actor.ActorSystem;


public class System {
    private static final String SYSTEM_ACTOR_NAME = "routes";


    public System(){
        ActorSystem system=ActorSystem.create(SYSTEM_ACTOR_NAME);


    }
}
