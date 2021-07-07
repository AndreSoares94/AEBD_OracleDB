package aebd.group.webapp;

import org.spongycastle.util.Times;

import java.sql.Timestamp;
import java.util.Date;

public class Session {
    private String id;
    private int userid;
    private String status;
    private String schemaname;
    private String machine;
    private int port;
    private String type;
    private String event;
    private Date logontime;
    //private int cpuusage;
    private Timestamp timestamp;

    public Session(String i, int ui, String st, String sn, String mc, int p, String ty, String e, Date lt, Timestamp tm){
        id=i;
        userid=ui;
        status=st;
        schemaname=sn;
        machine=mc;
        port=p;
        type=ty;
        event=e;
        logontime=lt;
        //cpuusage=cp;
        timestamp=tm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchemaname() {
        return schemaname;
    }

    public void setSchemaname(String schemaname) {
        this.schemaname = schemaname;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getLogontime() {
        return logontime;
    }

    public void setLogontime(Date logontime) {
        this.logontime = logontime;
    }

    /*
    public int getCpuusage() {
        return cpuusage;
    }

    public void setCpuusage(int cpuusage) {
        this.cpuusage = cpuusage;
    }
    */

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
