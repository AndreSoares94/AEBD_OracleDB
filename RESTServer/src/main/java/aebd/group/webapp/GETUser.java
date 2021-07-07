package aebd.group.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GETUser {
    public List<User> user() {
        try {
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1.localdomain", "monitor", "monitor");

//step3 create the statement object
            Statement stmt = con.createStatement();
            List<User> lst = new ArrayList<>();

//step4 execute query
            ResultSet rs = stmt.executeQuery("select * from \"MONITOR\".\"USER\"");
            while (rs.next()) {
                User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getTimestamp(6));
                lst.add(user);
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
