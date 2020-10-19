import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Data implements WritableComparable {
    private int airportID;
    private boolean airport;

    public int getAirportID() {
        return airportID;
    }

    public void setAirportID(int airportID) {
        this.airportID = airportID;
    }

    public boolean isAirport() {
        return airport;
    }

    public void setAirport(boolean airport) {
        this.airport = airport;
    }

    public Data(int airportID, boolean airport){
        this.airportID=airportID;
        this.airport = airport;
    }

    public Data(){}

    @Override
    public void write(DataOutput out) throws IOException {
        out.write(airportID);
        out.write(airport ? 1 : 0);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        airportID=in.readInt();
        airport =in.readInt()==1;
    }

    @Override
    public int compareTo(Object o) {
        return airportID-((Data)o).airportID;
    }
}
