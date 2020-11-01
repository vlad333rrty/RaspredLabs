package ru.bmstu.iu9.lab2.core;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import ru.bmstu.iu9.lab2.Key;

public class AirportPartitioner extends Partitioner<Key, Text> {

    @Override
    public int getPartition(Key key, Text text, int numPartitions) {
        return key.getId()%numPartitions;
    }
}