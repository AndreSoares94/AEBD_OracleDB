package aebd.group.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GETTablespace {
    public List<TableSpace> datafile() {
        try {
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1.localdomain", "monitor", "monitor");

//step3 create the statement object
            Statement stmt = con.createStatement();
            List<TableSpace> lst = new ArrayList<>();

//step4 execute query
            ResultSet rs = stmt.executeQuery("select * from \"MONITOR\".\"TABLESPACE\"");
            while (rs.next()) {
                TableSpace cpu = new TableSpace(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5), rs.getInt(6),rs.getString(7),rs.getString(8), rs.getTimestamp(9));
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
