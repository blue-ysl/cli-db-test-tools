public enum DbmsType
{
    ORACLE("Oracle"),
    MYSQL("MySQL"),
    MARIADB("MariaDB"),
    POSTGRES("Postgres");

    private final String name;

    DbmsType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
