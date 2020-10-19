import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DataMapper extends Mapper<LongWritable, Text,Data,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] data=value.toString().split(",");

        if (key.get()>0){
            if (!data[19].equals("1.00")){
                int id=Integer.parseInt(data[14]);
                context.write(new Data(id,false),new Text("0.00"));
            }
        }
    }
}
