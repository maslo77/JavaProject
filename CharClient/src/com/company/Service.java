package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Service{
    Socket socket;
   public static String nick="";
    public static PrintWriter outcoming;
    public static BufferedReader incoming;


    public Service() throws IOException {
        new ClientGUI();

        try {
            socket = new Socket("localhost",1234);
            outcoming = new PrintWriter(socket.getOutputStream(),true);
            incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true) {
                String readed = incoming.readLine();
                ClientGUI.chatting(readed);
            }
        } catch (IOException e) {

            e.printStackTrace();

        }


    }
    public static void main(String[] args) throws IOException {
        nick = JOptionPane.showInputDialog("Wprowadz nick: ");
       while(nick.equals(""))
       {    JOptionPane.showMessageDialog(null,"Wprowadz poprawny nick!");
           nick = JOptionPane.showInputDialog("Wprowadz nick: ");
       }
        new Service();
    }
}
