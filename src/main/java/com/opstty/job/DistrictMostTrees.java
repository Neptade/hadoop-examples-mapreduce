package com.opstty.job;

import com.opstty.utils.DistrictCountWritable;
import com.opstty.mapper.DistrictMostTreesMapper;
import com.opstty.mapper.DistrictMostTreesMapper2;
import com.opstty.reducer.DistrictMostTreesReducer;
import com.opstty.reducer.DistrictMostTreesReducer2;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class DistrictMostTrees {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2 ) {
            System.err.println("Usage : districtTrees <input> <output>");
            System.exit(2);
        }

        Job job1 = Job.getInstance(conf, "DistrictTrees");
        job1.setJarByClass(DistrictMostTrees.class);
        job1.setMapperClass(DistrictMostTreesMapper.class);
        job1.setReducerClass(DistrictMostTreesReducer.class);
        job1.setMapOutputKeyClass(IntWritable.class);
        job1.setMapOutputValueClass(IntWritable.class);
        job1.setOutputKeyClass(IntWritable.class);
        job1.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job1,
                new Path(otherArgs[0]));

        Path tempfile = new Path("/tmp/most-tree-districts-m1-" + System.currentTimeMillis());
        FileOutputFormat.setOutputPath(job1, tempfile);
        job1.waitForCompletion(true);

        Job job2 = Job.getInstance(conf, "DistrictTrees");
        job2.setJarByClass(DistrictMostTrees.class);
        job2.setMapperClass(DistrictMostTreesMapper2.class);
        job2.setReducerClass(DistrictMostTreesReducer2.class);
        job2.setMapOutputKeyClass(NullWritable.class);
        job2.setMapOutputValueClass(DistrictCountWritable.class);
        job2.setOutputKeyClass(IntWritable.class);
        job2.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job2, tempfile);
        FileOutputFormat.setOutputPath(job2,
                new Path(otherArgs[1]));

        boolean job2success = job2.waitForCompletion(true);
        FileSystem.get(conf).delete(tempfile, true);
        System.exit(job2success ? 0 : 1);
    }
}