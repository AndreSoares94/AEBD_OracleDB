package aebd.group.webapp;

import java.sql.Time;
import java.sql.Timestamp;

public class Table {
    private String name;
    private int userid;
    private String tablespacename;
    private int rows;
    private Timestamp timestamp;

    public Table(String nm, int uid, String tn, int ro, Timestamp tim){
        name=nm;
        userid=uid;
        tablespacename=tn;
        rows=ro;
        timestamp=tim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTablespacename() {
        return tablespacename;
    }

    public void setTablespacename(String tablespacename) {
        this.tablespacename = tablespacename;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
