package aebd.group.webapp;

import java.io.Serializable;
import java.sql.Timestamp;

public class Memory {
    private Timestamp timestamp;
    private float totalsize;
    private float freesize;
    private float percentagefree;

    public Memory(Timestamp t, float ts, float fs, float pf){
        timestamp=t;
        totalsize=ts;
        freesize=fs;
        percentagefree=pf;
        System.out.println("" + this.timestamp + " " + totalsize + " " + freesize + " " + percentagefree);
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public float getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(float totalsize) {
        this.totalsize = totalsize;
    }

    public float getFreesize() {
        return freesize;
    }

    public void setFreesize(float freesize) {
        this.freesize = freesize;
    }

    public float getPercentagefree() {
        return percentagefree;
    }

    public void setPercentagefree(float percentagefree) {
        this.percentagefree = percentagefree;
    }
}
