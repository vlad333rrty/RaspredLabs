package ru.bmstu.iu9.lab2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Key implements WritableComparable {
    private int id;
    private Type type;

    public Key() {}

    public Key(int key, Type type) {
        this.id = key;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Object o) {
        Key k = (Key)o;
        return k.id==id ? type.compareTo(k.type) : id-k.id;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(id);
        out.writeInt(type.equals(Type.AIRPORT) ? 1 : 0);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        id = in.readInt();
        type = (in.readInt() == 1 ? Type.AIRPORT : Type.DATA);
    }

    public enum Type {
        AIRPORT, DATA
    }
}
