# Kessoku

## **Una biblioteca Java para interactuar con bases de datos**

Kessoku es una biblioteca Java que proporciona una forma sencilla de interactuar con bases de datos. Permite realizar diversas operaciones, como seleccionar, insertar, copiar, eliminar, eliminar, etc. De los registros de una tabla.

## **Uso**

1. Cree una instancia de la clase `Kessoku` proporcionando las credenciales de la base de datos:

```java
Kessoku kessoku = new Kessoku("users", "password", "database_name");
```

1. Utilice los métodos de `Kessoku` para interactuar con la base de datos:
- Select
    - `selectAllData(String table, int limit, String... columnNames)`: Recupera un conjunto limitado de datos de la tabla especificada y las columnas especificadas. Toma los siguientes parámetros:
        - `table`: el nombre de la tabla de la cual se recuperarán los datos.
        - `limit`: el número máximo de filas a recuperar.
        - `columnNames`: los nombres de las columnas a recuperar (opcional). Devuelve un arreglo de mapas que contienen los datos recuperados. Puede lanzar una `SQLException` si ocurre un error al acceder a los datos.
    - `selectAllData(String table, String condition, int limit, String... columnNames)`: Selecciona todos los datos de la tabla especificada basado en una condición dada y límite, devolviendo los resultados como un arreglo de mapas. Toma los siguientes parámetros:
        - `table`: el nombre de la tabla de la cual se seleccionarán los datos.
        - `condition`: la condición para filtrar los datos (por ejemplo, "id > 10").
        - `limit`: el número máximo de filas a recuperar (0 para sin límite).
        - `columnNames`: los nombres de las columnas a seleccionar (si no se proporcionan, se seleccionarán todas las columnas). Devuelve un arreglo de mapas, donde cada mapa representa una fila del conjunto de resultados y mapea los nombres de las columnas a sus valores correspondientes. Puede lanzar una `SQLException` si ocurre un error de acceso a la base de datos o si la consulta es rechazada.
    - `selectData(String table, String condition, String... columnNames)`: Selecciona datos de la tabla especificada basado en una condición dada, devolviendo la primera fila del conjunto de resultados como un mapa. Toma los siguientes parámetros:
        - `table`: el nombre de la tabla de la cual se seleccionarán los datos.
        - `condition`: la condición para filtrar los datos (por ejemplo, "id = 10"), o null para seleccionar todas las filas.
        - `columnNames`: los nombres de las columnas a seleccionar (si no se proporcionan, se seleccionarán todas las columnas). Devuelve un mapa que representa la primera fila del conjunto de resultados, mapeando los nombres de las columnas a sus valores correspondientes. Puede lanzar una `SQLException` si ocurre un error de acceso a la base de datos o si la consulta es rechazada.
- Insert
    - `insertInto(String table, String[] columns, T[]... valuesList)`: Inserta datos en una tabla especificada con nombres de columnas proporcionados. Toma los siguientes parámetros:
        - `table`: el nombre de la tabla en la que se insertarán los datos.
        - `columns`: los nombres de las columnas en las que se insertarán los datos.
        - `valuesList`: una matriz de valores a insertar, donde cada fila corresponde a los valores de una fila.
    - `insertIntoSimple(String table, T[]... valuesList)`: Inserta datos en una tabla sin especificar columnas. Toma los siguientes parámetros:
        - `table`: el nombre de la tabla en la que se insertarán los datos.
        - `valuesList`: una lista de arreglos de valores a insertar en las columnas correspondientes.
    - `copyTable(String sourceTable, String destinationTable)`: Copia todos los datos de una tabla a otra. Toma los siguientes parámetros:
        - `sourceTable`: el nombre de la tabla de origen.
        - `destinationTable`: el nombre de la tabla de destino.
    - `copyTable(String sourceTable, String destinationTable, String... columns)`: Copia datos específicos de una tabla a otra. Toma los siguientes parámetros:
        - `sourceTable`: el nombre de la tabla de origen.
        - `destinationTable`: el nombre de la tabla de destino.
        - `columns`: los nombres de las columnas que se copiarán.
- Delete
    - `deleteRegister(String table, String condition)`: Elimina un registro de una tabla basado en ciertas condiciones. Toma los siguientes parámetros:
        - `table`: el nombre de la tabla de la que se eliminará el registro.
        - `condition`: condición para no eliminar toda la tabla.
    - `deleteAllRegister(String table)`: Elimina todos los registros de una tabla. Toma el siguiente parámetro:
        - `table`: el nombre de la tabla de la que se eliminarán todos los registros.
