package ru.bmstu.iu9.lab4;

public class Application {
    public static void main(String[] args) {
        try{
            new Server().start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
