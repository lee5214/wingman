package vehicle;

import basic.Base;
import bullet.Bullet;

abstract public class Vehicle extends Base {

	public int life;

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Vehicle() {
	}

	abstract void move();

	abstract Bullet[] shot();

}
