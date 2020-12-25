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
import scala.concurrent.Future$;
import scala.sys.Prop;
import scala.util.Try;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
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
                    CompletionStage<Object> future=
                            Patterns.ask(storeActor,new Request(RequestType.GET_RESULT,request.first()), Duration.ofMillis(TIMEOUT_MILLIS));
                    future.thenCompose((res)->{
                       if (res!=null) {
                           return HttpResponse.create().withEntity(res.toString());
                       }
                        Flow<Pair<HttpRequest,Long>,Pair<Try<HttpResponse>,Long>,NotUsed> client=http.superPool(materializer);

                        Sink<Pair<Try<HttpResponse>,Long>,CompletionStage<Long>> fold=
                                Sink.fold(0L, (agg,next)->agg-next.second()+System.currentTimeMillis());

                        Sink<Pair<HttpRequest,Integer>,CompletionStage<Long>> testSink= Flow
                                .<Pair<HttpRequest, Integer>>create()
                                .mapConcat(pair -> new ArrayList<>(Collections.nCopies(pair.second(),HttpRequest.create(request.first()))))
                                .mapAsync(request.second(),(url)->{
                                    
                                })
                    })
                })
                .map(result-> {
                    storeActor.tell(new Request(RequestType.ADD_RESULT, result.first().toString(),(long)result.second()),ActorRef.noSender());
                    return HttpResponse.create().withEntity(result.toString());
                });
    }
}