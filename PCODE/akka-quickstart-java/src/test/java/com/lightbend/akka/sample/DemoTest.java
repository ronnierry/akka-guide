package com.lightbend.akka.sample;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

import java.util.Arrays;
import java.util.Collection;

public class DemoTest {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actor-demo-java");
        ActorRef hello = system.actorOf(Props.create(Hello.class));
        hello.tell("Bob", ActorRef.noSender());
        hello.tell(Arrays.asList("1", 2), ActorRef.noSender());
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) { /* ignore */ }
    }

    private static class Hello extends AbstractActor {


        @Override
        public Receive createReceive() {
            return ReceiveBuilder.create().match(
                    String.class,x -> System.out.println(x+ " S")
            ).match(Collection.class, x -> {
                x.forEach(
                        System.out::println
                );
            }).build();
        }
    }
}
