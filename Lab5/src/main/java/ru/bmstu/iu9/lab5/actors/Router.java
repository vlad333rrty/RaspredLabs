package ru.bmstu.iu9.lab5.actors;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Source;
import scala.concurrent.Future;
import scala.sys.Prop;

import java.util.Collections;
import java.util.Map;

import static akka.http.javadsl.server.Directives.*;

public class Router{
    private static final String STORE_ACTOR_NAME = "store";
    private static final int POOL_NUMBER=10;
    private final ActorRef storeActor;

    public Router(ActorSystem system){
       storeActor=system.actorOf(Props.create(StoreActor.class),STORE_ACTOR_NAME);
    }

    public Route createRoute(){
        return null;
    }

    public Flow<HttpRequest, HttpResponse, NotUsed> createFlow(Http http, ActorSystem system, ActorMaterializer materializer){
        return Flow.of(HttpRequest.class)
                .map(request->{
                    Map<String,String> paramToValue=request.getUri().query().toMap();
                    
                })
                .mapAsync(POOL_NUMBER,request->{


                    return Source.from(Collections.singletonList(request))
                            .toMat(testSink, Keep.right()).run(materializer);
                })
    }
}