package vehicle;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import bullet.Bullet;
import bullet.PBullet;
import pub.Constant;
public class Player extends Vehicle {
    int PlayerNum;
public boolean isFire=false;
	public static boolean up, down, left, right; // �ɻ���������ҷ���

	public void setLife(int life) {
		if (life <= 0)
			life = 0;
		if (life >= 100)
			life = 100;
		this.life = life;
	}

	public Player(int num) {
            this.PlayerNum=num;
		this.img = new ImageIcon(Constant.PlayerImage_1).getImage();
		life = 100;
	}
       // public int getPlayerNum(){
    //return num;
//}

	@Override
	public void move() {
		if (Player.up) {
			if (y - Constant.PLAYER_SPEED >= 0) {
				y -= Constant.PLAYER_SPEED;
			}
		}
		if (Player.down) {
			if (y + Constant.PLAYER_SPEED <= Constant.FRAME_HEIGHT - 100) {
				y += Constant.PLAYER_SPEED;
			}
		}
		if (Player.right) {
			if (x + Constant.PLAYER_SPEED <= Constant.FRAME_WIDTH - 80)
				x += Constant.PLAYER_SPEED;
		}
		if (Player.left) {
			if (x - Constant.PLAYER_SPEED >= 0)
				x -= Constant.PLAYER_SPEED;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, panel);
	}

	@Override
	public Bullet[] shot() {

		PBullet bullet[] = new PBullet[Constant.PBULLTE_NUMBER];
		for (int i = 0; i < Constant.PBULLTE_NUMBER; i++) {
			bullet[i] = new PBullet();
			bullet[i].setX(x
					+ this.width
					/ (Constant.PBULLTE_NUMBER == 1 ? 2
							: Constant.PBULLTE_NUMBER - 1) * i-4);
			if(Constant.PBULLTE_NUMBER==1){
				bullet[i].setX(x
					+ this.width/2-4);
			}
			bullet[i].setY(y);
			bullet[i].setWidth(8);
			bullet[i].setHeight(15);
			bullet[i].setHurt(Constant.PBULLTE_HURT);
		}
		return bullet;
	}

}
