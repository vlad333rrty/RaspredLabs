import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Progressable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

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

            MultipleInputs.addInputPath(job,new Path(args[0]), TextInputFormat.class,AirportsMapper.class);


            FileSystem fileSystem=new FileSystem() {
                @Override
                public URI getUri() {
                    return null;
                }

                @Override
                public FSDataInputStream open(Path f, int bufferSize) throws IOException {
                    return null;
                }

                @Override
                public FSDataOutputStream create(Path f, FsPermission permission, boolean overwrite, int bufferSize, short replication, long blockSize, Progressable progress) throws IOException {
                    return null;
                }

                @Override
                public FSDataOutputStream append(Path f, int bufferSize, Progressable progress) throws IOException {
                    return null;
                }

                @Override
                public boolean rename(Path src, Path dst) throws IOException {
                    return false;
                }

                @Override
                public boolean delete(Path f, boolean recursive) throws IOException {
                    return false;
                }

                @Override
                public FileStatus[] listStatus(Path f) throws FileNotFoundException, IOException {
                    return new FileStatus[0];
                }

                @Override
                public void setWorkingDirectory(Path new_dir) {

                }

                @Override
                public Path getWorkingDirectory() {
                    return null;
                }

                @Override
                public boolean mkdirs(Path f, FsPermission permission) throws IOException {
                    return false;
                }

                @Override
                public FileStatus getFileStatus(Path f) throws IOException {
                    return null;
                }
            }

            //FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[2]));
            job.setMapperClass(AirportsMapper.class);
            job.setPartitionerClass(AirportPartitioner.class);
            job.setGroupingComparatorClass(GroupComparator.class);
            job.setReducerClass(AirportReducer.class);
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
