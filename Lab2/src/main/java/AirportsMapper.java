import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class AirportsMapper extends Mapper<LongWritable, Text,Key,Text>{
    private static final String COMMA=",";

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] data= Arrays.stream(value.toString().split(COMMA)).map(s->s.substring(1,s.length()-1)).toArray(String[]::new);
        if (key.get()>0){
            
        }

    }
}
