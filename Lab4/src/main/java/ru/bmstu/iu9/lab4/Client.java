package ru.bmstu.iu9.lab4;


import akka.http.javadsl.model.HttpRequest;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Client {
    private static final String URI="http://";
    public static void main(String[] args) throws IOException {
        HttpClient client = HttpClient.New(new URL(URI));
        
    }

}
