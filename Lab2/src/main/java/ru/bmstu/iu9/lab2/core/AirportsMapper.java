package ru.bmstu.iu9.lab2.core;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import ru.bmstu.iu9.lab2.AirportId;

import java.io.IOException;
import java.util.Arrays;

public class AirportsMapper extends Mapper<LongWritable, Text, AirportId,Text> {
    private static final String DELIMITER = ",";
    private static final int AIRPORT_ID = 0;
    private static final int AIRPORT_NAME = 1;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] data = Arrays.stream(value.toString().split(DELIMITER))
                .map(Utils::removeQuotes).toArray(String[]::new);
        if (key.get() > 0) {
            int id = Integer.parseInt(data[AIRPORT_ID]);
            context.write(new AirportId(id, AirportId.Type.AIRPORT), new Text(data[AIRPORT_NAME]));
        }
    }
}
