# Guía de Uso para TareaFTP

## Descripción
TareaFTP es una aplicación Java diseñada para facilitar la interacción con servidores FTP desde la línea de comandos. Con esta herramienta, puedes explorar, descargar y administrar archivos en un servidor FTP de manera sencilla y eficiente.

## Configuración Inicial
Antes de comenzar a utilizar TareaFTP, asegúrate de realizar la siguiente configuración:

1. Abre el archivo `Main.java` en un editor de texto.
2. Modifica la variable `SERVIDORURL` con la dirección del servidor FTP al que deseas conectarte.
3. Asegúrate de tener las credenciales de acceso al servidor FTP disponibles para proporcionarlas durante la ejecución de la aplicación.

## Uso del Proyecto
Una vez que hayas configurado el archivo `Main.java`, sigue estos pasos para utilizar TareaFTP:

1. Abre una terminal o línea de comandos en tu sistema.
2. Navega al directorio que contiene el archivo `Main.java`.
3. Compila el archivo `Main.java` ejecutando el siguiente comando:
   ```
   javac Main.java
   ```
4. Una vez compilado, ejecuta la aplicación con el siguiente comando:
   ```
   java Main
   ```
5. Aparecerá un menú con las opciones disponibles. Utiliza los siguientes comandos para interactuar con el servidor FTP:
   - `ls`: Lista el contenido del directorio actual en el servidor FTP.
   - `cd <ruta>`: Cambia al directorio especificado en el servidor FTP.
   - `get <archivo>`: Descarga el archivo especificado desde el servidor FTP.
   - `exit`: Cierra la aplicación y termina la conexión con el servidor FTP.

## Ejemplo de Uso
Aquí tienes un ejemplo de cómo utilizar TareaFTP:

1. Después de ejecutar la aplicación, utiliza el comando `ls` para listar el contenido del directorio actual en el servidor FTP.
2. Utiliza el comando `cd <ruta>` para cambiar al directorio deseado en el servidor FTP.
3. Una vez en el directorio deseado, utiliza el comando `get <archivo>` para descargar un archivo específico desde el servidor FTP.
4. Repite los pasos anteriores según sea necesario para explorar y administrar archivos en el servidor FTP.
5. Cuando hayas terminado, utiliza el comando `exit` para cerrar la aplicación y terminar la conexión con el servidor FTP.
