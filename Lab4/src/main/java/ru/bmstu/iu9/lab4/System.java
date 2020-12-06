package ru.bmstu.iu9.lab4;

import akka.actor.ActorSystem;

public class System {
    private static final String SYSTEM_ACTOR_NAME ="routes";

    private final ActorSystem system;

    public System(){
        system=ActorSystem.create(SYSTEM_ACTOR_NAME);
    }

    public void run(){
        
    }
}
