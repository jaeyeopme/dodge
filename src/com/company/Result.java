package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Result extends JPanel {
    public final String RANK = getClass().getResource("../database/RankDB.txt").getFile();
    public JLabel lbl_ranking[][], lbl_result;
    public BufferedReader br;

    public Result() {
        this.setOpaque(false);
        lbl_result = new JLabel("RANKING");
        lbl_result.setForeground(Color.white);
        lbl_result.setFont(new Font("Press Start 2P", Font.PLAIN, 32));
        lbl_result.setHorizontalAlignment(JLabel.CENTER);
        lbl_ranking = new JLabel[10][3];
        this.add(new JLabel());
        this.add(lbl_result);
        this.add(new JLabel());

        readRank();
        rankSet();

        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());

        this.setLayout(new GridLayout(0, 3));
        this.setOpaque(false);
    }

    public void readRank() {
        try {
            br = new BufferedReader(new FileReader(RANK));
            int i = 0;
            String str = "";
            while ((str = br.readLine()) != null) {
                String arr[] = str.split("/");
                lbl_ranking[i][0] = new JLabel(arr[0]);
                lbl_ranking[i][1] = new JLabel(arr[1]);
                lbl_ranking[i][2] = new JLabel(arr[2]);

                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void rankSet() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                lbl_ranking[i][j].setHorizontalAlignment(JLabel.CENTER);
                lbl_ranking[i][j].setFont(new Font("Press Start 2P", Font.BOLD, 31));
                lbl_ranking[i][j].setForeground(Color.white);
                this.add(lbl_ranking[i][j]);
            }
        }
    }
}
