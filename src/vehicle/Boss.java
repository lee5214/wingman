package vehicle;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import bullet.Bullet;
import bullet.EBullet;
import pub.Constant;

public class Boss extends Vehicle {

	public Random random = new Random();

	public Boss() {
		this.img = new ImageIcon(Constant.BOSSImage).getImage();
		life = 500;
		//Play.playBoss();
	}

	private int xspeed = 5;
	private int yspeed = 2;

	@Override
	public void move() {
		if (this.x > Constant.FRAME_WIDTH - this.width || this.x < 0) {
			xspeed = 0 - xspeed;
		}
		if (this.y > 200 || this.y < 0) {
			yspeed = 0 - yspeed;
		}
		x = x + xspeed;
		y = y + yspeed;
	}

	@Override
	public Bullet[] shot() {

		Bullet bullet[] = new EBullet[Constant.BOSS01_NUMBER];
		for (int i = 0; i < Constant.BOSS01_NUMBER; i++) {
			bullet[i] = new EBullet();
			bullet[i].setX(x
					+ this.width
					/ (Constant.BOSS01_NUMBER == 1 ? 2
							: Constant.BOSS01_NUMBER - 1) * i);
			bullet[i].setY(y + this.height);
			bullet[i].setWidth(5);
			bullet[i].setHeight(8);
			bullet[i].setHurt(Constant.EBULLTE_HURT);
		}
		return bullet;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, panel);
	}
	
	
}
