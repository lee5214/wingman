package vehicle;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import bullet.Bullet;
import bullet.EBullet;
import pub.Constant;

public class Enemy extends Vehicle {

	public Enemy() {
		this.img = new ImageIcon(Constant.ENERMYImage).getImage();
		life = 1;
	}

	@Override
	public void move() {
		this.y = this.y + Constant.ENEMY01_SPEED;
	}
        public void move2(){
            this.x = this.x + Constant.ENEMY01_SPEED;
        }

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, panel);
	}

	@Override
	public Bullet[] shot() {
		Bullet bullet[] = new EBullet[Constant.EBULLTE_NUMBER];
		for (int i = 0; i < Constant.EBULLTE_NUMBER; i++) {
			bullet[i] = new EBullet();
			bullet[i].setX(x
					+ this.width
					/ (Constant.EBULLTE_NUMBER == 1 ? 2
							: Constant.EBULLTE_NUMBER - 1) * i);
			if(Constant.EBULLTE_NUMBER==1){
				bullet[i].setX(x
					+ this.width/2-2);
			}
			bullet[i].setY(y+height);
			bullet[i].setWidth(5);
			bullet[i].setHeight(10);
			bullet[i].setHurt(Constant.EBULLTE_HURT);
		}
		return bullet;
	}

	public Bang getBang() {
		Bang bang = new Bang();
		bang.setX(x);
		bang.setY(y);
		bang.setWidth(width);
		bang.setHeight(height);
		return bang;
	}
}
