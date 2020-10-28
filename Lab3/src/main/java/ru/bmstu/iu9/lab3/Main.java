package ru.bmstu.iu9.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class Main {
    private static final String RESULT_FILE_NAME="result";
    private static final String APP_NAME="Lab3";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName(APP_NAME);
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> airports=sc.textFile(args[0]);
        JavaRDD<String> data=sc.textFile(args[1]);

        JavaPairRDD<String,String> air=airports.map(s->
                new String[]{s.substring(0,s.indexOf(',')),s.substring(s.indexOf(','))})
                .mapToPair(s->new Tuple2<>(s[0],s[1]));
        air.saveAsTextFile(RESULT_FILE_NAME);
    }
}
