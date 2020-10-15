import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class KeyCompare implements WritableComparable<KeyCompare> {
    private int airportID;
    private boolean withAirport;

    @Override
    public void write(DataOutput out) throws IOException {

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        airportID=in.readInt();
        withAirport=in.readBoolean();
    }

    @Override
    public int compareTo(KeyCompare o) {
        return 0;
    }
}
