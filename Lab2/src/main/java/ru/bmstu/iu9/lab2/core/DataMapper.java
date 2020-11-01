package ru.bmstu.iu9.lab2.core;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import ru.bmstu.iu9.lab2.Key;

import java.io.IOException;

public class DataMapper extends Mapper<LongWritable,Text, Key, Text>{
    private static final int CANCELLED=19;
    private static final int DELAY_TIME=18;
    private static final int AIRPORT_ID=14;

    private static final String CANCEL_INDICATOR ="1.00";
    private static final String NO_DELAY_TIME ="0.00";
    private static final String DELIMITER =",";

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] data=value.toString().split(DELIMITER);
        if (key.get()>0){
            if (!data[CANCELLED].equals(CANCEL_INDICATOR)){
                int id=Integer.parseInt(data[AIRPORT_ID]);
                String delay=data[DELAY_TIME];
                context.write(new Key(id, Key.Type.DATA),new Text(delay.isEmpty() ? NO_DELAY_TIME : delay));
            }
        }
    }
}