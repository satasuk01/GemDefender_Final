package logic;

import enemies.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import sharedObject.RenderableHolder;

public class FireTower extends Tower{
	int duration = 10;

	public FireTower(int row,int column){
		this.price = 80;
		this.damage = 3;
		this.splashAttack = true;
		this.range = 65;
		this.fireRate = 15;
		this.fireCount = 0;
		this.radius = 10;
		this.row = row;
		this.column = column;
		this.x = Field.getPositionX(column);
		this.y = Field.getPositionY(row);
		this.angle = 0;
		this.isDrew = false;
		this.eventAdded = false;
		this.level = 0;
		this.isSelected = false;
	}
	
	private void Attack() {
		int maxAttack = 5;
		int counter = 0;
		for(Enemy enemy : GameLogic.getEnemy()) {
			if(isInRange(enemy)&&counter<maxAttack) {
				FireBullet arrow = new FireBullet(x, y, enemy,this.damage,this.duration,this.level);
				GameLogic.gameObjectContainer.add(arrow);
				RenderableHolder.getInstance().add(arrow);
				counter++;
			}if(counter>=maxAttack) break;
		}
	}
	
	@Override
	public void update() {
		//----------Attack-------
		if(fireCount > fireRate) {
			Attack();
			fireCount = 0;
		}
		fireCount ++;
		//----------------------
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		if(isSelected == true) {
			//-----Draw Attack Range----
			gc.setLineWidth(1.0);
			gc.strokeArc(x-range, y-range, range*2, range*2, 0, 360, ArcType.OPEN);
		}
	}
	
	@Override
	public ImageView drawImageView() {
		tower = new ImageView(RenderableHolder.fireTowerSprite[level]);
		tower.relocate(x-12.5, y-12.5);
		tower.setRotate(angle);
		isDrew = true;
		return tower;
	}
	@Override
	public void changeSprite() {
		tower.setImage(RenderableHolder.fireTowerSprite[level]);
	}
	@Override
	public void move() {
		tower.setLayoutX(x-12.5);
		tower.setLayoutY(y-12.5);
		tower.setRotate(angle);
	}
	@Override
	public void destroy() {
		tower.setImage(null);
		this.destroyed = true;
	}
	@Override
	public ImageView getImageView() {
		return tower;
	}

	
}
