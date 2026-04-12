package com.clidb.db.handler;

import com.clidb.db.DbSession;
import com.clidb.db.DbmsType;
import com.clidb.db.OracleSession;
import com.clidb.db.OracleSession.OracleConnectType;
import com.clidb.prompt.Prompter;

import java.util.Map;

public class SessionCreator
{
    private final Prompter prompter;

    public SessionCreator(Prompter prompter)
    {
        this.prompter = prompter;
    }

    public DbSession createDbSession()
    {
        DbSession dbSession = null;
        DbmsType dbmsType = (DbmsType) prompter.getEnumChoiceOrNull("DBMS Type", DbmsType.values());

        switch (dbmsType)
        {
            case ORACLE:
                dbSession = createOracleSession();
                break;
            case MYSQL:
                // not yet
                break;
            default:
                break;
        }

        if (dbSession == null || !dbSession.testConnection())
        {
            if (dbSession != null)
            {
                System.err.println("WARNING: DB session could not be established.");
            }
            return null;
        }

        return dbSession;
    }

    public DbSession createOracleSession()
    {
        try
        {
            Map<String, Object> values = prompter.getObjectValuesOrNull(OracleSession.class);
            return new OracleSession(
                    (String) values.get("host"),
                    (int) values.get("port"),
                    (String) values.get("username"),
                    (String) values.get("password"),
                    (OracleConnectType) values.get("connectType"),
                    (String) values.get("dbName")
            );
        } catch (RuntimeException e)
        {
            System.err.println("Failed to create session: " + e.getMessage());
            return null;
        }
    }
}
