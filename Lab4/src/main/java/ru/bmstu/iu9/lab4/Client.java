package ru.bmstu.iu9.lab4;


import java.io.*;
import java.net.URI;
import  java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class Client {
    private static final String ADDRESS ="localhost:8080";
    private static final String JSON_FILE="request.json";
    private static final String HEADER="Content-type";
    private static final String VALUE="application/json";


    public static void main(String[] args) throws IOException {
        HttpClient httpClient=HttpClient.newHttpClient();
        String json=readFile(JSON_FILE);
        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(ADDRESS))
                .header(HEADER,VALUE)
                .POST()
                .build();



    }

    private static String readFile(String fileName){
        try(BufferedReader reader=new BufferedReader(new FileReader(fileName))){
            StringBuilder builder=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null){
                builder.append(line);
            }
            return builder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
