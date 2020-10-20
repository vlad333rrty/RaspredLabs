import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

public class AirportsMapper extends Mapper<LongWritable, Text,Key,Text>{
    private static final String COMMA=",";
    private static final int AIRPORT_ID=0;
    private static final int AIRPORT_NAME=1;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] data= Arrays.stream(value.toString().split(COMMA)).map(s->s.substring(1,s.length()-1)).toArray(String[]::new);
        if (key.get()>0){
            int id=Integer.parseInt(data[AIRPORT_ID]);
            context.write(new Key(id,Key.Type.AIRPORT),new Text(data[AIRPORT_NAME]));
        }

    }
}
