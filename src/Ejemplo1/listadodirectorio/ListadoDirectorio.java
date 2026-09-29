package Ejemplo1.listadodirectorio;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ListadoDirectorio {
  
    public static void main(String[] args) {
        String ruta=".";
        if(args.length>=1) ruta=args[0];
        File fich=new File(ruta);

        //Ejercicio 2
        String rutaDirectorio = System.getProperty("user.home") + "/Ejemplo1Ejercicio2/Nivel1/Nivel2/";
        File file = new File(rutaDirectorio);
        try {
            if (file.exists()){
                throw new Exception("La ruta de los directorios ya existe");
            }
            file.mkdirs();
            File file2 = new File(rutaDirectorio + "Mifichero.txt");
            try {
                if (file2.exists()){
                    throw new Exception("El archivo ya existe.");
                }
                file2.createNewFile();
                System.out.println("Fichero creado en la ruta: " + file2.getPath());
            } catch (IOException e) {
                System.err.println("Error de entrada/salida.");
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }

        //Ejercicio 1
        if(!fich.exists()) {
            System.out.println("No existe el fichero o directorio ("+ruta+").");
        }
        else {
            if(fich.isFile()) {
                System.out.println(ruta+" es un fichero.");
                mostrarContenido(fich);
            }
            else {
                System.out.println(ruta+" es un directorio. Contenidos: ");
                File[] ficheros=fich.listFiles(); // Ojo, ficheros o directorios
                for(File f : ficheros) {
                    mostrarContenido(f);
                }
            }
        }
    }

    public static void mostrarContenido(File fichero){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
        System.out.println();
        String textoDescr=fichero.isDirectory() ? "/" : fichero.isFile() ? "_" : "?";
        String permisos = "";
        if (fichero.canRead()){
            permisos += "r";
        } else {
            permisos += "_";
        }

        if (fichero.canWrite()){
            permisos += "w";
        } else {
            permisos += "_";
        }

        if (fichero.canExecute()){
            permisos += "x";
        } else {
            permisos += "_";
        }
        if (fichero.isFile()){
            System.out.println("("+textoDescr+") "+fichero.getName()
                    + " | " + fichero.length()+ " Bytes | Última modificación "
                    + dateFormat.format(fichero.lastModified()) + " | Permisos: " + permisos);
        } else {
            System.out.println("("+textoDescr+") "+fichero.getName()
                    + " | Última modificación "
                    + dateFormat.format(fichero.lastModified()) + " | Permisos: " + permisos);
        }
    }
}
