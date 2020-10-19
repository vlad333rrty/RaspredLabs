import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportsMapper extends Mapper<LongWritable,Text,Data,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] a=value.toString().split(",");
        if (key.get()>0) {
            int id=Integer.parseInt(a[0]);
            context.write(new Data(id,true),new Text(a[1]));
        }
    }
}
