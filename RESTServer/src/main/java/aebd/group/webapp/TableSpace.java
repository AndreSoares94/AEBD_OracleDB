package aebd.group.webapp;

import java.sql.Timestamp;

public class TableSpace {
    private String nome;
    private int datafileid;
    private int usedbytes;
    private int totalbytes;
    private int freebytes;
    private int percentagefreebytes;
    private String status;
    private String contents;
    private Timestamp timestamp;

    public TableSpace(String no,int ty, int ub, int tb,int fb, int pb,String st,String ae, Timestamp ti){
        nome=no;
        datafileid=ty;
        usedbytes=ub;
        totalbytes=tb;
        freebytes=fb;
        percentagefreebytes=pb;
        status=st;
        contents=ae;
        timestamp=ti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDatafileid() {
        return datafileid;
    }

    public void setDatafileid(int datafileid) {
        this.datafileid = datafileid;
    }

    public int getUsedbytes() {
        return usedbytes;
    }

    public void setUsedbytes(int usedbytes) {
        this.usedbytes = usedbytes;
    }

    public int getTotalbytes() {
        return totalbytes;
    }

    public void setTotalbytes(int totalbytes) {
        this.totalbytes = totalbytes;
    }

    public int getFreebytes() {
        return freebytes;
    }

    public void setFreebytes(int freebytes) {
        this.freebytes = freebytes;
    }

    public int getPercentagefreebytes() {
        return percentagefreebytes;
    }

    public void setPercentagefreebytes(int percentagefreebytes) {
        this.percentagefreebytes = percentagefreebytes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
