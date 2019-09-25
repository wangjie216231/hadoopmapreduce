package com.jt.RJoin;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NewBeanJoin implements Writable{
    private String Orderid;
    private String Pid;
    private int amount;
    private String pnm;
    private String flg;

    public NewBeanJoin() {
        super();
    }


    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(Orderid);
        out.writeUTF(Pid);
        out.writeInt(amount);
        out.writeUTF(pnm);
        out.writeUTF(flg);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.Orderid = in.readUTF();
        this.Pid = in.readUTF();
        this.amount = in.readInt();
        this.pnm = in.readUTF();
        this.flg = in.readUTF();
    }


    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPnm() {
        return pnm;
    }

    public void setPnm(String pnm) {
        this.pnm = pnm;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    @Override
    public String toString() {
        return "NewBeanJoin{" +
                "Orderid='" + Orderid + '\'' +
                ", Pid='" + Pid + '\'' +
                ", amount=" + amount +
                ", pnm='" + pnm + '\'' +
                ", flg='" + flg + '\'' +
                '}';
    }
}
