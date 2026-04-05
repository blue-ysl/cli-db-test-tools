package db.handler;

import db.DbSession;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionCreatorTest
{
    private SessionCreator sessionCreatorWith(String... inputLines)
    {
        String input = String.join("\n", inputLines);
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        return new SessionCreator(new Prompter());
    }

    // Prompt order for OracleSession: host, port, databaseName, username, password, connectType, connectId
    @Test
    void createOracleSession_withSid()
    {
        SessionCreator creator = sessionCreatorWith(
                "192.168.0.1", "1521", "ORCL", "admin", "password", "SID", "ORCL");

        Optional<DbSession> session = creator.createOracleSession();

        assertTrue(session.isPresent());
        assertEquals("jdbc:oracle:thin:@192.168.0.1:1521:ORCL", session.get().getConnectionUrl());
    }

    @Test
    void createOracleSession_withServiceName()
    {
        SessionCreator creator = sessionCreatorWith(
                "192.168.0.1", "1521", "ORCL", "admin", "password", "SERVICE_NAME", "myservice.db");

        Optional<DbSession> session = creator.createOracleSession();

        assertTrue(session.isPresent());
        assertEquals("jdbc:oracle:thin:@//192.168.0.1:1521/myservice.db", session.get().getConnectionUrl());
    }
}
