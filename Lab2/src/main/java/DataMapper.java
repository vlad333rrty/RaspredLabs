import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DataMapper extends Mapper<LongWritable, Text,Data,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] data=value.toString().split(",");

        context.write(new Data(10,true),new Text(data[0]));
    }
}
