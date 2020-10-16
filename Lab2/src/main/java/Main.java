import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class Main {
    public static void main(String[] args){
        try{
            Job job=Job.getInstance();
            job.setJarByClass(Main.class);
            job.setJobName("Lab2");
            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileInputFormat.addInputPath(job, new Path(args[1]));
            job.setMapperClass(AirportsMapper.class);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
