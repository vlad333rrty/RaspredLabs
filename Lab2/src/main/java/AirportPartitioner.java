import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirportPartitioner extends Partitioner<Key, Text> {

    @Override
    public int getPartition(Key key, Text text, int numPartitions) {
        return key.getAirportID()%numPartitions;
    }
}
