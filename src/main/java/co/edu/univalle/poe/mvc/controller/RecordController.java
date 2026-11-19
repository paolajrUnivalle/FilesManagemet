package co.edu.univalle.poe.mvc.controller;

import co.edu.univalle.poe.mvc.model.Estado;
import co.edu.univalle.poe.mvc.model.Record;
import co.edu.univalle.poe.mvc.model.RecordJugador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;


public class RecordController {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPuntaje;

    private Record record;
    private Estado estado;

    public RecordController(){
      record = new Record();
    }

    @FXML
    public void guardarDatos(){
        //validar que los campos estén bien diligenciados

        //estado = record.saveRecord(txtNombre.getText(), Integer.parseInt(txtPuntaje.getText()));
        estado = record.saveVariousRecord(txtNombre.getText(), Integer.parseInt(txtPuntaje.getText()));
        mostrarMensaje(estado,"");
    }

    private void mostrarMensaje(Estado estado, String salida){
        Alert alerta;
        switch (estado){
            case GUARDADO_OK:
                alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Guardado Exitoso");
                alerta.setHeaderText("Datos guardado en el archivo");
                alerta.setContentText("Se ha guardado el nombre y puntaje exitosamente");
                alerta.showAndWait();
                limpiarTextField();
                break;
            case GUARDADO_ERROR:
                alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al guardar datos");
                alerta.setHeaderText("No se ha podido guardar datos en el archivo");
                alerta.setContentText("Se ha presentado una falla al intentar acceder al archivo");
                alerta.showAndWait();
                limpiarTextField();
                break;
            case MOSTRAR_RECORDS:
                alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Datos Guardados en el archivo");
                alerta.setHeaderText("Jugadores y su puntaje");
                alerta.setContentText(salida);
                alerta.showAndWait();
                limpiarTextField();
                break;
        }
    }

    @FXML
    public void cargarDatos(){
       /* RecordJugador recordJugador = record.loadRecord();
        txtNombre.setText(recordJugador.getNombre());
        txtPuntaje.setText(Integer.toString(recordJugador.getPuntaje()));*/

        List<RecordJugador> registros = record.loadVariousRecord();
        String salida = "Jugador -  Puntaje";

        for(RecordJugador recordJugador : registros){
            salida+= "\n"+recordJugador.getNombre()+" - "+recordJugador.getPuntaje();
        }

        mostrarMensaje(Estado.MOSTRAR_RECORDS,salida);

    }

    private void limpiarTextField(){
        txtNombre.setText("");
        txtPuntaje.setText("");
    }
}
