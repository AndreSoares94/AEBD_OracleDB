package aebd.group.webapp;

import java.sql.Timestamp;

public class User {
    private int id;
    private String nome;
    private String default_tablespace;
    private String temp_tablespace;
    private String account_status;
    private Timestamp timestamp;

    public User(int i,String no,String def, String temp, String acc, Timestamp ti){
        id=i;
        nome=no;
        default_tablespace=def;
        temp_tablespace=temp;
        account_status=acc;
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

    public String getDefault_tablespace() {
        return default_tablespace;
    }

    public void setDefault_tablespace(String default_tablespace) {
        this.default_tablespace = default_tablespace;
    }

    public String getTemp_tablespace() {
        return temp_tablespace;
    }

    public void setTemp_tablespace(String temp_tablespace) {
        this.temp_tablespace = temp_tablespace;
    }

    public String getAccount_status() {
        return account_status;
    }

    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
