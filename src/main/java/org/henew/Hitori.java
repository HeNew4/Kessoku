package org.henew;

import org.henew.queryBuilder.Select;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public interface Hitori extends Starry
{

    Select selectBuilder = new Select();

    /**
     * Retrieves a limited set of data from the specified table and columns.
     *
     * @param table       the name of the table to retrieve data from
     * @param limit       the maximum number of rows to retrieve
     * @param columnNames the names of the columns to retrieve (optional)
     * @return an array of maps containing the retrieved data
     * @throws SQLException if an error occurs while accessing the data
     */
    default Map<String, Object>[] selectAllData( String table, int limit, String... columnNames ) throws SQLException
    {
        // Preparando la query antes de ejecutarla
        String preQuery = selectBuilder.select( table, limit, columnNames ).build();

        return executeQueryAndProcessResults( preQuery );
    }

    /**
     * Selects all data from a specified table based on a given condition and limit, returning the results as an array of maps.
     *
     * @param table       the name of the table to select data from
     * @param condition   the condition to filter the data (e.g., "id > 10")
     * @param limit       the maximum number of rows to retrieve (0 for no limit)
     * @param columnNames the names of the columns to select (if none are provided, all columns will be selected)
     * @return an array of maps, where each map represents a row of the result set and maps column names to their corresponding values
     * @throws SQLException if a database access error occurs or the query is rejected
     */
    default Map<String, Object>[] selectAllData( String table, String condition, int limit,
                                                 String... columnNames ) throws SQLException
    {
        // Preparing the query before executing
        String preQuery = selectBuilder.select( table, limit, columnNames ).where( condition ).build();

        return executeQueryAndProcessResults( preQuery );
    }

    /**
     * Selects data from a specified table based on a given condition, returning the first row of the result set as a map.
     *
     * @param table       the name of the table to select data from
     * @param condition   the condition to filter the data (e.g., "id = 10"), or null to select all rows
     * @param columnNames the names of the columns to select (if none are provided, all columns will be selected)
     * @return a map representing the first row of the result set, mapping column names to their corresponding values
     * @throws SQLException if a database access error occurs or the query is rejected
     */
    default Map<String, Object> selectData( String table, String condition, String... columnNames ) throws SQLException
    {
        String preQuery = selectBuilder.select( table, columnNames ).where( condition ).build();

        if ( columnNames == null || columnNames.length == 0 )
        {
            System.out.println( Arrays.toString( executeQueryAndProcessResults( preQuery ) ) );
        }

        return executeQueryAndProcessResults( preQuery )[ 0 ];
    }

    /**
     * Executes a given SQL query and processes the results, returning them as an array of maps.
     *
     * @param query the SQL query to execute
     * @return an array of maps, where each map represents a row of the result set and maps column names to their corresponding values
     * @throws SQLException if a database access error occurs or the query is rejected
     */
    default Map<String, Object>[] executeQueryAndProcessResults( String query ) throws SQLException
    {
        ArrayList<Map<String, Object>> data = new ArrayList<>();

        // Executing the query
        ResultSet resultSet = queryExecute( query );

        // Column information
        ResultSetMetaData metaData    = resultSet.getMetaData();
        int               numColumnas = metaData.getColumnCount();

        // Iterating through the results
        while ( resultSet.next() )
        {
            // Creating a temporary HashMap to store the data
            HashMap<String, Object> temporalSave = new HashMap<>();
            // Printing the values of each row
            for ( int i = 1; i <= numColumnas; i++ )
            {
                // Accessing the values of each column
                Object valor = resultSet.getObject( i );
                temporalSave.put( metaData.getColumnName( i ), valor );
            }

            data.add( temporalSave );
        }

        @SuppressWarnings( "unchecked" ) Map<String, Object>[] processedInformation = new HashMap[ data.size() ];
        processedInformation = data.toArray( processedInformation );

        return processedInformation;
    }

    /**
     * Realiza un join interno entre dos tablas basado en una condición dada y devuelve los resultados como un arreglo de mapas.
     *
     * @param table1      la primera tabla para realizar el join
     * @param table2      la segunda tabla para realizar el join
     * @param condition   la condición para el join (por ejemplo, "table1.id = table2.table1_id")
     * @param columnNames los nombres de las columnas a seleccionar (si no se proporcionan, se seleccionarán todas las columnas)
     * @return un arreglo de mapas, donde cada mapa representa una fila del conjunto de resultados y mapea los nombres de columna a sus valores correspondientes
     * @throws SQLException si ocurre un error de acceso a la base de datos o la consulta es rechazada
     */
    default Map<String, Object>[] joinTables( String table1, String table2, String condition,
                                              String... columnNames ) throws SQLException
    {
        // Preparar la consulta antes de ejecutarla
        String preQuery = selectBuilder.select( table1, columnNames ).join( table2, condition ).build();

        return executeQueryAndProcessResults( preQuery );
    }

    /**
     * Realiza un join interno entre la tabla 'registra' y otra tabla basado en una condición dada, y devuelve los resultados como un arreglo de mapas.
     *
     * @param otherTable  la otra tabla para realizar el join con la tabla 'registra'
     * @param condition   la condición para el join (por ejemplo, "registra.id = otherTable.registra_id")
     * @param columnNames los nombres de las columnas a seleccionar (si no se proporcionan, se seleccionarán todas las columnas)
     * @return un arreglo de mapas, donde cada mapa representa una fila del conjunto de resultados y mapea los nombres de columna a sus valores correspondientes
     * @throws SQLException si ocurre un error de acceso a la base de datos o la consulta es rechazada
     */
    default Map<String, Object>[] joinRegistra(String otherTable, String condition, String... columnNames) throws SQLException {
        // Preparar la consulta antes de ejecutarla
        String preQuery = selectBuilder.select("registra", columnNames).join(otherTable, condition).build();

        return executeQueryAndProcessResults(preQuery);
    }

}
