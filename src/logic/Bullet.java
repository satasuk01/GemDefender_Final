package logic;

import enemies.Enemy;
import javafx.scene.canvas.GraphicsContext;

public abstract class Bullet extends CollidableEntity{
	int damage;
	double x,y,speedx,speedy;
	Enemy lockedEnemy;
	@Override
	public abstract void draw(GraphicsContext gc);
	public abstract void update();
}
