import java.sql.*;
import java.util.Properties;
import java.lang.String ;
import java.util.Date ;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Collector {

    public static void main(String[] args){
        Connection sysConn = null;
        Connection monitorConn = null;
        String query;
        int i;

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            // PDB ORCL SYS
            sysConn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1.localdomain",
                    "system",
                    "Oradoc_db1"
            );

            // PDB ORCL Monitor
            monitorConn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1.localdomain",
                    "Monitor",
                    "monitor"
            );


            if (sysConn != null) {
                System.out.println("$ sys.orcl: Connected.");
            }
            else{
                System.out.println("$ sys.orcl: Failed connection.");
            }

            if (monitorConn != null) {
                System.out.println("$ Monitor.orcl: Connected.");
            }
            else{
                System.out.println("$ Monitor.orcl: Failed connection.");
            }

            sysConn.createStatement().executeUpdate("delete from monitor.\"SESSION\"");
            sysConn.createStatement().executeUpdate("delete from monitor.\"TABLE\"");
            sysConn.createStatement().executeUpdate("delete from monitor.\"USER\"");
            sysConn.createStatement().executeUpdate("delete from monitor.\"TABLESPACE\"");
            sysConn.createStatement().executeUpdate("delete from monitor.\"DATAFILE\"");
            sysConn.createStatement().executeUpdate("delete from monitor.\"MEMORY\"");
            sysConn.createStatement().executeUpdate("delete from monitor.\"CPU\"");

            while (true){

                Statement getStmt = sysConn.createStatement();

                /**
                 * DATAFILES
                 */
                String getDatafiles = "SELECT * FROM dba_data_files" ;

                ResultSet resultSet = getStmt.executeQuery(getDatafiles);
                while(resultSet.next()) {

                    float usedDFile = Float.parseFloat(resultSet.getString("BYTES"));
                    float totalDfile = Float.parseFloat(resultSet.getString("MAXBYTES"));
                    if(totalDfile == 0) totalDfile=usedDFile;

                    Statement stmt1 = monitorConn.createStatement();
                    String updateDatafiles = "UPDATE \"MONITOR\".\"DATAFILE\" " +
                            " SET \"name\" = " + "'"+resultSet.getString("FILE_NAME")+"'" +
                            "," + " \"type\" = " + "'data'" +
                            "," + " \"used_bytes\" = " + Integer.parseInt(resultSet.getString("BYTES")) +
                            "," + " \"total_bytes\" = " + Float.parseFloat(resultSet.getString("MAXBYTES")) +
                            "," + " \"free_bytes\" = " + (totalDfile-usedDFile) +
                            "," + " \"percetage_free_bytes\" = " + (1-(usedDFile/totalDfile)) +
                            "," + " \"status\" = " + "'" + resultSet.getString("STATUS") + "'" +
                            "," + " \"autoextensible\" = " + "'"+ resultSet.getString("AUTOEXTENSIBLE") + "'" +
                            "," + " \"timestamp\" = CURRENT_TIMESTAMP" +
                            " WHERE \"id\" = " + resultSet.getString("FILE_ID");

                    i = stmt1.executeUpdate(updateDatafiles);
                    if(i==0) {
                        query = "INSERT INTO MONITOR.datafile VALUES("
                                + resultSet.getString("FILE_ID") + ","
                                + "'" + resultSet.getString("FILE_NAME") + "'" +","
                                + "'data',"
                                + Integer.parseInt(resultSet.getString("BYTES")) + ","
                                + Float.parseFloat(resultSet.getString("MAXBYTES")) + ","
                                + (totalDfile-usedDFile) + ","
                                + (1-(usedDFile/totalDfile)) + ","
                                + "'" + resultSet.getString("STATUS") + "',"
                                + "'" + resultSet.getString("AUTOEXTENSIBLE") + "',"
                                + "CURRENT_TIMESTAMP)";
                        stmt1.executeUpdate(query);
                        stmt1.executeUpdate("commit");
                        //System.out.println(query);
                    } else {
                        stmt1.executeUpdate("commit");
                        //System.out.println(updateDatafiles);
                    }
                    stmt1.close();
                }
                System.out.println("DATAFILES DONE");

                /**
                 * DATAFILES TEMP
                 */
                String getDatafilesTEMP = "SELECT * FROM DBA_TEMP_FILES" ;

                ResultSet datafilesTEMP = getStmt.executeQuery(getDatafilesTEMP);
                while(datafilesTEMP.next()) {

                    float usedDFile = Float.parseFloat(datafilesTEMP.getString("BYTES"));
                    float totalDfile = Float.parseFloat(datafilesTEMP.getString("MAXBYTES"));
                    if(totalDfile == 0) totalDfile=usedDFile;

                    Statement datafilesTempStmt = monitorConn.createStatement();
                    String updateDatafilesTemp = "UPDATE \"MONITOR\".\"DATAFILE\" " +
                            " SET \"name\" = " + "'"+datafilesTEMP.getString("FILE_NAME")+"'" +
                            "," + " \"type\" = " + "'temp'" +
                            "," + " \"used_bytes\" = " + Integer.parseInt(datafilesTEMP.getString("BYTES")) +
                            "," + " \"total_bytes\" = " + Float.parseFloat(datafilesTEMP.getString("MAXBYTES")) +
                            "," + " \"free_bytes\" = " + (totalDfile-usedDFile) +
                            "," + " \"percetage_free_bytes\" = " + (1-(usedDFile/totalDfile)) +
                            "," + " \"status\" = " + "'" + datafilesTEMP.getString("STATUS") + "'" +
                            "," + " \"autoextensible\" = " + "'"+ datafilesTEMP.getString("AUTOEXTENSIBLE") + "'" +
                            "," + " \"timestamp\" = CURRENT_TIMESTAMP" +
                            " WHERE \"id\" = " + datafilesTEMP.getString("FILE_ID");

                    i = datafilesTempStmt.executeUpdate(updateDatafilesTemp);
                    if(i==0) {
                        query = "INSERT INTO MONITOR.datafile VALUES("
                                + datafilesTEMP.getString("FILE_ID") + ","
                                + "'" + datafilesTEMP.getString("FILE_NAME") + "'" +","
                                + "'temp',"
                                + Integer.parseInt(datafilesTEMP.getString("BYTES")) + ","
                                + Float.parseFloat(datafilesTEMP.getString("MAXBYTES")) + ","
                                + (totalDfile-usedDFile) + ","
                                + (1-(usedDFile/totalDfile)) + ","
                                + "'" + datafilesTEMP.getString("STATUS") + "',"
                                + "'" + datafilesTEMP.getString("AUTOEXTENSIBLE") + "',"
                                + "CURRENT_TIMESTAMP)";
                        datafilesTempStmt.executeUpdate(query);
                        datafilesTempStmt.executeUpdate("commit");
                        //System.out.println(query);
                    } else {
                        datafilesTempStmt.executeUpdate("commit");
                        //System.out.println(updateDatafiles);
                    }
                    datafilesTempStmt.close();
                }
                System.out.println("DATAFILES TEMP DONE");

                /**
                 * MEMORY
                 */
                ResultSet sga = getStmt.executeQuery("select (sga+pga)/1024/1024 as \"sga_pga\"\n" +
                        "from \n" +
                        "(select sum(value) sga from v$sga),\n" +
                        "(select sum(pga_used_mem) pga from v$process)");
                double memtotal = 0; /* mem total */
                double freebytes = 0; /* mem usada */
                while(sga.next()){
                    memtotal = sga.getDouble(1);
                }
                ResultSet sga2 = getStmt.executeQuery("select sum(bytes)/1024 from v$sgastat where name = 'free memory'");
                while(sga2.next()){
                    freebytes = sga2.getDouble(1)/1024;
                }
                //System.out.println("Mem total:  "+memtotal+"\nMem free: "+freebytes+"\nMem Perc: "+freebytes/memtotal);

                Statement sgaStmt = monitorConn.createStatement();

                String updateSga = " INSERT INTO MONITOR.MEMORY VALUES(CURRENT_TIMESTAMP, "
                        + memtotal +", " + freebytes + ", " + ((freebytes/memtotal)*100) + ")";
                i = sgaStmt.executeUpdate(updateSga);
                sgaStmt.executeUpdate("commit");
                if(i==0) System.out.println("ErrorUpdateSga");
                sgaStmt.close();
                System.out.println("MEMORY DONE");
                /**
                 * TABLESPACES PERMANENTE
                 */

                String getTablespaces = "SELECT " +
                        "ts.tablespace_name, df.file_id, ts.status, ts.contents, " +
                        "TRUNC(\"SIZE(MB)\", 2) \"Size(MB)\", " +
                        "TRUNC(fr.\"FREE(MB)\", 2) \"Free(MB)\", " +
                        "TRUNC(\"SIZE(MB)\" - \"FREE(MB)\", 2) \"Used(MB)\", " +
                        "round((fr.\"FREE(MB)\" / df.\"SIZE(MB)\") * 100,2) \"Percentage\" " +
                        "FROM " +
                        "(SELECT tablespace_name, " +
                        "SUM (bytes) / (1024 * 1024) \"FREE(MB)\" " +
                        "FROM dba_free_space " +
                        "GROUP BY tablespace_name) fr, " +
                        "(SELECT tablespace_name, file_id, SUM(bytes) / (1024 * 1024) \"SIZE(MB)\", COUNT(*) " +
                        "\"File Count\", SUM(maxbytes) / (1024 * 1024) \"MAX_EXT\" " +
                        "FROM dba_data_files " +
                        "GROUP BY tablespace_name, file_id) df, " +
                        "(SELECT tablespace_name, status, contents " +
                        "FROM dba_tablespaces) ts " +
                        "WHERE fr.tablespace_name = df.tablespace_name (+) " +
                        "AND fr.tablespace_name = ts.tablespace_name (+) " +
                        "ORDER BY \"Percentage\" desc";
                ResultSet tablesSpac = getStmt.executeQuery(getTablespaces);

                while(tablesSpac.next()) {
                    Statement TablesSpacesStmt = monitorConn.createStatement();
                    String updateQuery = "UPDATE \"MONITOR\".\"TABLESPACE\" " +
                            " SET \"datafile_id\" = " + tablesSpac.getString("FILE_ID") +
                            "," + " \"used_bytes\" = " + Float.parseFloat(tablesSpac.getString("Used(MB)")) +
                            "," + " \"total_bytes\" = " + Integer.parseInt(tablesSpac.getString("SIZE(MB)")) +
                            "," + " \"free_bytes\" = " + Float.parseFloat(tablesSpac.getString("FREE(MB)")) +
                            "," + " \"percetage_free_bytes\" = " + Float.parseFloat(tablesSpac.getString("Percentage")) +
                            "," + " \"status\" = " + "'"+ tablesSpac.getString("STATUS")+"'"+
                            "," + " \"contents\" = "  + "'"+ tablesSpac.getString("Contents")+"'"+
                            "," + " \"timestamp\" = CURRENT_TIMESTAMP"+
                            " WHERE \"name\" = " + "'" + tablesSpac.getString("tablespace_name") + "'";;

                    //System.out.println(updateQuery);
                    // devolve o número queries afetadas
                    i = TablesSpacesStmt.executeUpdate(updateQuery);
                    if(i==0) {
                        query = "INSERT INTO MONITOR.tablespace VALUES("
                                + "'"+ tablesSpac.getString("tablespace_name") +"',"
                                + tablesSpac.getString("FILE_ID") + ","
                                + Float.parseFloat(tablesSpac.getString("Used(MB)")) +","
                                + Integer.parseInt(tablesSpac.getString("SIZE(MB)")) +","
                                + Float.parseFloat(tablesSpac.getString("FREE(MB)")) + ","
                                + Float.parseFloat(tablesSpac.getString("Percentage")) + ","
                                + "'"+ tablesSpac.getString("STATUS") + "',"
                                + "'" + tablesSpac.getString("Contents") + "',"
                                + "CURRENT_TIMESTAMP)";
                        TablesSpacesStmt.executeUpdate(query);
                        TablesSpacesStmt.executeUpdate("commit");
                    } else {
                        TablesSpacesStmt.executeUpdate("commit");
                    }
                    TablesSpacesStmt.close();
                }

                System.out.println("TABLESPACES PERMANENT DONE");

                /**
                 * TABLESPACES TEMP
                 */

                String getTablespacesTemp = "SELECT " +
                        "ts.tablespace_name, df.file_id, ts.status, ts.contents, " +
                        "TRUNC(\"SIZE(MB)\", 2) \"Size(MB)\", " +
                        "TRUNC(fr.\"FREE(MB)\", 2) \"Free(MB)\", " +
                        "TRUNC(\"SIZE(MB)\" - \"FREE(MB)\", 2) \"Used(MB)\", " +
                        "round((fr.\"FREE(MB)\" / df.\"SIZE(MB)\") * 100,2) \"Percentage\" " +
                        "FROM " +
                        "(SELECT tablespace_name, " +
                        "SUM (FREE_SPACE) / (1024 * 1024) \"FREE(MB)\" " +
                        "FROM dba_temp_free_space " +
                        "GROUP BY tablespace_name) fr, " +
                        "(SELECT tablespace_name, file_id, SUM(bytes) / (1024 * 1024) \"SIZE(MB)\", COUNT(*) " +
                        "\"File Count\", SUM(maxbytes) / (1024 * 1024) \"MAX_EXT\" " +
                        "FROM DBA_TEMP_FILES " +
                        "GROUP BY tablespace_name, file_id) df, " +
                        "(SELECT tablespace_name, status, contents " +
                        "FROM dba_tablespaces) ts " +
                        "WHERE fr.tablespace_name = df.tablespace_name (+) " +
                        "AND fr.tablespace_name = ts.tablespace_name (+) " +
                        "ORDER BY \"Percentage\" desc";
                //System.out.println(getTablespacesTemp);
                ResultSet tablesSpacTemp = getStmt.executeQuery(getTablespacesTemp);

                while(tablesSpacTemp.next()) {
                    Statement TablesSpacesTempStmt = monitorConn.createStatement();
                    String updateQuery = "UPDATE \"MONITOR\".\"TABLESPACE\" " +
                            " SET \"datafile_id\" = " + tablesSpacTemp.getString("FILE_ID") +
                            "," + " \"used_bytes\" = " + Float.parseFloat(tablesSpacTemp.getString("Used(MB)")) +
                            "," + " \"total_bytes\" = " + Integer.parseInt(tablesSpacTemp.getString("SIZE(MB)")) +
                            "," + " \"free_bytes\" = " + Float.parseFloat(tablesSpacTemp.getString("FREE(MB)")) +
                            "," + " \"percetage_free_bytes\" = " + Float.parseFloat(tablesSpacTemp.getString("Percentage")) +
                            "," + " \"status\" = " + "'"+ tablesSpacTemp.getString("STATUS")+"'"+
                            "," + " \"contents\" = "  + "'"+ tablesSpacTemp.getString("Contents")+"'"+
                            "," + " \"timestamp\" = CURRENT_TIMESTAMP"+
                            " WHERE \"name\" = " + "'" + tablesSpacTemp.getString("tablespace_name") + "'";;

                    //System.out.println(updateQuery);
                    // devolve o número queries afetadas
                    i = TablesSpacesTempStmt.executeUpdate(updateQuery);
                    if(i==0) {
                        query = "INSERT INTO MONITOR.tablespace VALUES("
                                + "'"+ tablesSpacTemp.getString("tablespace_name") +"',"
                                + tablesSpacTemp.getString("FILE_ID") + ","
                                + Float.parseFloat(tablesSpacTemp.getString("Used(MB)")) +","
                                + Integer.parseInt(tablesSpacTemp.getString("SIZE(MB)")) +","
                                + Float.parseFloat(tablesSpacTemp.getString("FREE(MB)")) + ","
                                + Float.parseFloat(tablesSpacTemp.getString("Percentage")) + ","
                                + "'"+ tablesSpacTemp.getString("STATUS") + "',"
                                + "'" + tablesSpacTemp.getString("Contents") + "',"
                                + "CURRENT_TIMESTAMP)";
                        TablesSpacesTempStmt.executeUpdate(query);
                        TablesSpacesTempStmt.executeUpdate("commit");
                    } else {
                        TablesSpacesTempStmt.executeUpdate("commit");
                    }
                    TablesSpacesTempStmt.close();
                }

                System.out.println("TABLESPACES temp DONE");

                /**
                 * USERS
                 */
                ResultSet users = getStmt.executeQuery("select user_id, username, default_tablespace,"
                        + "temporary_tablespace, account_status from dba_users");

                while(users.next()){
                    Statement usersStmt = monitorConn.createStatement();
                    String updateQuery = "UPDATE \"MONITOR\".\"USER\" " +
                            " SET \"name\" = " + "'" + users.getString("USERNAME") + "'" +
                            "," + " \"default_tablespace\" = " + "'" + users.getString("DEFAULT_TABLESPACE") + "'" +
                            "," + " \"temp_tablespace\" = " + "'" + users.getString("TEMPORARY_TABLESPACE") + "'" +
                            "," + " \"account_status\" = " + "'" + users.getString("ACCOUNT_STATUS") + "'" +
                            "," + " \"timestamp\" = CURRENT_TIMESTAMP"+
                            " WHERE \"id\" = " + users.getString("USER_ID");;

                    //System.out.println(updateQuery);
                    // devolve o número queries afetadas
                    i = usersStmt.executeUpdate(updateQuery);
                    if(i==0) {
                        query = "INSERT INTO MONITOR.\"USER\" VALUES("
                                + users.getString("USER_ID") +","
                                + "'" + users.getString("USERNAME") + "',"
                                + "'" + users.getString("DEFAULT_TABLESPACE") + "',"
                                + "'" + users.getString("TEMPORARY_TABLESPACE") + "',"
                                + "'" + users.getString("ACCOUNT_STATUS") + "',"
                                + "CURRENT_TIMESTAMP)";
                        //System.out.println(query);
                        usersStmt.executeUpdate(query);
                        usersStmt.executeUpdate("commit");
                    } else {
                        usersStmt.executeUpdate("commit");
                    }
                    usersStmt.close();
                }

                System.out.println("USERS DONE");

                /**
                 * SESSIONS
                 */

                ResultSet sessions = getStmt.executeQuery("SELECT sid, username, user#, status, server, schemaname, osuser, machine, port, type, event, logon_time" +
                        " FROM v$session where username IS NOT NULL");

                while(sessions.next()){
                    Statement sessionsStmt = monitorConn.createStatement();
                    String updateQuery = "UPDATE \"MONITOR\".\"SESSION\" " +
                            " SET \"user_id\" = " + sessions.getString("USER#") +
                            "," + " \"status\" = " + "'" + sessions.getString("STATUS") + "'" +
                            "," + " \"schema_name\" = " + "'" + sessions.getString("SCHEMANAME") + "'" +
                            "," + " \"machine\" = " + "'" + sessions.getString("MACHINE") + "'" +
                            "," + " \"port\" = " + sessions.getString("PORT") +
                            "," + " \"type\" = " + "'" + sessions.getString("TYPE") + "'" +
                            "," + " \"event\" = " + "'" + sessions.getString("EVENT") + "'" +
                            "," + " \"logon_time\" = " + "'" + sessions.getString("LOGON_TIME").substring(0,10) + "'" +
                            "," + " \"timestamp\" = CURRENT_TIMESTAMP"+
                            " WHERE \"id\" = " + sessions.getString("SID");
                    i = sessionsStmt.executeUpdate(updateQuery);
                    if(i==0) {
                        query = "INSERT INTO MONITOR.\"SESSION\" VALUES("
                                + sessions.getString("SID") +","
                                + sessions.getString("USER#") +","
                                + "'" + sessions.getString("STATUS") + "',"
                                + "'" + sessions.getString("SCHEMANAME") + "',"
                                + "'" + sessions.getString("MACHINE") + "',"
                                + sessions.getString("PORT") + ","
                                + "'" + sessions.getString("TYPE") + "',"
                                + "'" + sessions.getString("EVENT") + "',"
                                + "'" + sessions.getString("LOGON_TIME").substring(0,10) + "',"
                                + "CURRENT_TIMESTAMP)";
                        //System.out.println(query);
                        sessionsStmt.executeUpdate(query);
                        sessionsStmt.executeUpdate("commit");
                    } else {
                        sessionsStmt.executeUpdate("commit");
                        //System.out.println(updateQuery);
                    }
                    sessionsStmt.close();
                }
                System.out.println("SESSIONS DONE");

                /**
                 * CPU
                 */
                ResultSet cpu = getStmt.executeQuery("SELECT USERNAME, SUM(CPU_USAGE) AS CPU_USAGE" +
                        " FROM (SELECT se.username, (value/100) AS CPU_USAGE" +
                        " FROM v$session se, v$sesstat ss, v$statname st" +
                        " WHERE ss.statistic# = st.statistic#" +
                        " AND name LIKE  '%CPU used by this session%'" +
                        " AND se.sid = ss.SID" +
                        " AND se.username IS NOT NULL" +
                        " ORDER BY value DESC" +
                        ")" +
                        " GROUP BY USERNAME");
                double cpu_total = 0;

                while(cpu.next()) {
                    cpu_total += Double.parseDouble(cpu.getString("CPU_USAGE"));
                }

                Statement cpuStmt = monitorConn.createStatement();

                String updateCpu = " UPDATE \"MONITOR\".\"CPU\"" +
                        " SET \"cpuUsage\" = " + cpu_total +
                        " WHERE \"timestamp\" = CURRENT_TIMESTAMP";
                //System.out.println(updateCpu);
                i = cpuStmt.executeUpdate(updateCpu);

                if(i==0) {
                    String insertcpu = "INSERT INTO MONITOR.\"CPU\" VALUES("
                            + "CURRENT_TIMESTAMP" + "," + cpu_total+")";
                    //System.out.println(insertcpu);
                    cpuStmt.executeUpdate(insertcpu);
                    cpuStmt.executeUpdate("commit");
                } else {
                    cpuStmt.executeUpdate("commit");
                }
                cpuStmt.close();

                System.out.println("CPU DONE");

                /**
                 * TABLES
                 */
                ResultSet tables = getStmt.executeQuery("select owner, u.user_id, table_name,tablespace_name,num_rows " +
                        "from dba_tables, dba_users u WHERE u.username = owner ORDER BY num_rows");


                while(tables.next()){
                    Statement tablesStmt = monitorConn.createStatement();
                    String updateQuery = "UPDATE \"MONITOR\".\"TABLE\" " +
                            "SET \"user_id\" ="+tables.getString("USER_ID")+
                            "," + " \"tablespace_name\" = " + "'" + tables.getString("TABLESPACE_NAME") + "'" +
                            "," + " \"rows\" = " + tables.getInt(5) +
                            "," + " \"timestamp\" = CURRENT_TIMESTAMP"+
                            " WHERE \"name\" = '" + tables.getString("TABLE_NAME") +"'";
                    //System.out.println(updateQuery);
                    i = tablesStmt.executeUpdate(updateQuery);
                    if(i==0) {
                        String insertTable = "INSERT INTO \"MONITOR\".\"TABLE\" VALUES("
                                + tables.getString("USER_ID")+ ","
                                + "'"+tables.getString("TABLE_NAME")+"',"
                                + "'"+tables.getString("TABLESPACE_NAME")+"',"
                                + tables.getInt(5) + ","
                                + "CURRENT_TIMESTAMP" + ")";
                        //System.out.println(insertTable);
                        tablesStmt.executeUpdate(insertTable);
                        tablesStmt.executeUpdate("commit");
                    } else {
                        tablesStmt.executeUpdate("commit");
                    }
                    tablesStmt.close();
                }

                System.out.println("TABLES DONE");
                System.out.println("waiting 10 s and running again");
                Thread.sleep(10000);
            }


        } catch (ClassNotFoundException e) {
            System.out.println("Classe não existe ou não foi encontrada.: " + e + e.getStackTrace()[0].getLineNumber());
        } catch (SQLException e) {
            System.out.println("Erro no SQL:" + e + e.getStackTrace()[0].getLineNumber());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
