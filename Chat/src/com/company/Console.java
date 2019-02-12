package com.company;

import javax.swing.*;

import java.awt.*;

public class Console {
    static final JTextField typeField = new JTextField();
    static final JButton send = new JButton("Wyslij");
    static final JFrame frame = new JFrame("Serwer");
    static final JPanel panel = new JPanel();

    public static final JTextArea text = new JTextArea(5,44);
    public static final  JScrollPane scroll = new JScrollPane(text);


    static  final int Szerokosc = 640;
    static final int Wysokosc = 900;

    public Console()
    {   //ustawiam rozmiar itp;
        frame.setSize(Szerokosc,Wysokosc);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        scroll.setPreferredSize(new Dimension(Szerokosc-32,Wysokosc-32 ));

        text.setEditable(false);

        //Dodaje do panelu przewijanie
       // scroll.add(text);
        panel.add(scroll);
       // panel.add(scroll);



        //Dodaje panel
        frame.add(panel);

    }
    public void ServerLogs(String log)
    {
        text.append(log+"\n");
    }
}
