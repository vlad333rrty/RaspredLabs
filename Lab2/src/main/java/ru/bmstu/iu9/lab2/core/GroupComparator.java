package ru.bmstu.iu9.lab2.core;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import ru.bmstu.iu9.lab2.Key;

public class GroupComparator extends WritableComparator {
    protected GroupComparator() {
        super(Key.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        return a.compareTo(b);
    }
}