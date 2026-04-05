package db;

public class OracleSession extends DbSession
{
    public enum OracleConnectType
    {
        SID, SERVICE_NAME
    }

    private final OracleConnectType connectType;
    private final String connectId;

    public OracleSession(String host, int port, String databaseName, String username, String password, OracleConnectType connectType, String connectId)
    {
        super(host, port, databaseName, username, password);
        this.connectType = connectType;
        this.connectId = connectId;
    }

    public OracleConnectType getOracleConnectType()
    {
        return connectType;
    }

    public String getConnectId()
    {
        return connectId;
    }

    @Override
    public String getConnectionUrl()
    {
        if (connectType == OracleConnectType.SERVICE_NAME)
        {
            return "jdbc:oracle:thin:@//" + host + ":" + port + "/" + connectId;
        }
        else
        {
            return "jdbc:oracle:thin:@" + host + ":" + port + ":" + connectId;
        }
    }
}
