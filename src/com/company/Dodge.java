package com.company;

import com.company.thread.CreateBulletThread;
import com.company.thread.PilotThread;
import com.company.thread.TimerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Dodge extends JFrame{
    public PilotThread pilotThread;
    public CreateBulletThread createBulletThread;
    public TimerThread timerThread;
    public Rank rank;
    public JPanel panel;
    public JLabel lbl_pilot, lbl_time, lbl_bulletCnt, lbl_main;
    public boolean gameStop = true;
    public boolean check;
    public double time;
    public BufferedReader br;

    public static void main(String[] args) {
        new Dodge().init();
    }

    public void init() {
        this.setLayout(null);
        
        panel = new JPanel() {
            public void paintComponent(Graphics g) {
                Image img = new ImageIcon("../img/space.png").getImage();
                super.paintComponent(g);
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        panel.setLayout(null);
        panel.setBounds(0, 0, 700, 700);

        lbl_time = new JLabel("Timer: 00");
        lbl_time.setBounds(600, 20, 100, 30);
        lbl_time.setForeground(Color.white);
        lbl_bulletCnt = new JLabel("Bullet : 00");
        lbl_bulletCnt.setBounds(330, 20, 100, 30);
        lbl_bulletCnt.setForeground(Color.white);
        lbl_main = new JLabel(new ImageIcon("../img/main.png"));
        lbl_main.setBounds(0, 0, 700, 700);
        this.add(lbl_main);
        this.add(lbl_time);
        this.add(lbl_bulletCnt);

        panel.setFocusable(true);
        panel.requestFocusInWindow();
        this.add(panel);
        this.setSize(panel.getSize());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);



        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && gameStop) {
                    panel.removeAll();
                    gameStart();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void gameStart() {
        lbl_time.setVisible(true);
        lbl_bulletCnt.setVisible(true);
        gameStop = false;
        lbl_main.setVisible(false);
        pilotInit(); // pilot initialize

        pilotThread = new PilotThread(this);
        new Thread(pilotThread).start(); // pilotThread START
        timerThread = new TimerThread(this);
        new Thread(timerThread).start(); // timerThread START
        createBulletThread = new CreateBulletThread(this, 30);
        new Thread(createBulletThread).start(); // createBulletThread
        // START
    }

    public void gameStop() {
        gameStop = true;
        pilotThread.flag = true; // pilotThread STOP
        timerThread.flag = true; // timerThread STOP
        createBulletThread.flag = true; // createBulletThread
        // STOP
        for (int i = 0; i < createBulletThread.bulletThreads.size(); i++) {
            createBulletThread.bulletThreads.get(i).flag = true; // bulletThread
            // STOP
        }
        panel.removeAll();
        rankCheck();

        if (check == true) {

            lbl_time.setVisible(false);
            lbl_bulletCnt.setVisible(false);
            rank = new Rank(String.format("%.2f", time / 1000), panel);
            panel.add(rank);
        } else {
            JOptionPane.showMessageDialog(this, "10� ���� ���߽��ϴ�");
            lbl_main.setVisible(true);
            lbl_bulletCnt.setText("Bullet : 00");
            lbl_time.setText("Time : 00");
        }
    }

    public boolean rankCheck() {
        try {
            br = new BufferedReader(new FileReader(getClass().getResource("../database/rankDB.txt").getFile()));
            check = false;
            String str;
            while ((str = br.readLine()) != null) {
                String[] arr = str.split("/");

                if (Double.parseDouble(arr[2]) > time / 1000) {
                    check = false;
                } else check = true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return check;
    }

    public void pilotInit() {
        ImageIcon pilotImage = new ImageIcon("../img/ufo.png");
        lbl_pilot = new JLabel(pilotImage);
        lbl_pilot.setBounds(325, 325, 30, 30);
        panel.add(lbl_pilot);
    }
}
