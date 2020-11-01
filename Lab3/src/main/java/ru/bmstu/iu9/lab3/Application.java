package ru.bmstu.iu9.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import ru.bmstu.iu9.lab3.core.Airport;
import ru.bmstu.iu9.lab3.core.Flight;
import ru.bmstu.iu9.lab3.core.CSVParser;
import scala.Tuple2;

import java.util.Iterator;
import java.util.Map;

public class Application {
    private static final String RESULT_FILE_NAME="result";
    private static final String APP_NAME="Lab3";
    private static final String AIRPORTS_DATA_FILE_NAME="airports.csv";
    private static final String FLIGHTS_DATA_FILE_NAME="flights.csv";

    public void run(){
        SparkConf conf = new SparkConf().setAppName(APP_NAME);
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> airports= CSVParser.getPreparedData(sc.textFile(AIRPORTS_DATA_FILE_NAME));
        JavaRDD<String> flights= CSVParser.getPreparedData(sc.textFile(FLIGHTS_DATA_FILE_NAME));

        JavaRDD<Flight> flightsRDD= CSVParser.getFlightsRDD(flights);

        JavaPairRDD<String,Airport> airportsPair= CSVParser.getIdToAirportRDD(airports);
        JavaPairRDD<Tuple2<String,String>,Flight> flightsCorrespondence= CSVParser.getIdToFlightRDD(flightsRDD);

        final Broadcast<Map<String,Airport>> broadcast=sc.broadcast(airportsPair.collectAsMap());

        flightsCorrespondence.groupByKey()
                .mapValues(f->{
                    Iterator<Flight> it=f.iterator();
                    double maxDelay=0;
                    double lateFlights=0;
                    double cancelledFlights=0;
                    int number=0;
                    while (it.hasNext()){
                        number++;
                        Flight flight=it.next();
                        if (flight.isCancelled()){
                            cancelledFlights++;
                        }else if (flight.getDelay()>0){
                            maxDelay=Math.max(maxDelay,flight.getDelay());
                            lateFlights++;
                        }
                    }
                    double lateFlightsPercent=lateFlights/number*100;
                    double cancelledFlightsPercent=cancelledFlights/number*100;
                    return new Tuple2(maxDelay,lateFlightsPercent+cancelledFlightsPercent);
                }).map(data->{
                    Airport origin=broadcast.value().get(data._1._1);
                    Airport destination=broadcast.value().get(data._1._2);
                    return new Tuple2(new Tuple2(origin,destination),data._2);
                }).saveAsTextFile(RESULT_FILE_NAME);
    }
}
