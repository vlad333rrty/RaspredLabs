package ru.bmstu.iu9.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.stream.javadsl.Flow;

public class Server {
    private static final String SYSTEM_ACTOR_NAME ="routes";
    private Router router;

    public Server(){
        ActorSystem system=ActorSystem.create(SYSTEM_ACTOR_NAME);
        router=new Router(system);
        final Http http=Http.get(system);
        final Flow<HttpRequest>
    }

    public void run(){

    }
}
