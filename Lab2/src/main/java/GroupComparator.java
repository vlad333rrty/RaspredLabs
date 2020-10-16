import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {
    @Override
    public int compare(Object a, Object b) {
        return ((Data)a).compareTo((Data)b);
    }
}
