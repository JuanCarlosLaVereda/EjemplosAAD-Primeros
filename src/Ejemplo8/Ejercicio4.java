package Ejemplo8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio4 {
    public static void main(String[] args) {
        String fichero = "Mifichero.txt";
        File ficheroCopiado = new File("Copia_" + fichero);

        try (BufferedReader br = new BufferedReader(new FileReader(fichero));
             BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroCopiado))){
            String linea = br.readLine();
            List<String> lineas = new ArrayList<>();
            while (linea != null){
                lineas.add(linea);
                linea = br.readLine();
            }

            for (int i = lineas.size()-1; i >= 0; i--) {
                bw.write(lineas.get(i));
                bw.newLine();

            }
            System.out.println("Fichero copiado e invertido con exito.");

        } catch (IOException e){
            e.printStackTrace();
            System.err.println("Error entrada/salida");
        }

    }
}
