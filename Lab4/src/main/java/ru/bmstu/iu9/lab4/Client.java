package ru.bmstu.iu9.lab4;


import akka.http.javadsl.model.Uri;

import java.io.*;
import java.net.URI;
import  java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    private static final String ADDRESS ="http://127.0.0.1:8080";
    private static final String JSON_FILE="request.json";
    private static final String HEADER="Content-type";
    private static final String VALUE="application/json";
    private static final String GET_REQUEST=ADDRESS+"/?packageId=11";

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient=HttpClient.newHttpClient();
        String json=readFile(JSON_FILE);
        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(ADDRESS))
                .header(HEADER,VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        Thread.sleep(200);

        HttpResponse<String> response=httpClient.send(request,HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        request=HttpRequest.newBuilder()
                .uri(URI.create(GET_REQUEST))
                .GET()
                .build();
       // response=httpClient.send(request,HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    private static String readFile(String fileName) throws IOException {
        try(BufferedReader reader=new BufferedReader(new FileReader(fileName))){
            StringBuilder builder=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null){
                builder.append(line);
            }
            return builder.toString();
        }
    }
}
