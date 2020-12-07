package ru.bmstu.iu9.lab4;


import akka.http.javadsl.model.HttpRequest;
import sun.net.www.http.HttpClient;

public class Client {
    private static final String URI="http://";
    public static void main(String[] args){
        HttpClient client = HttpClient.New(URI);

}
