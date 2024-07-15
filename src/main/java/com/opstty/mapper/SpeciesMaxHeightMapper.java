package com.opstty.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SpeciesMaxHeightMapper extends Mapper<Object, Text, Text, FloatWritable> {
    private Text species = new Text();
    private FloatWritable height = new FloatWritable();
    private boolean firstLine = true;

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        if (firstLine) {
            firstLine = false;
            return;
        }

        String[] attributes = value.toString().split(";");

        species.set(attributes[3]);
        try {
            height.set(Float.parseFloat(attributes[6]));
            context.write(species, height);
        } catch (NumberFormatException e) {}

    }
}
