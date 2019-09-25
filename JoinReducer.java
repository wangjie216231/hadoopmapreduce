package com.jt.RJoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class JoinReducer extends Reducer<Text,NewBeanJoin,NewBeanJoin,NullWritable>{
    @Override
    protected void reduce(Text key, Iterable<NewBeanJoin> values, Context context) throws IOException, InterruptedException {
        ArrayList<NewBeanJoin> orderBeansall = new ArrayList<>();
        NewBeanJoin pb = new NewBeanJoin();

        for(NewBeanJoin bean : values){
            NewBeanJoin ob = new NewBeanJoin();
            if(bean.getFlg() == "0"){
                try {
                    BeanUtils.copyProperties(ob,bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                orderBeansall.add(ob);
            }else{
                try {
                    BeanUtils.copyProperties(pb,bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        for(NewBeanJoin newbean : orderBeansall){
            newbean.setPnm(pb.getPnm());
            System.out.println(newbean+"=================newbean");
            context.write(newbean,NullWritable.get());
        }

    }
}
