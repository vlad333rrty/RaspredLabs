package ru.bmstu.iu9.lab4;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
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
    private static final String HOST="localhost";
    private static final int PORT=8080;

    public void start() throws IOException {
        ActorSystem system=ActorSystem.create(SYSTEM_ACTOR_NAME);
        ActorMaterializer materializer=ActorMaterializer.create(system);
        Router router=new Router(system);
        final Http http=Http.get(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = router.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost(HOST, PORT),materializer);

        System.in.read();

        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
    }
}
