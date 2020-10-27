import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Lab3");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> file=sc.textFile("airports.csv");
        JavaPairRDD<String,String> pair=file.flatMap((s)-> Arrays.asList(s.split(",")).iterator())
                .mapToPair()
    }
}
