/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class servidor {

    public HashMap<String, String> diccionario;
    public ServerSocket server;
    public int puerto = 6666;
    public String ip = "localhost";
    
    public Socket cliente;

    public servidor(HashMap<String, String> diccionario, ServerSocket server, Socket cliente) {
        this.diccionario = diccionario;
        this.server = server;
        this.cliente = cliente;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public HashMap<String, String> getDiccionario() {
        return diccionario;
    }

    public void setDiccionario(HashMap<String, String> diccionario) {
        this.diccionario = diccionario;
    }

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
        puerto = 6666;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public servidor() {
    }

    public void cargarDiccionario() {
        diccionario = new HashMap<>();
        diccionario.put("Java", "Es un lenguaje de programacion");
        diccionario.put("Html", "Lenguaje de marcado de hypertexto");
        diccionario.put("Python", "Lenguaje de programacion simple");
        diccionario.put("Casa", "Lugar de vivienda");
        diccionario.put("Perro", "Animal leal");
        diccionario.put("Libro", "Conjunto de hojas");
        diccionario.put("Mesa", "Mueble para comer");
        diccionario.put("Silla", "Mueble para sentarse");
        diccionario.put("Ventana", "Abertura para luz");
        diccionario.put("Computadora", "Dispositivo de procesamiento");

    }

    public void iniciarServidor(int puerto) throws IOException {
        cargarDiccionario();

        
        DataOutputStream salida;
        DataInputStream entrada;
        server = new ServerSocket(this.puerto);
        cliente = server.accept();
        System.out.println("Cliente conectado");
        entrada = new DataInputStream(cliente.getInputStream());
        String msgcleinte = entrada.readUTF();
        System.out.println("Cliente: " + msgcleinte);
        salida = new DataOutputStream(cliente.getOutputStream());
//        if (msgcleinte.isEmpty()) {
//            salida.writeUTF("ERROR");
//        }
        try {
            msgcleinte = msgcleinte.substring(0, 1).toUpperCase() + msgcleinte.substring(1).toLowerCase();
        } catch (Exception e) {
        }
        String significado = diccionario.get(msgcleinte);
        if (significado != null) {
            salida.writeUTF(significado);
        } else {
            salida.writeUTF("Palabra no encontrada");
            System.out.println("Palabra no encontrada");
        }

    }

    public static void main(String[] args) throws IOException {
        servidor servidor = new servidor();
        servidor.iniciarServidor(6666);
    }
}
