package Ejemplo10;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {

    private static final String FICHERO = "personas.dat";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE PERSONAS ---");
            System.out.println("1. Guardar persona");
            System.out.println("2. Mostrar personas");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    guardarPersona(sc);
                    break;

                case 2:
                    mostrarPersonas();
                    break;

                case 3:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 3);

        sc.close();
    }

    private static void guardarPersona(Scanner sc) {

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Sexo: ");
        String sexo = sc.nextLine();

        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        Persona persona = new Persona(
                nombre, apellido, sexo, edad, direccion
        );

        File fichero = new File(FICHERO);

        try (FileOutputStream fos =
                     new FileOutputStream(fichero, true);
             ObjectOutputStream oos =
                     fichero.length() == 0
                             ? new ObjectOutputStream(fos)
                             : new MiObjectOutputStream(fos)) {

            oos.writeObject(persona);

            System.out.println("Persona guardada correctamente.");

        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    private static void mostrarPersonas() {

        File fichero = new File(FICHERO);

        if (!fichero.exists() || fichero.length() == 0) {
            System.out.println("No hay personas guardadas.");
            return;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(fichero))) {

            while (true) {
                // readObject devuelve Object, por eso convertimos a Persona.
                Persona persona = (Persona) ois.readObject();

                System.out.println("\n--------------------");
                System.out.println(persona);
            }

        } catch (EOFException e) {
            // Fin normal del fichero.
            System.out.println("\nFin de la lista de personas.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }
}

