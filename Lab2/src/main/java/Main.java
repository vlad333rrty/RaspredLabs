import org.apache.hadoop.mapreduce.Job;

public class Main {
    public static void main(String[] args){
        try{
            Job job=Job.getInstance();
            job.setJarByClass(Main.class);
            job.setJobName("Lab2");
            
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
