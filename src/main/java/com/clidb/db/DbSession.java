package com.clidb.db;

import com.clidb.prompt.SkipPrompt;

import java.time.Instant;

public abstract class DbSession
{
    protected DbmsType dbmsType;
    protected String host;
    protected int port;
    protected String databaseName;
    protected String username;
    protected String password;
    @SkipPrompt
    protected Instant createdTime;

    public DbSession(DbmsType dbmsType, String host, int port, String databaseName, String username, String password)
    {
        this.dbmsType = dbmsType;
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        this.createdTime = Instant.now();
    }

    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getDatabaseName() { return databaseName; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Instant getCreatedTime() {return createdTime; }

    public abstract String getConnectionUrl();
}
