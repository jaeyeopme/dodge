package com.company.thread;

import com.company.Dodge;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateBulletThread implements Runnable {
	Dodge frame;
	public boolean flag;
	public ArrayList<BulletThread> bulletThreads = new ArrayList<>();
	long second = 1, defaultCount, redBulletCount, blueBulletCount, greenBulletCount;
	ImageIcon bulletColor;

	public CreateBulletThread(Dodge frame, int defaultCount) {
		this.frame = frame;
		this.defaultCount = defaultCount;
	}

	public void directionInit(JLabel bullet, int direction) {
		int x, y;
		int width = 10, height = 10;

		switch (direction) {
		case 0: // west
			y = (int) (Math.random() * 650);
			bullet.setBounds(10, y, width, height);
			break;
		case 1: // north
			x = (int) (Math.random() * 670);
			bullet.setBounds(x, 10, width, height);
			break;
		case 2: // east
			y = (int) (Math.random() * 650);
			bullet.setBounds(670, y, width, height);
			break;
		case 3: // south
			x = (int) (Math.random() * 670);
			bullet.setBounds(x, 650, width, height);
			break;
		}
	}

	public Thread bulletInit(Color color, int speed) {
		if (color == Color.red) {
			bulletColor = new ImageIcon(getClass().getResource("../img/red.png"));
		} else if (color == Color.blue) {
			bulletColor = new ImageIcon(getClass().getResource("../img/blue.png"));
		} else if (color == Color.green) {
			bulletColor = new ImageIcon(getClass().getResource("../img/green.png"));
		}

		JLabel bullet = new JLabel(bulletColor);

		int direction = (int) (Math.random() * 4);
		directionInit(bullet, direction);

		frame.panel.add(bullet);

		BulletThread bulletThread = new BulletThread(frame, bullet, direction, speed);
		bulletThreads.add(bulletThread);

		return new Thread(bulletThread);
	}

	@Override
	public void run() {
		for (int i = 0; i < defaultCount; i++) {
			bulletInit(Color.red, 1).start();
			redBulletCount++;
		}
		while (!flag) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (second % 3 == 0 && flag == false) {
				bulletInit(Color.green, 6).start();
				redBulletCount++;
			} else if (second % 2 == 0 && flag == false) {
				bulletInit(Color.blue, 3).start();
				blueBulletCount++;
			} else if (second % 1 == 0 && flag == false) {
				bulletInit(Color.red, 1).start();
				greenBulletCount++;
			}
			second++;
			frame.repaint();
			frame.revalidate();
		}
	}
}
