package com.jt.secondFlowbean;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowBeanShuffleReduce extends Reducer<Text,FlowBean,Text,FlowBean> {
    FlowBean flowBean = new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long sumup = 0;
        long sumd = 0;
        for(FlowBean flowBeans : values){//Iterable<FlowBean> flow : values
            sumup += flowBeans.getUpFlow();
            sumd += flowBeans.getDownFlow();
        }
        System.out.println(key+"===============================");
        flowBean.set(sumup,sumd);
        context.write(key,flowBean);
    }
}
