package Ejemplo6;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EscribeConNumeroDeLineas {

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Indicar por favor nombre de fichero");
      return;
    }
    String nomFich = args[0];
    String palabraBuscar = args[1];

    try (BufferedReader fbr = new BufferedReader(new FileReader(nomFich))) {
      int i = 0;
      String linea = fbr.readLine();
      List<String> apariciones = new ArrayList<>();
      while (linea != null) {
        System.out.format("[%5d] %s", i++, linea);
        System.out.println();
        if (linea.contains(palabraBuscar)){
          apariciones.add("Linea " + (i-1) + " columna " + (linea.indexOf(palabraBuscar)+1));
        }
        linea = fbr.readLine();
      }
      if (!apariciones.isEmpty()){
        System.out.println(palabraBuscar + " ha aparecido en las siguientes lineas:");
        System.out.println(apariciones);
      } else {
        System.out.println(palabraBuscar + " no ha aparecido en el texto :C");
      }
    } catch (FileNotFoundException e) {
      System.out.println("No existe fichero " + nomFich);
    } catch (IOException e) {
      System.out.println("Error de E/S: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
