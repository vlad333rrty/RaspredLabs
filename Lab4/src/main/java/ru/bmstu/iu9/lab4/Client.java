package ru.bmstu.iu9.lab4;

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
    private static final int TIME_TO_WAIT_MILLIS=250;

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client=HttpClient.newHttpClient();
        String json=readFile(JSON_FILE);
        HttpResponse<String> response;

        response=post(client,json);
        System.out.println(response.body());

        response=get(client);
        System.out.println(response.body());
    }

    private static HttpResponse<String> post(HttpClient client,String data) throws IOException, InterruptedException {
        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(ADDRESS))
                .header(HEADER,VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();
        waitForRequestProcessing();
        return client.send(request,HttpResponse.BodyHandlers.ofString());
    }

    private static HttpResponse<String> get(HttpClient client) throws IOException, InterruptedException {
        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(GET_REQUEST))
                .GET()
                .build();
        waitForRequestProcessing();
        return client.send(request,HttpResponse.BodyHandlers.ofString());
    }

    private static void waitForRequestProcessing() throws InterruptedException {
        Thread.sleep(TIME_TO_WAIT_MILLIS);
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
