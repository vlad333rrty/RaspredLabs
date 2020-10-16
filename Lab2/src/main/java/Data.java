import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Data implements WritableComparable<Data> {
    private int airportID;
    private boolean withAirport;

    public int getAirportID() {
        return airportID;
    }

    public void setAirportID(int airportID) {
        this.airportID = airportID;
    }

    public boolean isWithAirport() {
        return withAirport;
    }

    public void setWithAirport(boolean withAirport) {
        this.withAirport = withAirport;
    }

    public Data(int airportID, boolean withAirport){
        this.airportID=airportID;
        this.withAirport=withAirport;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.write(airportID);
        out.write(withAirport ? 1 : 0);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        airportID=in.readInt();
        withAirport=in.readInt()==1;
    }

    @Override
    public int compareTo(Data o) {
        return airportID-o.airportID;
    }
}
