package ru.bmstu.iu9.lab4;
import akka.actor.*;
import akka.dispatch.Dispatchers;
import akka.dispatch.Mailboxes;
import akka.event.EventStream;
import akka.event.LoggingAdapter;
import scala.Function0;
import scala.collection.Iterable;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.Future;

import java.util.concurrent.CompletionStage;

public class System {
    public System(){
        ActorSystem system=new ActorSystem() {
            @Override
            public String name() {
                return null;
            }

            @Override
            public Settings settings() {
                return null;
            }

            @Override
            public void logConfiguration() {

            }

            @Override
            public ActorPath $div(String name) {
                return null;
            }

            @Override
            public ActorPath $div(Iterable<String> name) {
                return null;
            }

            @Override
            public EventStream eventStream() {
                return null;
            }

            @Override
            public LoggingAdapter log() {
                return null;
            }

            @Override
            public ActorRef deadLetters() {
                return null;
            }

            @Override
            public Scheduler scheduler() {
                return null;
            }

            @Override
            public Dispatchers dispatchers() {
                return null;
            }

            @Override
            public ExecutionContextExecutor dispatcher() {
                return null;
            }

            @Override
            public Mailboxes mailboxes() {
                return null;
            }

            @Override
            public <T> void registerOnTermination(Function0<T> code) {

            }

            @Override
            public void registerOnTermination(Runnable code) {

            }

            @Override
            public Future<Terminated> terminate() {
                return null;
            }

            @Override
            public Future<Terminated> whenTerminated() {
                return null;
            }

            @Override
            public CompletionStage<Terminated> getWhenTerminated() {
                return null;
            }

            @Override
            public <T extends Extension> T registerExtension(ExtensionId<T> ext) {
                return null;
            }

            @Override
            public <T extends Extension> T extension(ExtensionId<T> ext) {
                return null;
            }

            @Override
            public boolean hasExtension(ExtensionId<? extends Extension> ext) {
                return false;
            }

            @Override
            public ActorSystemImpl systemImpl() {
                return null;
            }

            @Override
            public ActorRefProvider provider() {
                return null;
            }

            @Override
            public InternalActorRef guardian() {
                return null;
            }

            @Override
            public InternalActorRef lookupRoot() {
                return null;
            }

            @Override
            public ActorRef actorOf(Props props) {
                return null;
            }

            @Override
            public ActorRef actorOf(Props props, String name) {
                return null;
            }

            @Override
            public void stop(ActorRef actor) {

            }
        }
    }
}
