package db;

public class MariaDbSession extends DbSession
{
    private final boolean useCompression;

    public MariaDbSession(String host, int port, String databaseName, String username, String password, boolean useCompression)
    {
        super(host, port, databaseName, username, password);
        this.useCompression = useCompression;
    }

    public boolean isUseCompression()
    {
        return useCompression;
    }

    @Override
    public String getConnectionUrl()
    {
        return "jdbc:mariadb://" + host + ":" + port + "/" + databaseName + "?useCompression=" + useCompression;
    }
}
