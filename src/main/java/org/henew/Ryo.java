package org.henew;

public interface Ryo extends Starry
{

    /**
     * Elimina un registro de una tabla basado en ciertas condiciones.
     * Porque a veces necesitas hacer limpieza. 🧹
     *
     * @param table     el nombre de la tabla de la que se eliminará el registro.
     * @param condition condición para no eliminar toda la tabla
     */
    default void deleteRegister( String table, String condition )
    {
        // Preparando la query antes de ejecutarla
        String preQuery = "DELETE FROM " + table + " WHERE " + condition;

        // Ejecuta el query
        queryUpdate( preQuery );
    }

    /**
     * Elimina todos los registros de una tabla. Porque a veces necesitas un borrón y cuenta nueva. 🚮
     *
     * @param table el nombre de la tabla de la que se eliminarán todos los registros.
     */
    default void deleteAllRegister( String table )
    {
        // Preparando la query antes de ejecutarla
        String preQuery = "DELETE FROM " + table;

        // Ejecuta el query
        queryUpdate( preQuery );
    }

}
