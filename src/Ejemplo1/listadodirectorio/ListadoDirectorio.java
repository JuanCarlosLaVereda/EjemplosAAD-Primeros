package Ejemplo1.listadodirectorio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
            String texto = "Ser o no ser, esa es la cuestión.\n" +
                    "¿Qué es más noble para el alma?\n" +
                    "¿Soportar los golpes y flechas de la desdicha,\n" +
                    "o tomar las armas contra un mar de problemas\n" +
                    "y, enfrentándolos, acabar con ellos?\n" +
                    "Morir, dormir, nada más. Y con un sueño\n" +
                    "poner fin al tormento del corazón\n" +
                    "y a los mil dolores que son el destino de la carne.\n" +
                    "Es una conclusión que hay que desear.\n" +
                    "Morir, dormir. Dormir, tal vez soñar;\n" +
                    "sí, ahí está el obstáculo, porque en ese sueño de muerte,\n" +
                    "cuando hayamos dejado atrás el ruido del mundo,\n" +
                    "¿qué sueños pueden venir? Esa duda\n" +
                    "es lo que nos detiene y da larga vida\n" +
                    "a las desgracias. Porque, ¿quién soportaría\n" +
                    "los latigazos y burlas del tiempo,\n" +
                    "la opresión del poderoso, las injurias del soberbio,\n" +
                    "las punzadas del amor despreciado,\n" +
                    "las demoras de la justicia, la arrogancia del poder\n" +
                    "y el desprecio que el mérito paciente recibe del indigno,\n" +
                    "cuando uno mismo podría encontrar reposo\n" +
                    "con un simple puñal?";
            try {
                if (file2.exists()){
                    throw new Exception("El archivo ya existe.");
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));
                bufferedWriter.write(texto);
                bufferedWriter.close();
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
