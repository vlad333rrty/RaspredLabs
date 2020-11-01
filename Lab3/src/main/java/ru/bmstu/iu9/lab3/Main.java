package ru.bmstu.iu9.lab3;

import org.apache.spark.SparkConf;

public class Main {
    private static final String APP_NAME="Lab3";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName(APP_NAME);
        new Application(conf).run();
    }
}
