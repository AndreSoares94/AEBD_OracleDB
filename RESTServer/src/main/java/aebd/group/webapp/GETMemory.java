package aebd.group.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GETMemory {
    public List<Memory> memory() {
        try {
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1.localdomain", "monitor", "monitor");

//step3 create the statement object
            Statement stmt = con.createStatement();
            List<Memory> lst = new ArrayList<>();

//step4 execute query
            ResultSet rs = stmt.executeQuery("select * from \"MONITOR\".\"MEMORY\"");
            while (rs.next()) {
                Memory cpu = new Memory(rs.getTimestamp(1),rs.getFloat(2),rs.getFloat(3),rs.getFloat(4));
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
