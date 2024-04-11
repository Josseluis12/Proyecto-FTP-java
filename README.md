## Guía de Uso: TareaFTP

### Descripción
TareaFTP es una aplicación Java que te permite interactuar con un servidor FTP desde la línea de comandos. Con esta aplicación, puedes listar el contenido de un directorio, cambiar de directorio, descargar archivos y más.

### Requisitos previos
Antes de utilizar esta aplicación, asegúrate de tener instalado Java en tu sistema. También necesitarás tener acceso a un servidor FTP al que puedas conectarte.

### Configuración
Antes de ejecutar la aplicación, asegúrate de editar el archivo `Main.java` y proporcionar la dirección del servidor FTP al que deseas conectarte. Además, asegúrate de tener las credenciales de acceso al servidor FTP.

### Uso
Para ejecutar la aplicación, sigue estos pasos:

1. Abre una terminal o línea de comandos.
2. Navega al directorio que contiene el archivo `Main.java`.
3. Compila el archivo `Main.java` ejecutando el siguiente comando:
   ```
   javac Main.java
   ```
4. Una vez compilado, ejecuta la aplicación con el siguiente comando:
   ```
   java Main
   ```
5. Sigue las instrucciones en pantalla para interactuar con el servidor FTP. Puedes utilizar comandos como `ls` para listar el contenido del directorio actual, `cd` para cambiar de directorio, `get` para descargar archivos, y `exit` para salir de la aplicación.
