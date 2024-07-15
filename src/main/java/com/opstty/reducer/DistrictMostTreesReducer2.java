package com.opstty.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import com.opstty.utils.DistrictCountWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DistrictMostTreesReducer2 extends Reducer<NullWritable, DistrictCountWritable, IntWritable, IntWritable> {

    public void reduce(NullWritable key, Iterable<DistrictCountWritable> values, Context context)
            throws IOException, InterruptedException {
        IntWritable district = new IntWritable();
        IntWritable districtCount = new IntWritable(0);
        for (DistrictCountWritable val : values) {
            if (val.getDistrictCount().get() > districtCount.get()) {
                districtCount.set(val.getDistrictCount().get());
                district.set(val.getDistrict().get());
            }
        }
        context.write(district, districtCount);
    }
}