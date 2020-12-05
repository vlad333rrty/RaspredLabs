package ru.bmstu.iu9.lab4;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class System {
    private static final String SYSTEM_ACTOR_NAME = "store";

    private ActorRef storeActor;

    public System(ActorSystem system){
        storeActor=system.actorOf(Props.apply())
    }
}
