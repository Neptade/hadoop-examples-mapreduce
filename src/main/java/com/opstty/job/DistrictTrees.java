package com.opstty.job;

import com.opstty.mapper.DistrictMapper;
import com.opstty.mapper.TokenizerMapper;
import com.opstty.reducer.DistrictReducer;
import com.opstty.reducer.IntSumReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class DistrictTrees {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2 ) {
            System.err.println("Usage : districtTrees <input> <output>");
            System.exit(2);
        }
        Job job = Job.getInstance(conf, "DistrictTrees");
        job.setJarByClass(DistrictTrees.class);
        job.setMapperClass(DistrictMapper.class);
        job.setCombinerClass(DistrictReducer.class);
        job.setReducerClass(DistrictReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.addInputPath(job,
                new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job,
                new Path(otherArgs[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
