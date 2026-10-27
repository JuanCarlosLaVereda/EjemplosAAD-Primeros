package Ejemplo10;

import java.io.Serializable;

public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellido;
    private String sexo;
    private int edad;
    private String direccion;

    public Persona(String nombre, String apellido, String sexo,
                   int edad, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.edad = edad;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre
                + "\nApellido: " + apellido
                + "\nSexo: " + sexo
                + "\nEdad: " + edad
                + "\nDirección: " + direccion;
    }
}

