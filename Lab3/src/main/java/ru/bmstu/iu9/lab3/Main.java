package ru.bmstu.iu9.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import ru.bmstu.iu9.lab3.core.Airport;
import ru.bmstu.iu9.lab3.core.Flight;
import scala.Tuple2;

import java.util.Arrays;

public class Main {
    private static final String RESULT_FILE_NAME="result";
    private static final String APP_NAME="Lab3";
    private static final String AIRPORTS_DATA_FILE_NAME="airports.csv";
    private static final String FLIGHTS_DATA_FILE_NAME="flights.csv";
    private static final String DELIMITER=",";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName(APP_NAME);
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> airports=sc.textFile(AIRPORTS_DATA_FILE_NAME);
        JavaRDD<String> flights=sc.textFile(FLIGHTS_DATA_FILE_NAME);

        JavaRDD<Airport> airportsRdd=airports.map(s->s.split(DELIMITER)).filter(s->s.length>2)
                .map(s->new Airport(Integer.parseInt(s[0].substring(1,s[0].length()-1)),s[1].substring(1)));

        JavaRDD<Flight> flightsRdd=flights.map(s->s.split(DELIMITER))
                .map(s->new Flight())
    }
}
