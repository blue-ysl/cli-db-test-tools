package com.clidb.db;

import com.clidb.prompt.SkipPrompt;

import java.time.LocalDateTime;

public abstract class DbSession
{
    protected DbmsType dbmsType;
    protected String host;
    protected int port;
    protected String databaseName;
    protected String username;
    protected String password;
    @SkipPrompt
    protected LocalDateTime createdTime;

    public DbSession(DbmsType dbmsType, String host, int port, String databaseName, String username, String password)
    {
        this.dbmsType = dbmsType;
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        this.createdTime = LocalDateTime.now();
    }

    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getDatabaseName() { return databaseName; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public abstract String getConnectionUrl();
}
