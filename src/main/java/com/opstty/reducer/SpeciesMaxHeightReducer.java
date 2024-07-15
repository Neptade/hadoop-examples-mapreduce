package com.opstty.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SpeciesMaxHeightReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {

    public void reduce(Text key, Iterable<FloatWritable> values, Context context)
            throws IOException, InterruptedException {
        FloatWritable speciesHeight = new FloatWritable(0);
        for (FloatWritable val : values) {
            if (val.get() > speciesHeight.get()) {
                speciesHeight.set(val.get());
            };
        };

        context.write(key, speciesHeight);
    }
}