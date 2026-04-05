package db.handler;

import db.DbSession;
import db.MariaDbSession;
import db.MySqlSession;
import db.OracleSession;
import db.OracleSession.OracleConnectType;
import db.PostgresSession;

import java.io.IOException;
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
            Map<String, Object> values = prompter.prompt(OracleSession.class);
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
        catch (IOException | RuntimeException e)
        {
            System.err.println("Failed to create session: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<DbSession> createMySqlSession()
    {
        try
        {
            Map<String, Object> values = prompter.prompt(MySqlSession.class);
            return Optional.of(new MySqlSession(
                    (String) values.get("host"),
                    (int) values.get("port"),
                    (String) values.get("databaseName"),
                    (String) values.get("username"),
                    (String) values.get("password"),
                    (boolean) values.get("useSSL")
            ));
        }
        catch (IOException | RuntimeException e)
        {
            System.err.println("Failed to create session: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<DbSession> createMariaDbSession()
    {
        try
        {
            Map<String, Object> values = prompter.prompt(MariaDbSession.class);
            return Optional.of(new MariaDbSession(
                    (String) values.get("host"),
                    (int) values.get("port"),
                    (String) values.get("databaseName"),
                    (String) values.get("username"),
                    (String) values.get("password"),
                    (boolean) values.get("useCompression")
            ));
        }
        catch (IOException | RuntimeException e)
        {
            System.err.println("Failed to create session: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<DbSession> createPostgresSession()
    {
        try
        {
            Map<String, Object> values = prompter.prompt(PostgresSession.class);
            return Optional.of(new PostgresSession(
                    (String) values.get("host"),
                    (int) values.get("port"),
                    (String) values.get("databaseName"),
                    (String) values.get("username"),
                    (String) values.get("password"),
                    (String) values.get("schema")
            ));
        }
        catch (IOException | RuntimeException e)
        {
            System.err.println("Failed to create session: " + e.getMessage());
            return Optional.empty();
        }
    }
}
