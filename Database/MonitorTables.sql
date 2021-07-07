-- DROPS
DROP TABLE "MONITOR"."SESSION" CASCADE CONSTRAINTS;
DROP TABLE "MONITOR"."TABLE" CASCADE CONSTRAINTS;
DROP TABLE "MONITOR"."USER" CASCADE CONSTRAINTS;
DROP TABLE "MONITOR"."TABLESPACE" CASCADE CONSTRAINTS;
DROP TABLE "MONITOR"."DATAFILE" CASCADE CONSTRAINTS;
DROP TABLE "MONITOR"."MEMORY" CASCADE CONSTRAINTS;
DROP TABLE "MONITOR"."CPU" CASCADE CONSTRAINTS;

SET AUTOCOMMIT ON;

-- DATAFILE
CREATE TABLE "MONITOR"."DATAFILE" (
    "id"                        NUMBER NOT NULL,
    "name"                      VARCHAR2(300) NOT NULL,
    "type"                      VARCHAR2(100) NOT NULL,    -- temp or data
    "used_bytes"                NUMBER NOT NULL,
    "total_bytes"               NUMBER NOT NULL,
    "free_bytes"                NUMBER NOT NULL,
    "percetage_free_bytes"      NUMBER NOT NULL,        
    "status"                    VARCHAR2(100) NOT NULL,
    "autoextensible"            VARCHAR2(3) NOT NULL,    -- yes or no
    "timestamp"                 TIMESTAMP NOT NULL,
    CONSTRAINT DATAFILE_PK PRIMARY KEY ( "id" )
)
LOGGING;


-- TABLESPACE
CREATE TABLE "MONITOR"."TABLESPACE" (
    "name"                  VARCHAR2(100) NOT NULL,
    "datafile_id"           NUMBER NOT NULL,    
    "used_bytes"            NUMBER NOT NULL,
    "total_bytes"           NUMBER NOT NULL,
    "free_bytes"            NUMBER NOT NULL,
    "percetage_free_bytes"  NUMBER NOT NULL,
    "status"                VARCHAR2(100) NOT NULL,    
    "contents"              VARCHAR2(100) NOT NULL,
    "timestamp"             TIMESTAMP NOT NULL,
    CONSTRAINT TABLESPACE_PK PRIMARY KEY ("name"),
    CONSTRAINT TABLESPACE_DATAFILE_FK FOREIGN KEY ( "datafile_id" )
        REFERENCES "MONITOR"."DATAFILE" ( "id" )
)
LOGGING;


-- USER
CREATE TABLE "MONITOR"."USER" (
    "id"                  NUMBER NOT NULL,
    "name"                VARCHAR2(100) NOT NULL,
    "default_tablespace"  VARCHAR2(100) NOT NULL,
    "temp_tablespace"     VARCHAR2(100) NOT NULL,
    "account_status"      VARCHAR2(100) NOT NULL,
    "timestamp"           TIMESTAMP NOT NULL,
    CONSTRAINT USER_PK PRIMARY KEY ( "id" ),
    CONSTRAINT USER_DEF_TABLESPACE_FK FOREIGN KEY ( "default_tablespace" )
        REFERENCES "MONITOR"."TABLESPACE" ( "name" ),
    CONSTRAINT USER_TEMP_TABLESPACE_FK FOREIGN KEY ( "temp_tablespace" )
        REFERENCES "MONITOR"."TABLESPACE" ( "name" )
)
LOGGING;


-- TABLE
CREATE TABLE "MONITOR"."TABLE" (
    "user_id"           NUMBER NOT NULL,         -- owner
    "name"              VARCHAR2(100) NOT NULL,
    "tablespace_name"   VARCHAR2(100) NOT NULL,
    "rows"              NUMBER NOT NULL,
    "timestamp"         TIMESTAMP NOT NULL,
    CONSTRAINT TABLE_PK PRIMARY KEY ( "name" ),
    CONSTRAINT TABLE_USER_FK FOREIGN KEY ( "user_id" )
        REFERENCES "MONITOR"."USER" ( "id" )
)
LOGGING;


-- SESSION
CREATE TABLE "MONITOR"."SESSION" (
    "id"          VARCHAR2(100) NOT NULL,
    "user_id"     NUMBER NOT NULL,
    "status"      VARCHAR2(100) NOT NULL,
    "schema_name" VARCHAR2(100) NOT NULL,
    "machine"     VARCHAR2(100) NOT NULL,
    "port"        NUMBER NOT NULL,
    "type"        VARCHAR2(100),
    "event"       VARCHAR2(200) NOT NULL,
    "logon_time"  DATE NOT NULL,
    "timestamp"   TIMESTAMP NOT NULL,
    CONSTRAINT SESSION_PK PRIMARY KEY ( "id" ),
    CONSTRAINT SESSION_USER_FK FOREIGN KEY ( "user_id" )
        REFERENCES "MONITOR"."USER" ( "id" )
)
LOGGING;


-- MEMORY
CREATE TABLE "MONITOR"."MEMORY" (
    "timestamp"       TIMESTAMP NOT NULL,
    "total_size"      NUMBER NOT NULL,
    "free_size"       NUMBER NOT NULL,
    "percentage_free" NUMBER NOT NULL,
    CONSTRAINT MEMORY_PK PRIMARY KEY ( "timestamp" )
)
LOGGING;


-- CPU
CREATE TABLE "MONITOR"."CPU" (
    "timestamp"   TIMESTAMP NOT NULL,
    "cpuUsage"    NUMBER NOT NULL,
    CONSTRAINT CPU_PK PRIMARY KEY ( "timestamp" )
)
LOGGING;

-- insert into MONITOR.CPU values(CURRENT_TIMESTAMP,94); commit;

-- SELECTS
SELECT * FROM "MONITOR"."DATAFILE";
SELECT * FROM "MONITOR"."TABLESPACE";
SELECT * FROM "MONITOR"."USER";
SELECT * FROM "MONITOR"."SESSION";
SELECT * FROM "MONITOR"."TABLE";
SELECT * FROM "MONITOR"."CPU";
SELECT * FROM "MONITOR"."MEMORY";