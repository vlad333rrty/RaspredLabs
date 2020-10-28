package ru.bmstu.iu9.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import ru.bmstu.iu9.lab3.core.Airport;
import ru.bmstu.iu9.lab3.core.Flight;
import ru.bmstu.iu9.lab3.core.Utils;

import java.util.Collections;
import java.util.stream.Collectors;

public class Main {
    private static final String RESULT_FILE_NAME="result";
    private static final String APP_NAME="Lab3";
    private static final String AIRPORTS_DATA_FILE_NAME="airports.csv";
    private static final String FLIGHTS_DATA_FILE_NAME="flights.csv";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName(APP_NAME);
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> airports=sc.textFile(AIRPORTS_DATA_FILE_NAME);
        JavaRDD<String> flights=sc.textFile(FLIGHTS_DATA_FILE_NAME);

        JavaRDD<Airport> airportsRDD= Utils.getAirportsRDD(airports);
        JavaRDD<Flight> flightsRDD=Utils.getFlightsRDD(flights);

        JavaPairRDD<Integer,Iterable<Flight>> pair=flightsRDD.groupBy(Flight::getOriginId);

        pair.saveAsTextFile(RESULT_FILE_NAME);
    }
}
