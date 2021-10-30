# Challenge de Alkemy: Spring-Boot Disney API

<br>

# Sobre el challenge

<br>

El reto consistió en crear una API utilizando el proyecto de Spring, Spring-Boot, del lenguaje Java.

La API se basa en contener Characters, Shows y Genres (Personajes, Shows y Géneros), con temática de Disney.

Consigna:
https://drive.google.com/file/d/1mXkHaKZRPVTLcydFsrKhJxeNQ2PyMMfC/view

<br>

# Información del proyecto

## Se utilizaron las siguientes herramientas

-   Lenguaje **Java** para el desarrollo
-   **Spring-Boot Data JPA** como ORM para la persistencia
-   Base de datos **relacional** SQL
-   **XAMPP** con **PhpMyAdmin** para la administración de la base de datos
-   **Swagger** para documentar los endpoints
-   Librería **Lombok** para reducir porciones de código
-   IDE **NetBeans** 12.2
-   **Git** para el versionado

<br>

# Como probar el proyecto en su equipo

<br>

Es muy recomendable contar con al menos XAMPP para crear el servidor de base de datos y NetBeans 12.2 para abrir el proyecto.

Es ideal también que tenga conocimiento sobre las líneas del archivo **application.properties**, ya que ahí es donde se realiza la conexión a la base de datos.

<br>

## Pasos a seguir

<br>

-   Descargar el proyecto en su equipo
-   Crear una base de datos con el nombre **bd_disney_api**
-   Modificar el archivo **application.properties** con el usuario y contraseña de algún usuario que tenga en su gestor de base de datos
-   Si está utilizando **NetBeans**, vaya hasta el archivo _DisneyApiApplication.java_, haga click derecho -> run
-   Si no está utilizando ningún IDE o Editor de Texto, sitúese en la carpeta del proyecto, abra una terminal y escriba el siguiente comando:

    `.\mvnw.cmd spring-boot:run`

-   Una vez hecho eso, la base de datos anteriormente creada ya debería contar con las tablas del proyecto (la terminal debe permanecer abierta con el proyecto corriendo para que este se mantenga funcionando)
-   Para probar los endpoints, puede insertar datos manualmente a preferencia, o utilizar el siguiente código SQL para cargar datos rápidamente:

    SQL Query: https://drive.google.com/file/d/1zpJnp6mIOwnjEiXVNj6uG25NtFQyVcLc/view?usp=sharing

<br>

# Aclaraciones

<br>

Las relaciones entre los **registros** (leer bien, entre los REGISTROS), no se pueden crear a través de un POST, solo pueden relacionarse los datos desde el gestor de base de datos, por ejemplo, para crear una relación entre Characters y Shows, debe insertar un registro en la tabla intermedia entre ambos llamada "characters_shows" (esto no hace falta hacer si se utilizó el **SQL Query** dejado más arriba, ya que esa consulta, relaciona también los registros)
