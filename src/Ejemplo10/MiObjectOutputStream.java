package Ejemplo10;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {

    public MiObjectOutputStream(OutputStream salida)
            throws IOException {
        super(salida);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // No escribimos otra cabecera al añadir objetos.
    }
}
