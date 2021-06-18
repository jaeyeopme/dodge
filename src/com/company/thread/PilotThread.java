package com.company.thread;

import com.company.Dodge;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PilotThread implements Runnable, KeyListener {
	Dodge frame;
	public boolean flag;
	boolean press_left;
	boolean press_up;
	boolean press_right;
	boolean press_down;

	public PilotThread(Dodge frame) {
		this.frame = frame;
		frame.panel.addKeyListener(this);
	}

	public void draw(int speed) {
		int x = 0, y = 0;

		if (press_left && frame.lbl_pilot.getX() > 10) {
			x = frame.lbl_pilot.getX();
			x -= speed;
			frame.lbl_pilot.setLocation(x, frame.lbl_pilot.getY());
		}
		if (press_up && frame.lbl_pilot.getY() > 10) {
			y = frame.lbl_pilot.getY();
			y -= speed;
			frame.lbl_pilot.setLocation(frame.lbl_pilot.getX(), y);
		}
		if (press_right && frame.lbl_pilot.getX() < 650) {
			x = frame.lbl_pilot.getX();
			x += speed;
			frame.lbl_pilot.setLocation(x, frame.lbl_pilot.getY());
		}
		if (press_down && frame.lbl_pilot.getY() < 630) {
			y = frame.lbl_pilot.getY();
			y += speed;
			frame.lbl_pilot.setLocation(frame.lbl_pilot.getX(), y);
		}
	}

	@Override
	public void run() {
		while (!flag) {
			try {
				wait();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			draw(3);
			frame.repaint();
			frame.revalidate();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			press_left = true;
			break;
		case KeyEvent.VK_UP:
			press_up = true;
			break;
		case KeyEvent.VK_RIGHT:
			press_right = true;
			break;
		case KeyEvent.VK_DOWN:
			press_down = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			press_left = false;
			break;
		case KeyEvent.VK_UP:
			press_up = false;
			break;
		case KeyEvent.VK_RIGHT:
			press_right = false;
			break;
		case KeyEvent.VK_DOWN:
			press_down = false;
			break;
		}
	}
}
