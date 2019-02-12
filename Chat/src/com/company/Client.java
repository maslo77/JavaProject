package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Client implements Runnable{
    public Thread ClientT = new Thread(this);
    public PrintWriter outcoming;
   public BufferedReader incoming;

   public Client(PrintWriter out,BufferedReader in)
   {
      outcoming = out;
      incoming = in;
   }

   @Override
   public void run() {
     while(true)
     {
         try {
             String msg = incoming.readLine();
             ChatServer.sendToAll(msg);
         } catch (IOException e) {

             e.printStackTrace();
             ClientT.stop();
         }

     }
   }
}
