import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirportPartitioner extends Partitioner {

    @Override
    public int getPartition(Key key, Text text, int numPartitions) {
        return key.getAirportID()%numPartitions;
    }

    @Override
    public int getPartition(Object o, Object o2, int numPartitions) {
        return 0;
    }
}
