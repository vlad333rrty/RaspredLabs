package ru.bmstu.iu9.lab4;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;
import scala.sys.Prop;


public class Router extends AbstractActor {
    private static final String STORE_ACTOR_NAME = "store";
    private static final String EXECUTE_ACTOR_NAME="execute";
    private static final int POOL_NUMBER=10;

    private ActorRef storeActor;
    private ActorRef executeActor;

    public Router(ActorSystem system){
        storeActor=system.actorOf(Props.create(StoreActor.class), STORE_ACTOR_NAME);
        executeActor=system.actorOf(new RoundRobinPool(POOL_NUMBER).props(Props.create(ExecuteActor.class)),EXECUTE_ACTOR_NAME);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Request.class,request -> {
                    
                })
                .build();
    }
}
