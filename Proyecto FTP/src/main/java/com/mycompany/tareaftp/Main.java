package com.mycompany.tareaftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import java.io.IOException;
import java.io.FileOutputStream;
import java.net.SocketException;
import java.util.Scanner;

public class Main {

  // Aquí creamos nuestro cliente FTP. ¡Listo para la acción!
  private static FTPClient clienteFTP;
  // Este será nuestro flujo para guardar archivos que descarguemos.
  private static FileOutputStream ficheroObtenido;
  // Esta es la dirección de nuestro servidor FTP. ¡Cuidado, aquí viene lo bueno!
  private static final String SERVIDORURL = "127.0.0.1";
  // Aquí guardamos los nombres de las carpetas que tenemos disponibles. ¡Vamos a explorar!
  private static String[] nombreCarpeta;
  // Llevamos la cuenta de dónde estamos en el servidor FTP.
  private static String rutaActual = "/";

  public static void main(String[] args) {
    try {
      int reply;
      // Creamos un nuevo cliente FTP. ¡Aquí va la acción!
      clienteFTP = new FTPClient();
      // Pedimos al usuario sus credenciales. ¡Vamos a conectarnos!
      Scanner scanner = new Scanner(System.in);
      System.out.println("¡Hola! ¿Cuál es tu usuario de FTP?");
      String usuario = scanner.nextLine();
      System.out.println("Y, ¿cuál es tu contraseña de FTP?");
      String password = scanner.nextLine();
      // Nos conectamos al servidor FTP. ¡Estamos en camino!
      clienteFTP.connect(SERVIDORURL);
      // Verificamos que la conexión sea un éxito.
      reply = clienteFTP.getReplyCode();
      // Si la conexión es un éxito, ¡sigamos adelante!
      if (FTPReply.isPositiveCompletion(reply)) {
        // Intentamos hacer login con las credenciales que nos dio el usuario.
        if (clienteFTP.login(usuario, password)) {
          mostrarMenu();
          boolean salir = false;
          while (!salir) {
            System.out.print("\n¿Qué deseas hacer hoy?: ");
            String comando = scanner.nextLine();
            // Aquí viene lo interesante: vamos a ver qué acción quiere realizar el usuario.
            switch (comando.split(" ")[0]) {
              case "ls" -> listarContenido();
              case "cd" -> cambiarDirectorio(comando.split(" ")[1]);
              case "get" -> descargarArchivo(comando.split(" ")[1]);
              case "exit" -> salir = true;
              default -> System.out.println("¡Uy, parece que ese comando no lo tengo en mi lista!");
            }
          }
          // Cerramos la conexión con el servidor. ¡Hasta la próxima!
          clienteFTP.disconnect();
          System.out.println("¡Hasta luego! ¡Que tengas un buen día!");
        } else {
          // Si las credenciales son incorrectas, ¡démosle otra oportunidad!
          System.out.println("¡Vaya! Parece que tus credenciales no son las correctas. ¿Quieres intentarlo de nuevo?");
        }
      } else {
        // Algo salió mal con la conexión. ¡Oops!
        clienteFTP.disconnect();
        System.err.println("¡Vaya! Parece que no puedo conectarme al servidor FTP. ¡Qué desastre!");
        System.exit(1);
      }
    } catch (SocketException ex) {
      // Si hay un problema con el socket, ¡aquí lo manejamos!
      System.out.println("¡Ups! Parece que hay un problema con el socket: " + ex.getMessage());
    } catch (IOException ex) {
      // Si hay un problema de entrada/salida, ¡aquí lo manejamos!
      System.out.println("¡Ups! Parece que hay un problema de entrada/salida: " + ex.getMessage());
    }
  }

  // Este método muestra el menú de opciones disponibles. ¡Es hora de elegir!
  private static void mostrarMenu() {
    System.out.println("\n¡Bienvenido al cliente FTP! ¿Qué deseas hacer hoy?");
    System.out.println("ls           - Listar contenido del directorio actual");
    System.out.println("cd <ruta>    - Cambiar de directorio");
    System.out.println("get <archivo>- Descargar archivo");
    System.out.println("exit         - Salir del programa");
  }

  // ¡Hora de explorar! Este método lista el contenido del directorio actual.
  private static void listarContenido() {
    try {
      System.out.println("\n¡Aquí tienes lo que hay en este lugar!");
      nombreCarpeta = clienteFTP.listNames();
      for (String carpeta : nombreCarpeta) {
        System.out.println(carpeta);
      }
    } catch (IOException ex) {
      // Si hay un problema al listar el contenido, ¡aquí lo manejamos!
      System.out.println("¡Vaya! Parece que hubo un problema al listar el contenido: " + ex.getMessage());
    }
  }

  // ¡Vamos de paseo! Este método cambia de directorio.
  private static void cambiarDirectorio(String ruta) {
    try {
      if (ruta.equals("..")) {
        if (rutaActual.equals("/")) {
          // Si estamos en la raíz, ¡no podemos subir más!
          System.out.println("¡Uy! No podemos subir más, ¡estamos en la raíz!");
          return;
        }
        // Cambiamos al directorio padre.
        clienteFTP.changeToParentDirectory();
        // Actualizamos la ruta actual.
        rutaActual = clienteFTP.printWorkingDirectory();
      } else {
        if (!ruta.endsWith("/")) {
          ruta += "/";
        }
        if (!ruta.startsWith("/")) {
          ruta = "/" + ruta;
        }
        if (!clienteFTP.changeWorkingDirectory(rutaActual + ruta)) {
          // Si no podemos cambiar al directorio indicado, ¡aquí lo manejamos!
          System.out.println("¡Uy! No pudimos cambiar al directorio indicado.");
          return;
        }
        rutaActual += ruta;
      }
      System.out.println("¡Listo! ¡Ahora estamos en este lugar: " + rutaActual);
    } catch (IOException ex) {
      // Si hay un problema al cambiar de directorio, ¡aquí lo manejamos!
      System.out.println("¡Uy! Parece que hubo un problema al cambiar de directorio: " + ex.getMessage());
    }
  }

  // ¡Hora de descargar! Este método descarga un archivo desde el servidor FTP.
  private static void descargarArchivo(String nombreArchivo) {
    try {
      ficheroObtenido = new FileOutputStream(nombreArchivo);
      if (clienteFTP.retrieveFile(rutaActual + nombreArchivo, ficheroObtenido)) {
        // Si la descarga fue un éxito, ¡celebremos!
        System.out.println("¡Hurra! ¡El archivo se descargó correctamente!");
      } else {
        // Si no podemos descargar el archivo, ¡aquí lo manejamos!
        System.out.println("¡Vaya! Parece que no pudimos descargar el archivo.");
      }
    } catch (IOException ex) {
      // Si hay un problema al descargar el archivo, ¡aquí lo manejamos!
      System.out.println("¡Vaya! Parece que hubo un problema al descargar el archivo: " + ex.getMessage());
    } finally {
      try {
        if (ficheroObtenido != null) {
          ficheroObtenido.close();
        }
      } catch (IOException ex) {
        // Si hay un problema al cerrar el flujo, ¡aquí lo manejamos!
        System.out.println("¡Vaya! Parece que hubo un problema al cerrar el flujo: " + ex.getMessage());
      }
    }
  }
}
