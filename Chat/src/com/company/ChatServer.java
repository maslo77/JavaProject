package com.company;

import com.sun.security.ntlm.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    //Polaczenie
    ServerSocket ServerSocket1 ;

    Socket socket;

    PrintWriter outcoming;
    BufferedReader incoming;
    static ArrayList<Client> clients = new ArrayList<>();
    public static  Console konsola;
        public ChatServer(){
            try {
                ServerSocket1 = new ServerSocket(25568);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true)
                        {
                            try {
                                socket = ServerSocket1.accept(); //Akceptujemy polaczenie od klienta



                                outcoming = new PrintWriter(socket.getOutputStream(),true);
                                incoming  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                Client c = new Client(outcoming,incoming);
                                c.ClientT.start();
                                clients.add(c);
                                konsola.ServerLogs("Dolaczyl uzytkownik o adresie: "+socket.getLocalAddress());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }).start();

            }catch (IOException e)
            {
                e.printStackTrace();
            }
        konsola = new Console();
        }

     public static void sendToAll(String message)
     {
         for(int i = 0 ; i < clients.size(); ++i)
         {
            Client k = clients.get(i);
            //if(k == null) clients.remove(i);//sprawdzam czy istnieje uzytkownik
             if(k != null && k.outcoming != null && k.incoming != null)
             k.outcoming.println(message);
         }
     }
    public static void main(String[] args)
    {
        new ChatServer();
        konsola.ServerLogs("Serwer Zostal Uruchomiony!");
        konsola.ServerLogs("Porozmawiaj z nami!");
    }
}
