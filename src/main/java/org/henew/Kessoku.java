package org.henew;

import java.sql.*;

/**
 * ¡Bienvenido a Kessoku, el mundo de las conexiones de bases de datos! 💾✨
 * Aquí encontrarás una clase para realizar consultas y obtener datos de una base de datos.
 */
public class Kessoku implements Hitori
{

    private final String user; // El valiente aventurero que se aventura a conectar: el usuario. 👤
    private final String password; // La clave secreta para abrir la puerta de la base de datos. 🔑🚪
    private final String database; // El mundo misterioso que exploraremos: ¡la base de datos! 🌍🔍

    /**
     * Constructor de Kessoku. ¡Prepara tus suministros para la aventura!
     *
     * @param user     El usuario que se aventura a conectarse.
     * @param password La clave secreta para abrir la puerta de la base de datos.
     * @param database El mundo misterioso que exploraremos.
     */
    public Kessoku( String user, String password, String database )
    {
        this.user = user;
        this.password = password;
        this.database = database;
    }

    /**
     * ¡Conecta tu brújula y tu linterna para iniciar la aventura de la conexión!
     *
     * @return La conexión a la base de datos. ¡Prepárate para explorar!
     * @throws SQLException Si algo sale mal durante la conexión, prepárate para esquivar errores SQL.
     */
    public Connection connect() throws SQLException
    {
        return DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/" + this.database,
                this.user,
                this.password
        );
    }

    /**
     * Realiza una consulta a la base de datos. ¡Peligro, peligro!
     *
     * @param query La consulta que quieres hacer. ¡Cuidado con los dragones SQL!
     * @return El tesoro que encontraste en la base de datos: el resultado de la consulta. 💰💎
     */
    public ResultSet queryExecute( String query )
    {
        Statement statement;
        ResultSet resultSet = null;

        query = query.replaceAll( "['\"\\\\]", "\\\\$0" );

        try ( PreparedStatement stmt = connect().prepareStatement( query ) )
        {
            resultSet = stmt.executeQuery();
            resultSet.close();
        }
        catch ( SQLException ex )
        {
            // ¡Oh no! Encontramos un monstruo SQL. ¡Prepárate para la batalla!
            System.out.println( "SQLException: " + ex.getMessage() );
            System.out.println( "SQLState: " + ex.getSQLState() );
            System.out.println( "VendorError: " + ex.getErrorCode() );
        }

        return resultSet;
    }

    public void queryUpdate( String query )
    {
        Statement statement;

        query = query.replaceAll( "['\"\\\\]", "\\\\$0" );

        try
        {
            statement = connect().createStatement();
            statement.executeUpdate( query );
        }
        catch ( SQLException ex )
        {
            // ¡Oh no! Encontramos un monstruo SQL. ¡Prepárate para la batalla!
            System.out.println( "SQLException: " + ex.getMessage() );
            System.out.println( "SQLState: " + ex.getSQLState() );
            System.out.println( "VendorError: " + ex.getErrorCode() );
        }

    }

}
