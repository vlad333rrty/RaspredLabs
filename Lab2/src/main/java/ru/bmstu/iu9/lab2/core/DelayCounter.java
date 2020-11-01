package ru.bmstu.iu9.lab2.core;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import ru.bmstu.iu9.lab2.Key;

import java.io.IOException;
import java.util.Iterator;

public class ResultReducer extends Reducer<Key, Text, IntWritable,Text> {
    @Override
    protected void reduce(Key key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> it = values.iterator();

        double maxDelay = Double.MIN_VALUE;
        double minDelay = Double.MAX_VALUE;
        double averageDelayTime = 0;
        int i;
        String name = it.next().toString();

        for (i = 0; it.hasNext(); i++) {
            double t = Double.parseDouble(it.next().toString());
            averageDelayTime += t;
            maxDelay = Math.max(t, maxDelay);
            minDelay = Math.min(t, minDelay);
        }

        if (i > 0) {
            averageDelayTime /= i;
            context.write(new IntWritable(key.getId()), new Text(String.format("%s %f %f %f", name, minDelay, maxDelay, averageDelayTime)));
        }
    }
}