package com.clidb.db.handler;

import com.clidb.db.DbSession;
import com.clidb.db.MariaDbSession;
import com.clidb.db.MySqlSession;
import com.clidb.db.OracleSession;
import com.clidb.db.OracleSession.OracleConnectType;
import com.clidb.db.PostgresSession;
import prompt.Prompter;

import java.util.Map;
import java.util.Optional;

public class SessionCreator
{
    private final Prompter prompter;

    public SessionCreator(Prompter prompter)
    {
        this.prompter = prompter;
    }

    public Optional<DbSession> createOracleSession()
    {
        try
        {
            Map<String, Object> values = prompter.getObjectValuesOrNull(OracleSession.class);
            return Optional.of(new OracleSession(
                    (String) values.get("host"),
                    (int) values.get("port"),
                    (String) values.get("databaseName"),
                    (String) values.get("username"),
                    (String) values.get("password"),
                    (OracleConnectType) values.get("connectType"),
                    (String) values.get("connectId")
            ));
        }
        catch (RuntimeException e)
        {
            System.err.println("Failed to create session: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<DbSession> createMySqlSession()
    {
        try
        {
            Map<String, Object> values = prompter.getObjectValuesOrNull(MySqlSession.class);
            return Optional.of(new MySqlSession(
                    (String) values.get("host"),
                    (int) values.get("port"),
                    (String) values.get("databaseName"),
                    (String) values.get("username"),
                    (String) values.get("password"),
                    (boolean) values.get("useSSL")
            ));
        }
        catch (RuntimeException e)
        {
            System.err.println("Failed to create session: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<DbSession> createMariaDbSession()
    {
        try
        {
            Map<String, Object> values = prompter.getObjectValuesOrNull(MariaDbSession.class);
            return Optional.of(new MariaDbSession(
                    (String) values.get("host"),
                    (int) values.get("port"),
                    (String) values.get("databaseName"),
                    (String) values.get("username"),
                    (String) values.get("password"),
                    (boolean) values.get("useCompression")
            ));
        }
        catch (RuntimeException e)
        {
            System.err.println("Failed to create session: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<DbSession> createPostgresSession()
    {
        try
        {
            Map<String, Object> values = prompter.getObjectValuesOrNull(PostgresSession.class);
            return Optional.of(new PostgresSession(
                    (String) values.get("host"),
                    (int) values.get("port"),
                    (String) values.get("databaseName"),
                    (String) values.get("username"),
                    (String) values.get("password"),
                    (String) values.get("schema")
            ));
        }
        catch (RuntimeException e)
        {
            System.err.println("Failed to create session: " + e.getMessage());
            return Optional.empty();
        }
    }
}
