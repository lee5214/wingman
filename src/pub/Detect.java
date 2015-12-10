package pub;

import java.awt.Rectangle;

import basic.Base;
import bullet.Bullet;
import bullet.PBullet;
import vehicle.Enemy;
import vehicle.Vehicle;

public class Detect {

	public static boolean AC(PBullet ball, Enemy enemy) {
		return CheckPoint(ball.getX(), ball.getY(), enemy);
	}

	public static boolean CheckPoint(int x, int y, Enemy enemy) {
		return x > enemy.getX() && x < enemy.getX() + enemy.getWidth()
				&& y > enemy.getY() && y < enemy.getY() + enemy.getHeight();
	}


	public static boolean C1(Bullet ball, Vehicle enemy) {
		Rectangle a = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(),
				ball.getHeight());
		Rectangle b = new Rectangle(enemy.getX(), enemy.getY(), enemy
				.getWidth(), enemy.getHeight());
		return a.intersects(b);
	}


	public static boolean Testing(Base ball, Base enemy) {
		Rectangle a = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(),
				ball.getHeight());
		Rectangle b = new Rectangle(enemy.getX(), enemy.getY(), enemy
				.getWidth(), enemy.getHeight());
		return a.intersects(b);
	}

	public static boolean C3(int x, int y, int w, int h, Enemy enemy) {
		return C1(new PBullet(x, y, w, h), enemy);
	}

}
