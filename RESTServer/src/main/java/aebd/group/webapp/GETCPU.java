package aebd.group.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GETCPU {
    public List<CPU> cpu() {
        try {
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1.localdomain", "monitor", "monitor");

//step3 create the statement object
            Statement stmt = con.createStatement();
            List<CPU> lst = new ArrayList<>();

//step4 execute query
            ResultSet rs = stmt.executeQuery("select * from \"MONITOR\".\"CPU\"");
            while (rs.next()) {
                CPU cpu = new CPU(rs.getTimestamp(1),rs.getInt(2));
                System.out.println(rs.getInt(2));
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
