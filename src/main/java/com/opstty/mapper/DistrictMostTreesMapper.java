package com.opstty.mapper;

import com.opstty.utils.PositionAgeWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DistrictMostTreesMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
    private IntWritable district = new IntWritable();
    private IntWritable count = new IntWritable(1);
    private boolean firstLine = true;

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        if (firstLine) {
            firstLine = false;
            return;
        }

        String[] attributes = value.toString().split(";");

        district.set(Integer.parseInt(attributes[1]));
        try {
            context.write(district, count);
        } catch (NumberFormatException e) {}

    }
}
