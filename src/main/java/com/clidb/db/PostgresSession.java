package com.clidb.db;

public class PostgresSession extends DbSession
{
    private final String schema;

    public PostgresSession(String host, int port, String databaseName, String username, String password, String schema)
    {
        super(host, port, databaseName, username, password);
        this.schema = schema;
    }

    public String getSchema()
    {
        return schema;
    }

    @Override
    public String getConnectionUrl()
    {
        return "jdbc:postgresql://" + host + ":" + port + "/" + databaseName + "?currentSchema=" + schema;
    }
}
