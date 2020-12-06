package ru.bmstu.iu9.lab4;

import akka.NotUsed;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public class Server {
    private static final String SYSTEM_ACTOR_NAME ="routes";
    private Router router;

    public Server() throws IOException {
        ActorSystem system=ActorSystem.create(SYSTEM_ACTOR_NAME);
        router=new Router(system);
        final Http http=Http.get(system);

        final CompletionStage<ServerBinding> binding = http.bindAndHandle("localhost", 8080).bind(router.createRoute());
        System.in.read();

        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
    }

    public void run(){

    }
}
