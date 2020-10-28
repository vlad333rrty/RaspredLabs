package ru.bmstu.iu9.lab3.core;

import org.apache.spark.api.java.JavaRDD;

public class Utils {
    private static final int IS_CANCELLED=19;
    private static final int DELAY_TIME=18;
    private static final int ORIGIN_AIRPORT_ID =14;

    private static final String DELIMITER=",";

    public static JavaRDD<Flight> getFlightsRDD(JavaRDD<String> flights,JavaRDD<Airport> airportsRDD){
        JavaRDD<Flight> result=flights.map(s->s.split(DELIMITER))
                .map(s->new Flight(getAirportById(airportsRDD,s[ORIGIN_AIRPORT_ID]),))
    }

    public static JavaRDD<Airport> getAirportsRDD(JavaRDD<String> airports){
        return airports.map(s->s.split(DELIMITER)).filter(s->s.length>2)
                .map(s->new Airport(Integer.parseInt(s[0].substring(1,s[0].length()-1)),s[1].substring(1)));
    }

    private static Airport getAirportById(JavaRDD<Airport> airportsRDD, String id){
        return airportsRDD.filter(airport -> airport.getId()==Integer.parseInt(id)).first();
    }
}
