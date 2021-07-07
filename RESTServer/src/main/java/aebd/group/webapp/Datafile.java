package aebd.group.webapp;

import java.sql.Timestamp;

public class Datafile {
    private int id;
    private String nome;
    private String type;
    private long usedbytes;
    private long totalbytes;
    private long freebytes;
    private float percentagefreebytes;
    private String status;
    private String autoextensible;
    private Timestamp timestamp;

    public Datafile(int i,String no,String ty, long ub, long tb, long fb, float pb,String st,String ae, Timestamp ti){
        id=i;
        nome=no;
        type=ty;
        usedbytes=ub;
        totalbytes=tb;
        freebytes=fb;
        percentagefreebytes=pb;
        status=st;
        autoextensible=ae;
        timestamp=ti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUsedbytes() {
        return usedbytes;
    }

    public void setUsedbytes(long usedbytes) {
        this.usedbytes = usedbytes;
    }

    public long getTotalbytes() {
        return totalbytes;
    }

    public void setTotalbytes(long totalbytes) {
        this.totalbytes = totalbytes;
    }

    public long getFreebytes() {
        return freebytes;
    }

    public void setFreebytes(long freebytes) {
        this.freebytes = freebytes;
    }

    public float getPercentagefreebytes() {
        return percentagefreebytes;
    }

    public void setPercentagefreebytes(float percentagefreebytes) {
        this.percentagefreebytes = percentagefreebytes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAutoextensible() {
        return autoextensible;
    }

    public void setAutoextensible(String autoextensible) {
        this.autoextensible = autoextensible;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
