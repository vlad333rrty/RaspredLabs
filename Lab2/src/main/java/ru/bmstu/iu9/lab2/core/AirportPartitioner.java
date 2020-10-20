package ru.bmstu.iu9.lab2.core;

import org.apache.hadoop.mapreduce.Partitioner;
import ru.bmstu.iu9.lab2.Key;

public class AirportPartitioner extends Partitioner{

    @Override
    public int getPartition(Object o, Object o2, int numPartitions) {
        return ((Key)o).getId()%numPartitions;
    }
}