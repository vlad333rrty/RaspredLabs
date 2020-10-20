import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator{
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return ((Key)a).getId() - ((Key)b).getId();
    }
}