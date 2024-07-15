package com.opstty.job;

import com.opstty.mapper.SpeciesMaxHeightMapper;
import com.opstty.reducer.SpeciesMaxHeightReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class SpeciesMaxHeight {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2 ) {
            System.err.println("Usage : districtTrees <input> <output>");
            System.exit(2);
        }
        Job job = Job.getInstance(conf, "DistrictTrees");
        job.setJarByClass(SpeciesMaxHeight.class);
        job.setMapperClass(SpeciesMaxHeightMapper.class);
        job.setCombinerClass(SpeciesMaxHeightReducer.class);
        job.setReducerClass(SpeciesMaxHeightReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FloatWritable.class);

        FileInputFormat.addInputPath(job,
                new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job,
                new Path(otherArgs[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
