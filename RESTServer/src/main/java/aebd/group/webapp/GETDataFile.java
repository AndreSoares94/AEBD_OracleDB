package aebd.group.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GETDataFile {
    public List<Datafile> datafile() {
        try {
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1.localdomain", "monitor", "monitor");

//step3 create the statement object
            Statement stmt = con.createStatement();
            List<Datafile> lst = new ArrayList<>();

//step4 execute query
            ResultSet rs = stmt.executeQuery("select * from \"MONITOR\".\"DATAFILE\"");
            while (rs.next()) {
                Datafile cpu = new Datafile(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getLong(5),rs.getLong(6), rs.getInt(7),rs.getString(8),rs.getString(9), rs.getTimestamp(10));
                lst.add(cpu);
            }

//step5 close the connection object
            con.close();
            return lst;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
