package com.lab;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        String s=value.toString().replaceAll("[^\\p{L}|\\s+]","");
        Pattern p=Pattern.compile("([^\\s]+)");
        Matcher m=p.matcher(s);
        while (m.find()) {
            context.write(new Text(m.group().toLowerCase()),new IntWritable(1));
        }
    }
}