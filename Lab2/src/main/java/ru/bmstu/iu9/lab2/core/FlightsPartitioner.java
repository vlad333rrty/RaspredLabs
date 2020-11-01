package ru.bmstu.iu9.lab2.core;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import ru.bmstu.iu9.lab2.AirportId;

public class FlightsPartitioner extends Partitioner<AirportId, Text> {

    @Override
    public int getPartition(AirportId key, Text text, int numPartitions) {
        return key.getId()%numPartitions;
    }
}