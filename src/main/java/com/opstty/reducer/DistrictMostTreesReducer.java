package com.opstty.reducer;

import com.opstty.utils.PositionAgeWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DistrictMostTreesReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

    public void reduce(IntWritable key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        int districtCount = 0;
        for (IntWritable val : values) {
            districtCount += val.get();
        }
        context.write(key, new IntWritable(districtCount));
    }
}