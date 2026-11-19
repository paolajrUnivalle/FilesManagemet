package co.edu.univalle.poe.mvc.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Path.of;

public class Record {
    private static final Path RUTA_RECORD =  Path.of("src","main","java","co","edu",
                                                          "univalle","poe","mvc",
                                                          "model","record.txt");

    private BufferedWriter writer;
    private BufferedReader reader;

    private Estado estado;

    public Estado saveRecord(String nombre, int puntaje) {
        String linea = nombre + "," + puntaje;

        try {
            writer = Files.newBufferedWriter(RUTA_RECORD, StandardCharsets.UTF_8);
            writer.write(linea);
            writer.newLine();
            writer.close();
            estado = Estado.GUARDADO_OK;
        } catch (IOException e) {
            estado = Estado.GUARDADO_ERROR;
        }
        return estado;
    }

    public RecordJugador loadRecord(){
        RecordJugador recordJugador;
        try {
            reader = Files.newBufferedReader(RUTA_RECORD);
            String linea = reader.readLine();
            String[] partes = linea.split(",");
            recordJugador = new RecordJugador(partes[0],Integer.parseInt(partes[1]));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return recordJugador;
    }

    public Estado saveVariousRecord(String nombre, int puntaje) {
        String linea = nombre + "," + puntaje;

        try {
            writer = Files.newBufferedWriter(RUTA_RECORD, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            writer.write(linea);
            writer.newLine();
            writer.close();
            estado = Estado.GUARDADO_OK;
        } catch (IOException e) {
            estado = Estado.GUARDADO_ERROR;
        }
        return estado;
    }

    public List<RecordJugador> loadVariousRecord(){
        List<RecordJugador> registros = new ArrayList<>();
        String linea;

        try {
            reader = Files.newBufferedReader(RUTA_RECORD);
            linea = reader.readLine();
            while(linea!=null){
                String[] partes = linea.split(",");
                registros.add(new RecordJugador(partes[0],Integer.parseInt(partes[1])));
                linea = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return registros;
    }
}
