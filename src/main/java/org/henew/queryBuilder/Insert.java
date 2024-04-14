package org.henew.queryBuilder;

public class Insert
{
    private static final String INSERT_INTO_PREFIX = "INSERT INTO ";
    private static final String VALUES_PREFIX = " VALUES ";
    private static final String SELECT_PREFIX = " SELECT ";
    private static final String FROM_PREFIX = " FROM ";

    private final Concatenate concatenate;

    public Insert() {
        this.concatenate = new Concatenate();
    }

    @SafeVarargs
    public final <T> String insert( String table, String[] columns, T[]... valuesList ) {
        return buildInsertQuery(table, concatenate.brackets(columns), concatenate.brackets(valuesList));
    }

    @SafeVarargs
    public final <T> String insertSimple( String table, T[]... valuesList ) {
        return buildInsertQuery(table, null, concatenate.brackets(valuesList));
    }

    public String insertCopy(String sourceTable, String destinationTable) {
        return INSERT_INTO_PREFIX + sourceTable + SELECT_PREFIX + "*" + FROM_PREFIX + destinationTable;
    }

    public String insertCopy(String sourceTable, String destinationTable, String... columns) {
        return INSERT_INTO_PREFIX + sourceTable + concatenate.brackets(columns) + SELECT_PREFIX + concatenate.simple(columns) + FROM_PREFIX + destinationTable;
    }

    private String buildInsertQuery(String table, String columns, String values) {
        String query = INSERT_INTO_PREFIX + table;
        if (columns != null) {
            query += columns;
        }
        query += VALUES_PREFIX + values;
        return query;
    }
}
