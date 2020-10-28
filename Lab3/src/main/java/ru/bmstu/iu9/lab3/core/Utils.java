package ru.bmstu.iu9.lab3.core;

import org.apache.spark.api.java.JavaRDD;

public class Utils {
    private static final int IS_CANCELLED=19;
    private static final int DELAY_TIME=18;
    private static final int DESTINATION_AIRPORT_ID=14;
    private static final int ORIGIN_AIRPORT_ID =11;

    private static final String DELIMITER=",";
    private static final String ONE="1.00";

    public static JavaRDD<Flight> getFlightsRDD(JavaRDD<String> flights,JavaRDD<Airport> airportsRDD){
        JavaRDD<Flight> result=flights.map(s->s.split(DELIMITER))
                .map(s-> {
                    if (s[IS_CANCELLED].equals(ONE)){
                        return new Flight();
                    }
                    return new Flight(getAirportById(airportsRDD, s[ORIGIN_AIRPORT_ID]),
                            getAirportById(airportsRDD, s[DESTINATION_AIRPORT_ID]),
                            Double.parseDouble(s[DELAY_TIME]));
                });
        return result;
    }

    public static JavaRDD<Airport> getAirportsRDD(JavaRDD<String> airports){
        return airports.map(s->s.split(DELIMITER)).filter(s->s.length>2)
                .map(s->new Airport(Integer.parseInt(s[0].substring(1,s[0].length()-1)),s[1].substring(1)));
    }

    private static Airport getAirportById(JavaRDD<Airport> airportsRDD, String id){
        return airportsRDD.filter(airport -> airport.getId()==Integer.parseInt(id)).first();
    }
}
