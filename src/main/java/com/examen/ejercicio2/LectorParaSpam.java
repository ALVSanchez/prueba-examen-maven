package com.examen.ejercicio2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * LectorParaSpam
 */
public class LectorParaSpam {

    String rutaMensajes;
    String rutaDirecciones;

    ArrayList<SpamListener> listeners;

    public interface SpamListener {
        public void emailPreparado(String direccion, String mensaje);
    }

    public void addListener(SpamListener listener) {
        listeners.add(listener);
    }

    LectorParaSpam(String rutaMensajes, String rutaDirecciones) {
        this.rutaMensajes = rutaMensajes;
        this.rutaDirecciones = rutaDirecciones;
        this.listeners = new ArrayList<>();
    }

    public void comenzarLectura() {
        try (BufferedReader readerMensajes = new BufferedReader(new FileReader(rutaMensajes));
                BufferedReader readerDirecciones = new BufferedReader(new FileReader(rutaDirecciones));) {

            String lineaMensaje;
            String lineaDireccion;

            while ((lineaMensaje = readerMensajes.readLine()) != null
                    && (lineaDireccion = readerDirecciones.readLine()) != null) {

                for (SpamListener listener : listeners) {
                    listener.emailPreparado(lineaDireccion, lineaMensaje);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}