import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {
    public GroupComparator() {
        super(Key.class,true);
    }

    @Override
    public int compare(Object a, Object b) {
        return ((Key)a).compareTo((Key)b);
    }
}
