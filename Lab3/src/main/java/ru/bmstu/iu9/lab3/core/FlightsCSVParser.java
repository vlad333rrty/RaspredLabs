package ru.bmstu.iu9.lab3.core;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Tuple2;

public final class FlightsCSVParser {
    private static final int IS_CANCELLED=19;
    private static final int DELAY_TIME=18;
    private static final int DESTINATION_AIRPORT_ID=14;
    private static final int ORIGIN_AIRPORT_ID=11;
    private static final int AIRPORT_ID=0;
    private static final int AIRPORT_NAME=1;

    private static final String DELIMITER=",";
    private static final String CANCEL_INDICATOR ="1.00";
    private static final String NO_DELAY_TIME ="0.00";

    public static JavaRDD<Flight> getFlightsRDD(JavaRDD<String> flights){
        return flights.map(s->s.split(DELIMITER))
                .map(s-> {
                    if (s[IS_CANCELLED].equals(CANCEL_INDICATOR)){
                        return new Flight(s[ORIGIN_AIRPORT_ID],s[DESTINATION_AIRPORT_ID]);
                    }
                    String delay=s[DELAY_TIME].isEmpty() ? NO_DELAY_TIME : s[DELAY_TIME];
                    return new Flight(s[ORIGIN_AIRPORT_ID],s[DESTINATION_AIRPORT_ID],
                            Double.parseDouble(delay));
                });
    }

    public static JavaPairRDD<String,Airport> convertRawAirportData(JavaRDD<String> airports){
        return airports.map(s->s.split(DELIMITER))
                .mapToPair(s->{
                   String id=Utils.removeQuotes(s[AIRPORT_ID]);
                   String name=Utils.removeQuotes(s[AIRPORT_NAME]);
                   return new Tuple2<>(id,new Airport(Integer.parseInt(id),name));
                });
    }

    public static JavaRDD<String> removeHeader(JavaRDD<String> data){
        final String firstLine=data.first();
        return data.filter(s->!s.equals(firstLine));
    }

    public static JavaPairRDD<Tuple2<String,String>,Flight> convertFlightData(JavaRDD<Flight> flights){
        return flights.mapToPair(f->new Tuple2<>(new Tuple2<>(f.getOriginId(),f.getDestinationId()),f));
    }
}
