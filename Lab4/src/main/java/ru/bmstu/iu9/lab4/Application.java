package ru.bmstu.iu9.lab4;

import akka.actor.ActorSystem;

public class Application {
    private static final String SYSTEM_ACTOR_NAME ="routes";
    public static void main(String[] args){
        ActorSystem system=ActorSystem.create(SYSTEM_ACTOR_NAME);
        
    }
}
