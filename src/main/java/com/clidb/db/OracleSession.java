package com.clidb.db;

public class OracleSession extends DbSession
{
    public enum OracleConnectType
    {
        SID, SERVICE_NAME
    }

    private final OracleConnectType connectType;
    private final String dbName;

    public OracleSession(String host, int port, String username, String password, OracleConnectType connectType, String dbName)
    {
        super(DbmsType.ORACLE, host, port, username, password);
        this.connectType = connectType;
        this.dbName = dbName;
    }

    public OracleConnectType getOracleConnectType()
    {
        return connectType;
    }

    public String getDbName()
    {
        return dbName;
    }

    @Override
    public String getConnectionUrl()
    {
        if (connectType == OracleConnectType.SERVICE_NAME)
        {
            return "jdbc:oracle:thin:@//" + host + ":" + port + "/" + dbName;
        }
        else
        {
            return "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName;
        }
    }
}
