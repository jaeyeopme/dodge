package com.company.thread;

import com.company.Dodge;

public class TimerThread implements Runnable {
	Dodge frame;
	public boolean flag;
	long beforeTime, afterTime;

	public TimerThread(Dodge frame) {
		this.frame = frame;
	}

	@Override
	public void run() {
		beforeTime = System.currentTimeMillis();
		while (!flag) {
			afterTime = System.currentTimeMillis();
			frame.time = (afterTime - beforeTime);
			frame.lbl_time.setText("Time : " + String.format("%.2f", frame.time / 1000));
			frame.repaint();
			frame.revalidate();
			frame.lbl_bulletCnt.setText(
					"Bullet : " + (frame.createBulletThread.redBulletCount
							+ frame.createBulletThread.blueBulletCount
							+ frame.createBulletThread.greenBulletCount));
		}
	}
}
