package com.company;

import com.sun.security.ntlm.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientGUI extends JFrame {

   private static final long UserID = 1L;

   static int Szerokosc = 640;
   static int Wysokosc = 700;

   static final JPanel panel = new JPanel();
   static final JTextArea textarea = new JTextArea();
   static final JScrollPane scroll = new JScrollPane(textarea);
   static final JTextField  sendField = new JTextField();
   static final JButton Button = new JButton("Wyslij");

   public ClientGUI()
   {
       try{
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
       }catch (Exception e){
           e.printStackTrace();
       }
       this.setSize(Szerokosc,Wysokosc);
       this.setVisible(true);
       this.setResizable(false);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        scroll.setPreferredSize(new Dimension(Szerokosc-32,Wysokosc-130));
        sendField.setPreferredSize(new Dimension(Szerokosc -100,48));


        panel.add(scroll);
        panel.add(sendField);
       sendField.requestFocusInWindow();
       panel.add(Button);

       this.add(panel);
       this.getRootPane().setDefaultButton(Button);
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mess = sendField.getText();
                if (mess.equals("")) {
                    JOptionPane.showMessageDialog(null,"Pusta Wiadomosc!");
                } else {
                    DateFormat DateForm = new SimpleDateFormat("HH:mm:ss");

                    Date data = new Date();
                    Service.outcoming.println(DateForm.format(data)+" "+Service.nick + ": " + mess);
                    sendField.setText("");
                }
            }
        });
    }
    public static void chatting(String message)
    {
        textarea.append(message+"\n");
    }
    public static int noConnection(){return JOptionPane.showConfirmDialog(null,"Brak połączenia! Połączyć ponownie?");
}
}