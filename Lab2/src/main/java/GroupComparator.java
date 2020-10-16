import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {
    public GroupComparator() {
        super(GroupComparator.class,true);
    }

    @Override
    public int compare(Object a, Object b) {
        return ((Data)a).compareTo((Data)b);
    }
}
