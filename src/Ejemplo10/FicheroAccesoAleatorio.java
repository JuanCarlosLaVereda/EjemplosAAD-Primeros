
package Ejemplo10;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

public class FicheroAccesoAleatorio {

    private final File f;
    private final List<SimpleEntry<String, Integer>> campos;
    private long longReg;
    private long numReg = 0;

    public FicheroAccesoAleatorio(
            String nomFich,
            List<SimpleEntry<String, Integer>> campos)
            throws IOException {

        this.campos = campos;
        this.f = new File(nomFich);
        this.longReg = 0;

        for (SimpleEntry<String, Integer> campo : campos) {
            this.longReg += campo.getValue();
        }

        if (f.exists() && longReg > 0) {
            this.numReg = f.length() / this.longReg;
        }
    }

    public long getNumReg() {
        return numReg;
    }

    public void insertar(Map<String, String> reg)
            throws IOException {
        insertar(reg, this.numReg++);
    }

    public void insertar(Map<String, String> reg, long pos)
            throws IOException {

        try (RandomAccessFile faa =
                     new RandomAccessFile(f, "rws")) {

            faa.seek(pos * this.longReg);

            for (SimpleEntry<String, Integer> campo : this.campos) {

                String nomCampo = campo.getKey();
                int longCampo = campo.getValue();

                String valorCampo = reg.get(nomCampo);

                if (valorCampo == null) {
                    valorCampo = "";
                }

                // Ajustar la longitud del campo
                if (valorCampo.length() > longCampo) {
                    valorCampo = valorCampo.substring(0, longCampo);
                }

                String valorCampoForm =
                        String.format("%1$-" + longCampo + "s",
                                valorCampo);

                byte[] bytes = valorCampoForm.getBytes(
                        java.nio.charset.StandardCharsets.UTF_8);

                // Escribir exactamente el tamaño reservado
                byte[] bytesCampo = new byte[longCampo];
                System.arraycopy(
                        bytes, 0, bytesCampo, 0,
                        Math.min(bytes.length, longCampo));

                faa.write(bytesCampo);
            }
        }
    }

    public static void main(String[] args) {

        List<SimpleEntry<String, Integer>> campos =
                new ArrayList<>();

        campos.add(new SimpleEntry<>("DNI", 9));
        campos.add(new SimpleEntry<>("NOMBRE", 32));
        campos.add(new SimpleEntry<>("CP", 5));

        try {
            FicheroAccesoAleatorio faa =
                    new FicheroAccesoAleatorio(
                            "fic_acceso_aleat.dat", campos);

            Map<String, String> reg = new HashMap<>();

            reg.put("DNI", "56789012B");
            reg.put("NOMBRE", "SAMPER");
            reg.put("CP", "29730");
            faa.insertar(reg);

            reg.clear();
            reg.put("DNI", "89012345E");
            reg.put("NOMBRE", "ROJAS");
            faa.insertar(reg);

            reg.clear();
            reg.put("DNI", "23456789D");
            reg.put("NOMBRE", "DORCE");
            reg.put("CP", "13700");
            faa.insertar(reg);

            reg.clear();
            reg.put("DNI", "78901234X");
            reg.put("NOMBRE", "NADALES");
            reg.put("CP", "44126");
            faa.insertar(reg, 1);

            System.out.println(
                    "Número de registros: " + faa.getNumReg());

        } catch (IOException e) {
            System.err.println(
                    "Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
