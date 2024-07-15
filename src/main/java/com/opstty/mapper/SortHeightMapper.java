package com.opstty.mapper;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SortHeightMapper extends Mapper<Object, Text, FloatWritable, Text> {
    private Text geopoint = new Text();
    private FloatWritable height = new FloatWritable();
    private boolean firstLine = true;

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        if (firstLine) {
            firstLine = false;
            return;
        }

        String[] attributes = value.toString().split(";");

        geopoint.set(attributes[0]);
        try {
            height.set(Float.parseFloat(attributes[6]));
            context.write(height, geopoint);
        } catch (NumberFormatException e) {}

    }
}
