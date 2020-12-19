package ru.bmstu.iu9.lab5;

import akka.http.javadsl.Http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class Client {
    private static final String ADDRESS ="http://127.0.0.1:8080";
    private static final String GET_REQUEST=ADDRESS+"/?packageId=11";
    public static void main(String[] args) {
        HttpClient client= HttpClient.newHttpClient();

        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(GET_REQUEST))
                .build();
    }
}
