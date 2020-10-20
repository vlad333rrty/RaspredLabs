import org.apache.hadoop.mapreduce.Partitioner;

public class AirportPartitioner extends Partitioner{

    @Override
    public int getPartition(Object o, Object o2, int numPartitions) {
        return ((Key)o).getId()%numPartitions;
    }
}