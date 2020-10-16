import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Main {
    public static void main(String[] args){
        try{
            Job job=Job.getInstance();
            job.setJarByClass(Main.class);
            job.setJobName("Lab2");

            MultipleInputs.addInputPath(job,new Path(args[1]),AirportsMapper.class);

            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            job.setMapperClass(AirportsMapper.class);
            job.setPartitionerClass(AirportPartitioner.class);
            job.setGroupingComparatorClass(GroupComparator.class);
            job.setMapOutputKeyClass(Data.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);
            job.setNumReduceTasks(2);
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
