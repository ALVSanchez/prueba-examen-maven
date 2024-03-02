package com.examen.ejercicio1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Receptor {

    private static final int PACKET_SIZE = 1000;

    private ArrayList<CuadradoListener> listeners = new ArrayList<>();

    private int port;

    Receptor(int port){
        this.port = port;
    }

    public void addListener(CuadradoListener listener){
        listeners.add(listener);
    }

    public void escuchar() {
        try(DatagramSocket socket = new DatagramSocket(port)) {
            
            byte[] buffer = new byte[PACKET_SIZE];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                socket.receive(packet);
                String datos = new String(packet.getData());
                for(CuadradoListener listener : listeners){
                    listener.cuadradoRecibido(datos);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}