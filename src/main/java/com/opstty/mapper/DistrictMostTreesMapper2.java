package com.opstty.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import com.opstty.utils.DistrictCountWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DistrictMostTreesMapper2 extends Mapper<Object, Text, NullWritable, DistrictCountWritable> {
    private IntWritable district = new IntWritable();
    private IntWritable count = new IntWritable(1);

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {


        String[] attributes = value.toString().split("\t");

        district.set(Integer.parseInt(attributes[0]));
        count.set(Integer.parseInt(attributes[1]));
        try {
            context.write(NullWritable.get(), new DistrictCountWritable(district, count));
        } catch (NumberFormatException e) {}

    }
}
