package ru.bmstu.iu9.lab4;


import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.URL;

public class Client {
    private static final String URL ="localhost:8080";
    public static void main(String[] args) throws IOException {
        HttpClient client = HttpClient.New(new URL(URL));
        
    }

}
