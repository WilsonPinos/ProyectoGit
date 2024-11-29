/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class clientetcp {

    public Socket cliente;
    public DataOutputStream salida;
    public DataInputStream entrada;

    public clientetcp() {
    }

    public clientetcp(Socket socket, DataOutputStream salida, DataInputStream entrada) {
        this.cliente = socket;
        this.salida = salida;
        this.entrada = entrada;
    }

    public Socket getSocket() {
        return cliente;
    }

    public void setSocket(Socket socket) {
        this.cliente = socket;
    }

    public DataOutputStream getSalida() {
        return salida;
    }

    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    public void iniciarCliente() {
        try {
            Scanner sc = new Scanner(System.in);

            this.cliente = new Socket("localhost", 6666);

            this.salida = new DataOutputStream(cliente.getOutputStream());
            String mensaje;
            while (true) {
                cambioparagit c = new cambioparagit();
                c.mensaje();
                System.out.println("");
            System.out.println("Ingresa una palabra: ");
            mensaje = sc.nextLine();

            if (mensaje.trim().isEmpty()) {
                System.out.println("La palabra no puede estar vacia");
                continue;
            }

            salida.writeUTF(mensaje);
            this.entrada = new DataInputStream(cliente.getInputStream());
            String respuesta = entrada.readUTF();

            if (respuesta.equals("ERROR")) {
                System.out.println("Palabra no encontrada");
            } else {
                System.out.println("Significado: ");
                System.out.print(respuesta);
                System.out.println("");
            }

            break;
        }
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        clientetcp client = new clientetcp();
        client.iniciarCliente();
    }
}
