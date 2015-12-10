package bullet;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import pub.Constant;

public class EBullet extends Bullet {

	public EBullet() {
		this.img = new ImageIcon(Constant.EBULLETImage).getImage();
	}

	@Override
	public void move() {
		this.y = this.y + Constant.EBULLTE_SPEED;

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, panel);
	}

}