- Update
    - **`update(String table, String[] columnNames, Object[] values)`:**
        - Descripción: Actualiza los datos en una tabla especificada con los valores proporcionados para las columnas respectivas.
        - Parámetros:
            - **`table`** (String): El nombre de la tabla en la que se actualizarán los datos.
            - **`columnNames`** (String[]): Los nombres de las columnas que se actualizarán.
            - **`values`** (Object[]): Los valores que se utilizarán para actualizar las columnas respectivas.
        - Retorno: Void.
    - **`update(String table, String[] columnNames, Object[] values, String condition)`:**
        - Descripción: Actualiza los datos en una tabla especificada con los valores proporcionados para las columnas respectivas, pero solo para las filas que cumplen con la condición especificada.
        - Parámetros:
            - **`table`** (String): El nombre de la tabla en la que se actualizarán los datos.
            - **`columnNames`** (String[]): Los nombres de las columnas que se actualizarán.
            - **`values`** (Object[]): Los valores que se utilizarán para actualizar las columnas respectivas.
            - **`condition`** (String): La condición que debe cumplirse para la actualización.
        - Retorno: Void.
    - **`updateSubConsult(String table, String columnName, String condition, String tableSubConsult, int limit, String... columnNamesSubConsult)`:**
        - Descripción: Actualiza una columna con los resultados de una subconsulta limitada.
        - Parámetros:
            - **`table`** (String): La tabla en la que se actualizará la columna.
            - **`columnName`** (String): El nombre de la columna que se actualizará.
            - **`condition`** (String): La condición que debe cumplirse para la actualización.
            - **`tableSubConsult`** (String): La tabla de la subconsulta.
            - **`limit`** (int): El límite de resultados para la subconsulta.
            - **`columnNamesSubConsult`** (String...): Los nombres de las columnas en la subconsulta.
        - Retorno: Void.

## **Ejemplo**

```java
Kessoku kessoku = new Kessoku("users", "password", "database_name");

try {
    System.out.println(Arrays.toString(kessoku.selectAllData("alumno", 20, "nombre")));
    System.out.println(kessoku.selectData("alumno", "boleta = 2023090559", "nombre", "boleta"));
    System.out.println(kessoku.selectData("alumno", "boleta = 2023090559"));

    kessoku.insertInto("alumno",
			    new String[]{"nombre", "boleta"},
          new Object[]{"Angel Maclovio Morales", 2023090559});

    kessoku.copyTable("alumno", "alumno_copia");
    kessoku.deleteRegister("alumno", "boleta = 2023090559");
    kessoku.deleteAllRegister("alumno");
} catch (Exception e) {
    System.out.println(e.getMessage());
}

```

Este ejemplo muestra cómo utilizar los métodos de `Kessoku` para seleccionar datos, insertar un nuevo registro, copiar una tabla, eliminar un registro específico y eliminar todos los registros de una tabla.

A continuación se mostrara los valores de retorno de los métodos que se imprimieron en pantalla. Principalmente se retorna.

```
/* 
	Retorno de:
	System.out.println(Arrays.toString(kessoku.selectAllData("alumno", 20, "nombre")));
*/

[
	{Nombre=CHAIX PADILLA LEONARDO},
	{Nombre=RAMOS FLORES CHRISTOPHER ALEXANDER},
	{Nombre=HUANG XIWEI},
	{Nombre=CARBAJAL CHIMAL ISAAC},
	{Nombre=CAYETANO VERDUZCO ALEJANDRO},
	...
]

/* 
	Retorno de:
  System.out.println(kessoku.selectData("alumno", "boleta = 2023090559", "nombre", "boleta"));
*/
{
	Nombre=MACLOVIO MORALES ANGEL,
	Boleta=2023090559
}

/* 
	Retorno de:
  System.out.println(kessoku.selectData("alumno", "boleta = 2023090559"));
*/
{
	Nombre="ANGEL",
	Apellido Paterno="MACLOVIO",
	Apellido Materno="MORALES ",
	Especialidad_FK=1,
	Boleta=2023090559
}
```

## **Requisitos**

- Java 8 o superior
- Controlador JDBC para la base de datos correspondiente