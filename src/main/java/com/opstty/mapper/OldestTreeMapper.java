package com.opstty.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import com.opstty.utils.PositionAgeWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OldestTreeMapper extends Mapper<Object, Text, NullWritable, PositionAgeWritable> {
    private Text district = new Text();
    private IntWritable yearPlanted = new IntWritable();
    private boolean firstLine = true;

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        if (firstLine) {
            firstLine = false;
            return;
        }

        String[] attributes = value.toString().split(";");

        district.set(attributes[1]);
        try {
            yearPlanted.set(Integer.parseInt(attributes[5]));
            context.write(NullWritable.get(), new PositionAgeWritable(yearPlanted, district));
        } catch (NumberFormatException e) {}

    }
}
