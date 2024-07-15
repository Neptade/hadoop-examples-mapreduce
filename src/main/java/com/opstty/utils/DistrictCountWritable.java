package com.opstty.utils;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DistrictCountWritable implements Writable {
    private IntWritable district;
    private IntWritable districtCount;

    public DistrictCountWritable() {}

    public DistrictCountWritable(IntWritable district, IntWritable districtCount) {
        this.district = district;
        this.districtCount = districtCount;
    }

    public IntWritable getDistrict() {
        return district;
    }

    public void setDistrict(IntWritable district) {
        this.district = district;
    }

    public IntWritable getDistrictCount() {
        return districtCount;
    }

    public void setDistrictCount(IntWritable districtCount) {
        this.districtCount = districtCount;
    }



    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(district.get());
        out.writeInt(districtCount.get());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        district = new IntWritable(in.readInt());
        districtCount = new IntWritable(in.readInt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistrictCountWritable that = (DistrictCountWritable) o;
        return district.equals(that.district) && districtCount.equals(that.districtCount);
    }

    @Override
    public String toString() {
        return district.toString() + "\t" + districtCount.toString();
    }
}
