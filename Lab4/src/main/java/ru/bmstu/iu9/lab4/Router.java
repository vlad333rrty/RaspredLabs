package ru.bmstu.iu9.lab4;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Directives;
import akka.http.javadsl.server.Route;
import akka.japi.pf.ReceiveBuilder;
import akka.pattern.Patterns;
import akka.pattern.PatternsCS;
import akka.routing.RoundRobinPool;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.sys.Prop;

import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class Router{
    private static final String STORE_ACTOR_NAME = "store";
    private static final String EXECUTE_ACTOR_NAME="execute";
    private static final String GET_PARAMETER="packageId";
    private static final int POOL_NUMBER=10;
    private static final int TIMEOUT_MILLIS=1000;

    public static ActorRef storeActor;
    private ActorRef executeActor;

    public Router(ActorSystem system){
        storeActor=system.actorOf(Props.create(StoreActor.class), STORE_ACTOR_NAME);
        executeActor=system.actorOf(new RoundRobinPool(POOL_NUMBER).props(Props.create(ExecuteActor.class)),EXECUTE_ACTOR_NAME);
    }

    public Route createRoute(){
        return post(()-> entity(Jackson.unmarshaller(Request.class), request -> {
            executeActor.tell(request,ActorRef.noSender());
            return complete(request.getCode());
        })).orElse(get(()->parameter(GET_PARAMETER,id->{
            System.out.println("GET");
            Future<Object> future=Patterns.ask(storeActor,Integer.parseInt(id),TIMEOUT_MILLIS);
            return completeOKWithFuture(future,Jackson.marshaller());
        })));
    }
}
