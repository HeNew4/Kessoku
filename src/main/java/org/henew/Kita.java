package org.henew;

import org.henew.queryBuilder.Insert;

public interface Kita extends Starry
{
    Insert insertBuilder = new Insert();

    /**
     * Inserta datos en una tabla especificada con nombres de columnas proporcionados.
     * Porque a veces necesitas llenar las cosas con datos... como si fueran una hoja en blanco. 📝
     *
     * @param table      el nombre de la tabla en la que se insertarán los datos.
     * @param columns    los nombres de las columnas en las que se insertarán los datos.
     * @param valuesList una matriz de valores a insertar, donde cada fila corresponde a los valores de una fila.
     */
    default void insertInto( String table, String[] columns, String[]... valuesList )
    {
        String preQuery = insertBuilder.insert( table, columns, valuesList );

        queryUpdate( String.valueOf( preQuery ) );
    }

    /**
     * Inserta datos en una tabla sin especificar columnas.
     * Porque a veces la improvisación es la clave. 🎭
     *
     * @param table      el nombre de la tabla en la que se insertarán los datos.
     * @param valuesList una lista de arreglos de valores a insertar en las columnas correspondientes.
     */
    default void insertIntoSimple( String table, String[]... valuesList )
    {
        String preQuery = insertBuilder.insertSimple( table, valuesList );

        queryUpdate( preQuery );
    }

    /**
     * Copia todos los datos de una tabla a otra.
     * Porque a veces necesitas duplicar tus éxitos. 📝✨
     *
     * @param sourceTable      el nombre de la tabla de origen.
     * @param destinationTable el nombre de la tabla de destino.
     */
    default void copyTable( String sourceTable, String destinationTable )
    {
        queryUpdate( insertBuilder.insertCopy( sourceTable, destinationTable ) );
    }

    /**
     * Copia datos específicos de una tabla a otra.
     * Porque a veces necesitas seleccionar solo lo mejor. 🏆
     *
     * @param sourceTable      el nombre de la tabla de origen.
     * @param destinationTable el nombre de la tabla de destino.
     * @param columns          los nombres de las columnas que se copiarán.
     */
    default void copyTable( String sourceTable, String destinationTable, String... columns )
    {
        String preQuery = insertBuilder.insertCopy( sourceTable, destinationTable, columns );

        queryUpdate( preQuery );
    }
}
