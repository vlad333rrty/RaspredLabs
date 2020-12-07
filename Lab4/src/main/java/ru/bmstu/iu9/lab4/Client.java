package ru.bmstu.iu9.lab4;


import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.URL;

public class Client {
    private static final String URL ="localhost:8080";
    private static final String JSON_FILE="request.json";
    public static void main(String[] args) throws IOException {
        HttpClient client = HttpClient.New(new URL(URL));
        String json=readFile(JSON_FILE);

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
