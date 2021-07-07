package aebd.group.webapp;

import com.sun.jna.platform.win32.Sspi;

import java.sql.Timestamp;

public class CPU {
    private Timestamp timestamp;
    private int usage;


    public CPU(Timestamp time, int u){
        timestamp=time;
        usage=u;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
