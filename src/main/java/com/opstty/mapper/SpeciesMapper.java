package com.opstty.mapper;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SpeciesMapper extends Mapper<Object, Text, Text, NullWritable> {
    private Text species = new Text();
    private boolean firstLine = true;

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        if (firstLine) {
            firstLine = false;
            return;
        }

        String[] attributes = value.toString().split(";");

        species.set(attributes[3]);
        context.write(species, NullWritable.get());
    }
}
