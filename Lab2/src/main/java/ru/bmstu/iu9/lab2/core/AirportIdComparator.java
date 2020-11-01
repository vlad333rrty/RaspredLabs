package ru.bmstu.iu9.lab2.core;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import ru.bmstu.iu9.lab2.AirportId;

public class AirportIdComparator extends WritableComparator {
    protected AirportIdComparator() {
        super(AirportId.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return ((AirportId)a).getId() - ((AirportId)b).getId();
    }
}