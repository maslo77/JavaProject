package com.company;

import com.sun.security.ntlm.Server;

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
            ClientGUI.chatting("Trwa nawiazywanie polaczenia z serwerem...");
            socket = new Socket("localhost",1234);
            ClientGUI.chatting("Polaczono! Witamy w pokoju czatowym!");
            outcoming = new PrintWriter(socket.getOutputStream(),true);
            incoming = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            while(true) {
                String readed = incoming.readLine();
                ClientGUI.chatting(readed);
            }
        } catch (IOException e) {
            ClientGUI.noConnection();
            e.printStackTrace();
            System.exit(1);
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
