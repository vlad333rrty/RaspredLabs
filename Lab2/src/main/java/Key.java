import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Key implements WritableComparable {
    private int airportID;
    private Type type;

    public int getAirportID() {
        return airportID;
    }

    public void setAirportID(int airportID) {
        this.airportID = airportID;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Key(int airportID, Type type){
        this.airportID=airportID;
        this.type=type;
    }

    public Key(){}

    @Override
    public void write(DataOutput out) throws IOException {
        out.write(airportID);
        out.write(type.equals(Type.AIRPORT) ? 1 : 0);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        airportID=in.readInt();
        type =(in.readInt()==1 ? Type.AIRPORT : Type.DATA);
    }

    @Override
    public int compareTo(Object o) {
        Key k=(Key)o;
        if (k.airportID==airportID) return type.compareTo(k.type);
        return airportID-k.airportID;
    }

    public enum Type{
        AIRPORT,DATA
    }
}
