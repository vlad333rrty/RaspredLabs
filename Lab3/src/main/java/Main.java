import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class Main {
    private static final String RESULT_FILE_NAME="result";
    private static final String APP_NAME="Lab3";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName(APP_NAME);
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> file=sc.textFile("airports.csv");
        JavaPairRDD<String,Integer> pair=file.flatMap((s) -> Arrays.asList(s.split(",")).iterator())
                .mapToPair(s->new Tuple2<>(s,1));
        pair.saveAsTextFile(RESULT_FILE_NAME);
    }
}
