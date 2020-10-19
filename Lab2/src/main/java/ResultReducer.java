import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ResultReducer extends Reducer<Data, Text, IntWritable,Text> {
    @Override
    protected void reduce(Data key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        context.write(new IntWritable(key.getAirportID()),new Text("222"));
    }
}
