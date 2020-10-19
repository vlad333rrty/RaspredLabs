import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirportPartitioner extends Partitioner<Data, Text> {
    @Override
    public int getPartition(Object o, Object o2, int numPartitions) {
        return ((Data)o).getAirportID()%numPartitions;
    }

    @Override
    public int getPartition(Data data, Text text, int numPartitions) {
        return 0;
    }
}
