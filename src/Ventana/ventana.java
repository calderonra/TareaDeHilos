/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventana;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import threads.AnimalThreads;


/**
 *
 * @author Raul Calderon <https://github.com/calderonra>
 */
public class ventana extends JFrame {

    private JPanel[] paneles;
    private JLabel[] labels;
    private JLabel reloj;
    private JButton inicio;
    private JButton reiniciar;
    private String[] nombres = {"canguro", "tortuga", "dragon"};

    public ventana() {
        super("Carrera de animales");
        initialComponents();
        time();
    }

    public void initialComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        paneles = new JPanel[3];
        labels = new JLabel[3];
        inicio = new JButton("Inicio");
        reiniciar = new JButton("Reinicio");
        reloj = new JLabel("clock");

        Container container = getContentPane();
        //llenando el panel
        for (int i = 0; i < 3; i++) {
            labels[i] = new JLabel();
            labels[i].setIcon(new ImageIcon(getClass().getResource(nombres[i] + ".gif")));
            labels[i].setBounds(10, (i * 220) + 10, 200, 200);
            container.add(labels[i]);
        }
        inicio.setBounds(10, 0, 100, 30);
        reiniciar.setBounds(550, 0, 100, 30);
        reloj.setBounds(350, 0, 400, 30);
        container.add(inicio);
        container.add(reiniciar);
        container.add(reloj);
        reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

            }

        });
        inicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AnimalThreads canguro = new AnimalThreads("canguro", labels[0].getX(), labels[0].getY(), 510, labels[0]);
                AnimalThreads tortuga = new AnimalThreads("colibri", labels[1].getX(), labels[1].getY(), 510, labels[1]);
                AnimalThreads dragon = new AnimalThreads("dragon", labels[2].getX(), labels[2].getY(), 510, labels[2]);
                canguro.start();
                tortuga.start();
                dragon.start();
            }

        });
        setSize(700, 650);
    }

    public void time() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Calendar c = new GregorianCalendar();
                        int hora = c.get(Calendar.HOUR);
                        int minuto = c.get(Calendar.MINUTE);
                        int segundos = c.get(Calendar.SECOND);
                        System.out.println();
                        reloj.setText("Hora: " + hora + "Minutos: " + minuto + "Segundos: " + segundos);
                        sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        clock.start();

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ventana().setVisible(true);
            }
        });
    }

}
