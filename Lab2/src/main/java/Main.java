import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Main {
    public static void main(String[] args){
        if (args.length!=3){
            System.err.println("Usage: <input path> <input path> <output path>");
            System.exit(-1);
        }
        try{
            Job job=Job.getInstance();
            job.setJarByClass(Main.class);
            job.setJobName("Lab2");

            MultipleInputs.addInputPath(job,new Path(args[0]),TextInputFormat.class,DataMapper.class);
            MultipleInputs.addInputPath(job,new Path(args[1]), TextInputFormat.class,AirportsMapper.class);

            FileOutputFormat.setOutputPath(job, new Path(args[2]));
            job.setMapperClass(AirportsMapper.class);
            job.setPartitionerClass(AirportPartitioner.class);
            job.setGroupingComparatorClass(GroupComparator.class);
            job.setReducerClass(ResultReducer.class);
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
