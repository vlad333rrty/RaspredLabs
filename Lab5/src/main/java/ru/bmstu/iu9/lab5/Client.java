package ru.bmstu.iu9.lab5;

import akka.http.javadsl.Http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class Client {
    public static void main(String[] args) {
        HttpClient client= HttpClient.newHttpClient();

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create())
                .build();
    }
}
