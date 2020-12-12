package ru.bmstu.iu9.lab5.actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import scala.concurrent.Future;

import static akka.http.javadsl.server.Directives.*;

public class Router{
    private static final String STORE_ACTOR_NAME = "store";
    private static final String EXECUTE_ACTOR_NAME="execute";
    private static final String GET_PARAMETER="packageId";
    private static final int POOL_NUMBER=10;
    private static final int TIMEOUT_MILLIS=1000;

    private final ActorRef storeActor;
    private final ActorRef executeActor;

    public Router(ActorSystem system){
       storeActor=null;
       executeActor=null;
    }

    public Route createRoute(){
        return null;
    }
}