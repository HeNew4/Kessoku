package org.henew.queryBuilder;

public class Update
{

    private static final String UPDATE_PREFIX = "UPDATE ";
    private static final String SET_PREFIX = " SET ";
    private static final String WHERE_PREFIX = " WHERE ";

    private final Concatenate concatenate;

    public Update()
    {
        this.concatenate = new Concatenate();
    }

    public String update( String table, String[] columnNames, Object[] values )
    {
        return buildUpdateQuery( table, columnNames, values, null );
    }

    public String update( String table, String[] columnNames, Object[] values, String condition )
    {
        return buildUpdateQuery( table, columnNames, values, condition );
    }

    public String updateSubConsult( String table, String columnName, String condition, String tableSubConsult,
                                    int limit, String... columnNamesSubConsult )
    {
        String subConsult = "SELECT " + concatenate.simple(
                columnNamesSubConsult ) + " FROM " + tableSubConsult + " LIMIT " + limit;
        return UPDATE_PREFIX + table + SET_PREFIX + columnName + " = (" + subConsult + ")" + WHERE_PREFIX + condition;
    }

    private String buildUpdateQuery( String table, String[] columnNames, Object[] values, String condition )
    {
        StringBuilder query = new StringBuilder(
                UPDATE_PREFIX + table + SET_PREFIX + concatenate.equal( columnNames, values ) );
        if ( condition != null )
        {
            query.append( WHERE_PREFIX + condition );
        }
        return query.toString();
    }

}
