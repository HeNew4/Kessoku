package org.henew.queryBuilder;

/**
 * La clase Concatenate proporciona métodos para concatenar elementos en diferentes formatos,
 * como una lista simple, una lista entre paréntesis y una lista de igualdades.
 */
public class Concatenate
{

    /**
     * Concatena una serie de elementos en una lista simple separada por comas.
     *
     * @param elements los elementos a concatenar
     * @return una cadena que representa la concatenación de los elementos
     */
    protected String simple( String... elements )
    {
        StringBuilder query = new StringBuilder();

        for ( String element : elements )
        {
            query.append( element );
            query.append( ", " );
        }

        query = new StringBuilder( query.substring( 0, query.length() - 2 ) );

        return String.valueOf( query );
    }

    /**
     * Concatena una serie de elementos entre paréntesis separados por comas.
     *
     * @param elements los elementos a concatenar
     * @return una cadena que representa la concatenación de los elementos entre paréntesis
     */
    protected String brackets( String... elements )
    {
        StringBuilder query = new StringBuilder();

        query.append( "(" );

        for ( String element : elements )
        {
            query.append( element );
            query.append( ", " );
        }

        query = new StringBuilder( query.substring( 0, query.length() - 2 ) );

        query.append( ")" );

        return String.valueOf( query );
    }

    /**
     * Concatena una serie de matrices de elementos, donde cada matriz está entre paréntesis
     * y las matrices están separadas por comas.
     *
     * @param elements las matrices de elementos a concatenar
     * @return una cadena que representa la concatenación de las matrices de elementos entre paréntesis
     */
    protected String brackets( String[]... elements )
    {
        StringBuilder query = new StringBuilder();

        for ( String[] element : elements )
        {
            query.append( "(" );

            for ( String value : element )
            {
                query.append( value );
                query.append( ", " );
            }

            query = new StringBuilder( query.substring( 0, query.length() - 2 ) );

            query.append( "), " );
        }

        query = new StringBuilder( query.substring( 0, query.length() - 2 ) );

        return String.valueOf( query );
    }

    /**
     * Concatena una serie de columnas y sus respectivos valores en una lista de igualdades.
     *
     * @param columns las columnas
     * @param values  los valores correspondientes a las columnas
     * @param <T>     el tipo de los valores
     * @return una cadena que representa la concatenación de las igualdades entre columnas y valores
     */
    protected <T> String equal( String[] columns, T[] values )
    {
        StringBuilder query = new StringBuilder();

        for ( int i = 0; i < columns.length; i++ )
        {
            query.append( columns[ i ] );
            query.append( " = " );
            query.append( values[ i ] );
            query.append( ", " );
        }

        query = new StringBuilder( query.substring( 0, query.length() - 2 ) );

        return String.valueOf( query );
    }

}
