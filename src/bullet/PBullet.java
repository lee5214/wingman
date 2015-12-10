package bullet;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import pub.Constant;

public class PBullet extends Bullet {

	public PBullet() {
		this.img = new ImageIcon(Constant.PBULLETImage_1).getImage();
	}

	public PBullet(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	@Override
	public void move() {
		this.y = this.y - Constant.PBULLTE_SPEED;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, panel);
	}

}
