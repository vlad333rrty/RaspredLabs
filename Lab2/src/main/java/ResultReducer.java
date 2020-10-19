import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class ResultReducer extends Reducer<Data, Text, IntWritable,Text> {
    @Override
    protected void reduce(Data key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> it = values.iterator();

        String name = it.next().toString();
        double minDelay = Double.MAX_VALUE;
        double maxDelay = Double.MIN_VALUE;
        double averageDelayTime = 0;
        int i = 0;

        while (it.hasNext()) {
            i++;
            double delay = Double.parseDouble(it.next().toString());
            averageDelayTime += delay;
            maxDelay = Math.max(maxDelay, delay);
            minDelay = Math.min(minDelay, delay);
        }

        averageDelayTime /= i;
        context.write(new IntWritable(key.getAirportID()),
                new Text(String.format("%s %f %f %f",name,minDelay,maxDelay,averageDelayTime)));
    }
}
