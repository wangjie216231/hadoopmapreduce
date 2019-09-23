package com.jt.secondFlowbean;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowBeanShufflemap extends Mapper<LongWritable,Text,Text,FlowBean>{
    Text k = new Text();//为什么类属性
    FlowBean flow = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] word = line.split("\t");
        String phone = word[1];
        long upFlow = Long.parseLong(word[word.length -3]);
        long downFlow = Long.parseLong(word[word.length-2]);
        flow.set(upFlow,downFlow);
        k.set(phone);
        context.write(k,flow);
    }
}
