package ru.bmstu.iu9.lab4;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class Router {
    private static final String STORE_ACTOR_NAME = "store";

    private ActorRef storeActor;

    public Router(ActorSystem system){
        storeActor=system.actorOf(Props.create(StoreActor.class), STORE_ACTOR_NAME);
    }
}
