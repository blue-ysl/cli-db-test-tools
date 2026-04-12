package com.clidb.db;

public class MySqlSession extends DbSession
{
    private final boolean useSSL;

    public MySqlSession(String host, int port, String databaseName, String username, String password, boolean useSSL)
    {
        super(host, port, databaseName, username, password);
        this.useSSL = useSSL;
    }

    public boolean isUseSSL()
    {
        return useSSL;
    }

    @Override
    public String getConnectionUrl()
    {
        return "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?useSSL=" + useSSL;
    }
}
