import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportsMapper extends Mapper<LongWritable,Text, Key,Text> {
    private static final String COMMA=",";

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] data=value.toString().split(COMMA);
        for (int i=0;i<data.length;i++){
            data[i]=data[i].substring(1,data[i].length()-1);
        }
        if (key.get()>0) {
            int id=Integer.parseInt(data[0]);
            context.write(new Key(id,Key.Type.AIRPORT),new Text(data[1]));
        }
    }
}
