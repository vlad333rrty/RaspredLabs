package ru.bmstu.iu9.lab5.actors;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;
import scala.sys.Prop;

import static akka.http.javadsl.server.Directives.*;

public class Router{
    private static final String STORE_ACTOR_NAME = "store";
    private static final String EXECUTE_ACTOR_NAME="execute";
    private static final String GET_PARAMETER="packageId";
    private static final int POOL_NUMBER=10;
    private static final int TIMEOUT_MILLIS=1000;

    private final ActorRef storeActor;

    public Router(ActorSystem system){
       storeActor=system.actorOf(Props.create(StoreActor.class),STORE_ACTOR_NAME);
    }

    public Route createRoute(){
        return null;
    }

    public Flow<HttpRequest, HttpResponse, NotUsed> createFlow(){
        return Flow.of(HttpRequest.class)
                .map()
    }
}