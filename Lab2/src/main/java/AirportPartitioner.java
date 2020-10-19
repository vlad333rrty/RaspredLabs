import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirportPartitioner extends Partitioner<Data, Text> {

    @Override
    public int getPartition(Data data, Text text, int numPartitions) {
        return data.getAirportID()%numPartitions;
    }
}
