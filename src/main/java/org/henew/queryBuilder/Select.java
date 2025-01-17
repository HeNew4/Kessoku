package org.henew.queryBuilder;

public class Select extends Concatenate
{

    private static final String SELECT_PREFIX = "SELECT ";
    private static final String FROM_PREFIX   = " FROM ";
    private static final String LIMIT_PREFIX  = " LIMIT ";
    private static final String WHERE_PREFIX  = " WHERE ";
    private static final String JOIN_PREFIX   = " INNER JOIN ";
    private static final String ON_PREFIX     = " ON ";

    private final Concatenate concatenate;
    private       String      selectQuery;

    public Select()
    {
        this.concatenate = new Concatenate();
    }

    public Select select( String table, String... columnNames )
    {
        if ( columnNames == null || columnNames.length == 0 )
        {
            selectQuery = buildSelectQuery( "*", table, 1 );
        }
        else
        {
            selectQuery = buildSelectQuery( concatenate.simple( columnNames ), table, 1 );
        }

        return this;
    }

    public Select select( String table, int limit )
    {
        selectQuery = buildSelectQuery( "*", table, limit );
        return this;
    }

    public Select select( String table, int limit, String... columnNames )
    {
        selectQuery = buildSelectQuery( concatenate.simple( columnNames ), table, limit );
        return this;
    }

    public Select where( String condition )
    {
        selectQuery += WHERE_PREFIX + condition;
        return this;
    }

    public Select join( String table, String condition )
    {
        selectQuery += JOIN_PREFIX + table + ON_PREFIX + condition;
        return this;
    }

    public String build()
    {
        return selectQuery;
    }

    private String buildSelectQuery( String columns, String table, int limit )
    {
        String query = SELECT_PREFIX + columns + FROM_PREFIX + table;

        if ( limit != 1 )
        {
            return SELECT_PREFIX + columns + FROM_PREFIX + table + LIMIT_PREFIX + limit;
        }

        return query;
    }

}
