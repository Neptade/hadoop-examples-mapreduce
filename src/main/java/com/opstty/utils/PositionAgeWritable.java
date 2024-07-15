package com.opstty.utils;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PositionAgeWritable implements Writable {
    private IntWritable yearPlanted;
    private Text district;

    public PositionAgeWritable() {}

    public PositionAgeWritable(IntWritable yearPlanted, Text geoposition) {
        this.yearPlanted = yearPlanted;
        this.district = geoposition;
    }

    public IntWritable getYearPlanted() {
        return yearPlanted;
    }

    public void setYearPlanted(IntWritable yearPlanted) {
        this.yearPlanted = yearPlanted;
    }

    public Text getDistrict() {
        return district;
    }

    public void setDistrict(Text district) {
        this.district = district;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(yearPlanted.get());
        out.writeUTF(district.toString());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        yearPlanted = new IntWritable(in.readInt());
        district = new Text(in.readUTF());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionAgeWritable that = (PositionAgeWritable) o;
        return yearPlanted.equals(that.yearPlanted) && district.equals(that.district);
    }

    @Override
    public String toString() {
        return district.toString() + "\t" + yearPlanted.toString();
    }
}
