package com.jt.RJoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class JoinMapper extends Mapper<LongWritable,Text,Text,NewBeanJoin>{
    NewBeanJoin bean = new NewBeanJoin();
    Text k = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        String s = fileSplit.getPath().getName();
        String line = value.toString();
        String[] split = line.split("\t");
        if(s.startsWith("order")){
            bean.setOrderid(split[0]);
            bean.setPid(split[1]);
            bean.setAmount(Integer.parseInt(split[2]));
            bean.setPnm("");
            bean.setFlg("0");
            k.set(split[1]);
        }else{
            bean.setOrderid("");
            bean.setPid(split[0]);
            bean.setAmount(0);
            bean.setPnm(split[1]);
            bean.setFlg("1");
            k.set(split[0]);
        }
        System.out.println(bean+"=================newbean");
        context.write(k,bean);
    }
}
