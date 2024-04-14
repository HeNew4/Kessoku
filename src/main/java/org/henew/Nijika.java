package org.henew;

import org.henew.queryBuilder.Update;

public interface Nijika extends Starry
{
    Update updateBuilder = new Update();

    /**
     * Actualiza los datos en una tabla especificada.
     * ¡Porque a veces necesitas refrescar las cosas! 🔄
     *
     * @param table       el nombre de la tabla en la que se actualizarán los datos.
     * @param columnNames los nombres de las columnas que se actualizarán.
     * @param values      los valores que se utilizarán para actualizar las columnas respectivas.
     */
    default void update( String table, String[] columnNames, Object[] values )
    {
        String preQuery = updateBuilder.update( table, columnNames, values );

        queryUpdate( preQuery );

    }

    /**
     * Actualiza los datos en una tabla especificada con una condición.
     * Porque a veces necesitas ser selectivo sobre lo que actualizas. 😉
     *
     * @param table       el nombre de la tabla en la que se actualizarán los datos.
     * @param columnNames los nombres de las columnas que se actualizarán.
     * @param values      los valores que se utilizarán para actualizar las columnas respectivas.
     * @param condition   la condición que debe cumplirse para la actualización.
     */
    default void update( String table, String[] columnNames, Object[] values, String condition )
    {
        String preQuery = updateBuilder.update( table, columnNames, values, condition );

        queryUpdate( preQuery );

    }

    /**
     * Actualiza una columna con los resultados de una subconsulta limitada.
     * ¡Porque a veces necesitas un toque de magia SQL! 🎩✨
     *
     * @param table                 la tabla en la que se actualizará la columna.
     * @param columnName            el nombre de la columna que se actualizará.
     * @param condition             la condición que debe cumplirse para la actualización.
     * @param tableSubConsult       la tabla de la subconsulta.
     * @param limit                 el límite de resultados para la subconsulta.
     * @param columnNamesSubConsult los nombres de las columnas en la subconsulta.
     */
    default void updateSubConsult( String table, String columnName, String condition, String tableSubConsult, int limit, String... columnNamesSubConsult )
    {
        String preQuery = updateBuilder.updateSubConsult( table, columnName, condition, tableSubConsult, limit, columnNamesSubConsult );

        queryUpdate( preQuery );

    }
}
