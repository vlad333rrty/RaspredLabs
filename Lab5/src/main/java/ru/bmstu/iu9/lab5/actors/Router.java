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
import akka.japi.Pair;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import ru.bmstu.iu9.lab5.data.Request;
import ru.bmstu.iu9.lab5.data.RequestType;
import scala.concurrent.Future;
import scala.sys.Prop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class Router{
    private static final String STORE_ACTOR_NAME = "store";
    private static final String TEST_URL="testUrl";
    private static final String REQUEST_NUMBER="count";
    private static final int POOL_NUMBER=10;
    private static final int TIMEOUT_MILLIS=1000;

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
                    String testUrl=paramToValue.get(TEST_URL);
                    String count=paramToValue.get(REQUEST_NUMBER);
                    return new Pair<>(testUrl, Integer.parseInt(count));
                })
                .mapAsync(POOL_NUMBER,request->{
                    Request r=new Request(RequestType.GET_RESULT,request.first()+request.second(),
                            request.second());
                    Future<Object> future=Patterns.ask(storeActor,r,TIMEOUT_MILLIS);

                    System.out.println(future.value().get().get());

                    Sink<Pair<String,Integer>,CompletionStage<Long>> fold=Sink.fold(0L,
                            (agg,next)-> agg+System.currentTimeMillis()-next.second());

                    Sink<Pair<String,String>, CompletionStage<Double>> testSink=Flow.
                            <Pair<String,String>>create()
                            .mapConcat(param -> new ArrayList<>(Collections.nCopies(param.second())))


                })
    }
}