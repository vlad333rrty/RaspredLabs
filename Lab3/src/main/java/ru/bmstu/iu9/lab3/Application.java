package ru.bmstu.iu9.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import ru.bmstu.iu9.lab3.core.Airport;
import ru.bmstu.iu9.lab3.core.Flight;
import ru.bmstu.iu9.lab3.core.Utils;
import scala.Tuple2;

import scala.Serializable;
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
        JavaRDD<String> airports= Utils.getPreparedData(sc.textFile(AIRPORTS_DATA_FILE_NAME));
        JavaRDD<String> flights=Utils.getPreparedData(sc.textFile(FLIGHTS_DATA_FILE_NAME));

        JavaRDD<Airport> airportsRDD= Utils.getAirportsRDD(airports);
        JavaRDD<Flight> flightsRDD=Utils.getFlightsRDD(flights);

        JavaPairRDD<String,Airport> airportsPair=Utils.getPairRDD(airports);
        JavaPairRDD<Tuple2<String,String>,Flight> flightsCorrespondence=Utils.getIdToFlightRDD(flightsRDD);

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
                    Data data=new Data(maxDelay,lateFlightsPercent,cancelledFlightsPercent);
                    return data;
                }).map(data->{
                    Airport origin=broadcast.value().get(data._1._1);
                    Airport destination=broadcast.value().get(data._1._2)
                })
    }

    private class Data implements Serializable{
        private double maxDelay;
        private double lateFlightsPercent;
        private double cancelledFlightsPercent;

        public Data(double maxDelay, double lateFlightsPercent, double cancelledFlightsPercent) {
            this.maxDelay = maxDelay;
            this.lateFlightsPercent = lateFlightsPercent;
            this.cancelledFlightsPercent = cancelledFlightsPercent;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "maxDelay=" + maxDelay +
                    ", lateFlightsPercent=" + lateFlightsPercent +
                    ", cancelledFlightsPercent=" + cancelledFlightsPercent +
                    '}';
        }
    }
}
