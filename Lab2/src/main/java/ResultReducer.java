import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class ResultReducer extends Reducer<Key, Text, IntWritable,Text>{
    @Override
    protected void reduce(Key key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> it=values.iterator();

        double maxDelay=Double.MIN_VALUE;
        double minDelay=Double.MAX_VALUE;
        double averageDelayTime=0;
        int i=0;

        while (it.hasNext()){
            double t=Double.parseDouble(it.next().toString());
            averageDelayTime+=t;
            maxDelay=Math.max(t,maxDelay);
            minDelay=Math.min(t,minDelay);
            i++;
        }

        averageDelayTime/=i;
        context.write(new IntWritable());
    }
}