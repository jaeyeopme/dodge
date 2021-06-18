package com.company;

import javax.swing.*;
import java.io.*;

public class Rank extends JPanel {
    final String RANK = getClass().getResource("../database/rankDB.txt").getFile();
    String name;
    BufferedReader br;
    PrintWriter pw;
    JButton btn;
    String rank[][];
    int i = 0;
    int result = 0;
    double time;
    Result rr;
    JPanel panel;


    public Rank(String s, JPanel p) {
        time = Double.parseDouble(s);
        this.panel = p;
        this.setOpaque(false);
        rank = new String[10][3];
        saveRank(time);
    }

    public void saveRank(Double time) {
        try {
            br = new BufferedReader(new FileReader(RANK));

            String str = "";
            while ((str = br.readLine()) != null) {
                String[] arr = str.split("/");

                rank[i][0] = arr[0];
                rank[i][1] = arr[1];
                rank[i][2] = arr[2];
                i++;

            }

            pw = new PrintWriter(new FileWriter(RANK), true);

            for (int j = 0; j < 9; j++) {
                if (Double.parseDouble(rank[0][2]) < time) {
                    for (int k = 9; k >= j + 1; k--) {
                        rank[k][0] = (k + 1) + "";
                        rank[k][1] = rank[k - 1][1];
                        rank[k][2] = rank[k - 1][2];
                    }

                    JOptionPane.showMessageDialog(this, "1���Դϴ�!");
                    name = JOptionPane.showInputDialog("�г����� �Է����ּ���.");
                    if (name.equals("") || name == null) {
                        name = "AAA";
                    }

                    rank[j][0] = 1 + "";
                    rank[j][1] = name;
                    rank[j][2] = time + "";
                    result = 1;


                    for (int q = 0; q < 10; q++) {
                        pw.println(rank[q][0] + "/" + rank[q][1]
                                + "/"
                                + rank[q][2]);
                    }

                    rr = new Result();
                    panel.add(rr);
                    rr.setBounds(0, 0, 700, 700);
                    this.revalidate();
                    this.repaint();

                    break;
                } else if (Double.parseDouble(rank[j][2]) > time && Double
                        .parseDouble(rank[j + 1][2]) < time) {
                    for (int k = 9; k > j + 1; k--) {
                        rank[k][0] = (k + 1) + "";
                        rank[k][1] = rank[k - 1][1];
                        rank[k][2] = rank[k - 1][2];
                    }

                    JOptionPane.showMessageDialog(this, (j + 2) + "���Դϴ�.");
                    name = JOptionPane.showInputDialog("�г����� �Է����ּ���.");
                    if (name.equals("") || name == null) {
                        name = "AAA";
                    }
                    rank[j + 1][0] = (j + 2) + "";
                    rank[j + 1][1] = name;
                    rank[j + 1][2] = time + "";
                    result = j + 2;

                    for (int q = 0; q < 10; q++) {
                        pw.println(rank[q][0] + "/" + rank[q][1]
                                + "/"
                                + rank[q][2]);
                    }

                    rr = new Result();
                    panel.add(rr);
                    rr.setBounds(0, 0, 700, 700);
                    this.revalidate();
                    this.repaint();

                    break;
                } else if (j == 8) {

                    JOptionPane.showMessageDialog(this, "10��ȿ� ���� ���߽��ϴ�.");

                    for (int q = 0; q < 10; q++) {
                        pw.println(rank[q][0] + "/" + rank[q][1]
                                + "/"
                                + rank[q][2]);
                    }

                    rr = new Result();
                    panel.add(rr);
                    rr.setBounds(0, 0, 700, 700);
                    this.revalidate();
                    this.repaint();

                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null)
                pw.close();
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
