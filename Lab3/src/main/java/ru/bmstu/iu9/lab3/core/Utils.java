package ru.bmstu.iu9.lab3.core;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Tuple2;

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
        return airports.map(s->s.split(DELIMITER))
                .map(s-> {
                    String id=s[0].substring(1,s[0].length()-1);
                    String name=s[1].substring(1);
                    return new Airport(Integer.parseInt(id), name);
                });
    }

    public static JavaPairRDD<String,Airport> getPairRDD(JavaRDD<String> airports){
        return airports.map(s->s.split(DELIMITER))
                .mapToPair(s->{
                   String id=s[0].substring(1,s[0].length()-1);
                   String name=s[1].substring(1);
                   return new Tuple2<>(id,new Airport(Integer.parseInt(id),name));
                });
    }

    public static JavaRDD<String> getPreparedData(JavaRDD<String> data){
        return data.filter(s-> Arrays.stream(s.split(DELIMITER)).anyMatch(str->str.chars().anyMatch(Character::isDigit)));
    }
}
