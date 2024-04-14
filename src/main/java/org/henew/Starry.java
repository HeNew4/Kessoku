package org.henew;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Starry
{


    Connection connect() throws SQLException;

    ResultSet queryExecute( String query );

    void queryUpdate( String query );

}
