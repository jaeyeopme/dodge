package com.company.thread;

import com.company.Dodge;

import javax.swing.*;

public class BulletThread implements Runnable {
    public boolean flag;
    Dodge frame;
    JLabel bullet;
    int width, height;
    int x, y, bounce, direction;
    int speed;

    public BulletThread(Dodge frame, JLabel bullet, int direction, int speed) {
        this.frame = frame;
        this.bullet = bullet;
        this.direction = direction;
        this.speed = speed;
        this.width = frame.getWidth();
        this.height = frame.getHeight();
        this.x = bullet.getX();
        this.y = bullet.getY();
    }

    public void move() {
        if (0 <= x && x <= width && 0 <= y && y <= height) {
            switch (direction) {
                case 0: // west
                    if (bounce == 0) {
                        y += speed;
                    } else {
                        y -= speed;
                    }
                    x += speed;
                    bullet.setLocation(x, y);
                    break;
                case 1: // north
                    if (bounce == 0) {
                        x += speed;
                    } else {
                        x -= speed;
                    }
                    y += speed;
                    bullet.setLocation(x, y);
                    break;
                case 2: // east
                    if (bounce == 0) {
                        y += speed;
                    } else {
                        y -= speed;
                    }
                    x -= speed;
                    bullet.setLocation(x, y);
                    break;
                case 3: // south
                    if (bounce == 0) {
                        x += speed;
                    } else {
                        x -= speed;
                    }
                    y -= speed;
                    bullet.setLocation(x, y);
                    break;
            }
        }
    }

    public void bounce() {
        if (x >= width - 30) { // west
            bounce = (int) (Math.random() * 2);
            direction = 2;
        } else if (y >= height - 50) { // north
            bounce = (int) (Math.random() * 2);
            direction = 3;
        } else if (x <= 10) { // east
            bounce = (int) (Math.random() * 2);
            direction = 0;
        } else if (y <= 10) { // south
            bounce = (int) (Math.random() * 2);
            direction = 1;
        }
    }

    public void attack() {
        if ((frame.lbl_pilot.getX() + frame.lbl_pilot.getWidth() - 4 > bullet.getX()) && // east
                (frame.lbl_pilot.getX() + 4 < bullet.getX() + bullet.getWidth()) && // west
                (frame.lbl_pilot.getY() + frame.lbl_pilot.getHeight() - 4 > bullet.getY()) && // north
                (frame.lbl_pilot.getY() + 4 < bullet.getY() + bullet.getHeight())) { // south
            frame.gameStop();
        }
    }

    @Override
    public void run() {
        while (!flag) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move();
            bounce();
            attack();
            frame.repaint();
            frame.revalidate();
        }
    }
}
