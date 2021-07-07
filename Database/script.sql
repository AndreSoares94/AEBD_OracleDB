/*** Criação TableSpaces ***/
CREATE TABLESPACE monitor_tables 
    DATAFILE 'monitor_tables_01.dbf' 
    SIZE 400M;

CREATE TEMPORARY TABLESPACE monitor_temp 
    TEMPFILE 'monitor_temp_01.dbf' 
    SIZE 200M AUTOEXTEND ON;

SELECT * FROM DBA_TABLESPACES;

/*** Criação User ***/
CREATE USER monitor IDENTIFIED BY monitor 
    DEFAULT TABLESPACE monitor_tables 
    TEMPORARY TABLESPACE monitor_temp 
    QUOTA UNLIMITED ON monitor_tables
    ACCOUNT UNLOCK;

GRANT 
    CONNECT,RESOURCE,DBA,
    CREATE SESSION,
    CREATE VIEW, 
    CREATE SEQUENCE,
    CREATE TABLE,
    CREATE TRIGGER,
    CREATE PROCEDURE,
    DROP ANY TABLE,
    INSERT ANY TABLE,
    SELECT ANY TABLE,
    UPDATE ANY TABLE,
    ALTER ANY TABLE,
    DELETE ANY TABLE
    TO monitor; 


SELECT * FROM DBA_USERS;


/*** Tables ***/
SELECT * FROM DBA_TABLES;

/*** Datafiles (& Tempfiles) ***/
SELECT * FROM DBA_DATA_FILES;
SELECT * FROM DBA_TEMP_FILES;


/*** Memory ***/
SELECT name, value FROM v$sga;

SELECT sum(bytes)/1024 AS "Free MB" FROM v$sgastat 
    WHERE name = 'free memory';


/*** Sessions ***/
SELECT * FROM v$session;

/*** CPU per session ***/
SELECT se.username, ss.sid, ROUND (value/100) "CPU Usage"
FROM v$session se, v$sesstat ss, v$statname st
WHERE ss.statistic# = st.statistic#
   AND name LIKE  '%CPU used by this session%'
   AND se.sid = ss.SID 
   AND se.username IS NOT NULL
  ORDER BY value DESC;
  
/*** total CPU usage ***/
SELECT sum(ROUND (value/100)) "total CPU Usage"
FROM v$session se, v$sesstat ss, v$statname st
WHERE ss.statistic# = st.statistic#
   AND name LIKE  '%CPU used by this session%'
   AND se.sid = ss.SID 
   AND se.username IS NOT NULL;




