package logic;

import enemies.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class IceBullet extends Bullet {
	private int duration,level;
	public IceBullet(double x, double y, Enemy lockedEnemy,int damage,int duration,int level) {
		this.radius = 15;
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.speedx = 0;
		this.speedy = 0;
		this.lockedEnemy = lockedEnemy;
		this.duration = duration;
		this.level = level;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.LIGHTBLUE);
		gc.setStroke(Color.ROYALBLUE);
		gc.fillOval(x, y, 10, 10);
		gc.strokeOval(x, y, 10, 10);
	} 

	public void misile(Enemy enemy) {
		if (enemy != null) {
			double distanceX = enemy.x - this.x;
			double distanceY = enemy.y - this.y;
			double distanceTotal = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
			double moveDistanceX = distanceX / distanceTotal;
			double moveDistanceY = distanceY / distanceTotal;
			this.speedx += moveDistanceX*1.5;
			this.speedy += moveDistanceY*1.5;
			this.x += speedx;
			this.y += speedy;
			if (this.x > lockedEnemy.x) {
				if (this.x - lockedEnemy.x <= radius && this.y - lockedEnemy.y <= radius) {
					this.destroyed = true;
					lockedEnemy.getHit(damage);
					lockedEnemy.freeze(duration, level);
				}
			} else {
				if (lockedEnemy.x - this.x <= radius && lockedEnemy.y - this.y <= radius) {
					this.destroyed = true;
					lockedEnemy.getHit(damage);
					lockedEnemy.freeze(duration, level);
				}
			}
		}
	}

	public void update() {
		misile(lockedEnemy);
	}

}
