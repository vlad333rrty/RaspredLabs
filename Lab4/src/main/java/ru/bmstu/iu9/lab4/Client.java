package ru.bmstu.iu9.lab4;


import sun.net.www.http.HttpClient;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class Client {
    private static final String URL ="localhost:8080";
    public static void main(String[] args) throws IOException {
        HttpClient client = HttpClient.New(new URL(URL));

    }

    private static String readFile(String fileName){
        try(BufferedInputStream in=new BufferedInputStream(new FileInputStream(fileName))){
            int c;
            StringBuilder builder=new StringBuilder();
            while ((c=in.read())!=-1){
                builder.append((char)c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
