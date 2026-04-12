package com.clidb.db;

import com.clidb.prompt.SkipPrompt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;

public abstract class DbSession
{
    @SkipPrompt
    protected DbmsType dbmsType;
    protected String host;
    protected int port;
    protected String username;
    protected String password;
    @SkipPrompt
    protected Instant createdTime;
    @SkipPrompt
    protected Connection connection;

    public DbSession(DbmsType dbmsType, String host, int port, String username, String password)
    {
        this.dbmsType = dbmsType;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.createdTime = Instant.now();
    }

    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Instant getCreatedTime() {return createdTime; }

    public abstract String getConnectionUrl();
    public boolean testConnection()
    {
        try (Connection conn = DriverManager.getConnection(getConnectionUrl(), username, password))
        {
            return conn.isValid(3);
        }
        catch (SQLException e)
        {
            System.err.println("Connection test failed: " + e.getMessage());
            return false;
        }
    }
}
