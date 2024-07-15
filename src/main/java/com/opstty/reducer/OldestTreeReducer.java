package com.opstty.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import com.opstty.utils.PositionAgeWritable;

import java.io.IOException;

public class OldestTreeReducer extends Reducer<NullWritable, PositionAgeWritable, Text, IntWritable> {

    public void reduce(NullWritable key, Iterable<PositionAgeWritable> values, Context context)
            throws IOException, InterruptedException {
        IntWritable oldest = new IntWritable(Integer.MAX_VALUE);
        Text district = new Text();
        for (PositionAgeWritable val : values) {
            if (val.getYearPlanted().get() < oldest.get()) {
                oldest.set(val.getYearPlanted().get());
                district.set(val.getDistrict());
            }
        }

        context.write(district, oldest);
    }
}