import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class KeyCompare implements WritableComparable<KeyCompare> {
    private int aeroportID;
    private boolean is

    @Override
    public void write(DataOutput out) throws IOException {

    }

    @Override
    public void readFields(DataInput in) throws IOException {

    }

    @Override
    public int compareTo(KeyCompare o) {
        return 0;
    }
}
