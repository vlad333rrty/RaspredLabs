import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AirportReducer extends Reducer<Data, Text, IntWritable,Text> {
    @Override
    protected void reduce(Data key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text t:values){
            context.write();
        }
    }
}
