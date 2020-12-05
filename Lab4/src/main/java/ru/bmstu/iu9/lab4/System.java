package ru.bmstu.iu9.lab4;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;


public class System {
    private static final String ACTOR_NAME="System actor";

    public System(){
        ActorSystem system=ActorSystem.create(ACTOR_NAME);
        final Http http =Http.get(system);
        final ActorMaterializer materializer=ActorMaterializer.create(system);

        final Flow 
    }
}
