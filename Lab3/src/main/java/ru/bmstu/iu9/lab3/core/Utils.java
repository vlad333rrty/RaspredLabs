package ru.bmstu.iu9.lab3.core;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;

import java.util.Arrays;

public class Utils {
    private static final int IS_CANCELLED=19;
    private static final int DELAY_TIME=18;
    private static final int DESTINATION_AIRPORT_ID=14;
    private static final int ORIGIN_AIRPORT_ID =11;

    private static final String DELIMITER=",";
    private static final String ONE="1.00";
    private static final String ZERO="0.00";

    public static JavaRDD<Flight> getFlightsRDD(JavaRDD<String> flights){
        return flights.map(s->s.split(DELIMITER))
                .filter(s-> Arrays.stream(s).anyMatch(str->str.chars().anyMatch(Character::isDigit)))
                .map(s-> {
                    if (s[IS_CANCELLED].equals(ONE)){
                        return new Flight(Integer.parseInt(s[ORIGIN_AIRPORT_ID]),Integer.parseInt(s[DESTINATION_AIRPORT_ID]));
                    }
                    String delay=s[DELAY_TIME].isEmpty() ? ZERO : s[DELAY_TIME];
                    return new Flight(Integer.parseInt(s[ORIGIN_AIRPORT_ID]),Integer.parseInt(s[DESTINATION_AIRPORT_ID]),
                            Double.parseDouble(delay));
                });
    }

    public static JavaRDD<Airport> getAirportsRDD(JavaRDD<String> airports){
        return airports.map(s->s.split(DELIMITER)).filter(s->s.length>2)
                .map(s->new Airport(Integer.parseInt(s[0].substring(1,s[0].length()-1)),s[1].substring(1)));
    }

    public static JavaPairRDD<String,Airport> get
}
