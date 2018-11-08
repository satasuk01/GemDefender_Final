package logic;

import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import core.GameCore;
import enemies.Enemy;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.shape.ArcType;
import sharedObject.RenderableHolder;

public class SniperTower extends Tower {

	public SniperTower(int row, int column) {
		this.price = 100;
		this.damage = 50;
		this.splashAttack = false;
		this.range = 100;
		this.fireRate = 60;
		this.fireCount = 0;
		this.radius = 10;
		this.row = row;
		this.column = column;
		this.x = Field.getPositionX(column);
		this.y = Field.getPositionY(row);
		this.angle = 0;
		this.isDrew = false;
		this.destroyed = false;
		this.eventAdded = false;
		this.level = 0;
		this.isSelected = false;
	}

	private void Attack() {
		int nearestDistance = range;
		Enemy lockedEnemy = null;
		for (Enemy enemy : GameLogic.getEnemy()) {
			if (inRange(enemy) <= nearestDistance) {
				nearestDistance = inRange(enemy); 
				lockedEnemy = enemy;
			}

		}
		if (lockedEnemy != null) {

			SniperBullet arrow = new SniperBullet(x, y, lockedEnemy,this.damage);
			GameLogic.gameObjectContainer.add(arrow);
			RenderableHolder.getInstance().add(arrow);

		}
		nearestDistance = range;
		lockedEnemy = null;
	}

	@Override
	public void update() {

		// ----------Attack-------
		if (fireCount > fireRate) {
			Attack();
			fireCount = 0;
		}
		fireCount++;

	}

	@Override
	public void draw(GraphicsContext gc) {
		if(isSelected == true) {
			// -----Draw Attack Range----
			gc.setLineWidth(1.0);
			gc.strokeArc(x - range, y - range, range * 2, range * 2, 0, 360, ArcType.OPEN);
		}
	}
	@Override
	public int upgrade() { //level 0 price*1.5 level 1 price*2.5
		if(this.level>=2) {
			return 0;
		}else {
			if(this.level ==0) {
				if((int)((double)this.price*1.5)<=GameCore.getGold()) {
					this.level++;
					this.damage += (int)((double)damage*1.5);
					this.range += 10;
					GameCore.decreaseGold((int)((double)this.price*1.5));
					changeSprite();
					return (int)((double)this.price*2.5);
				}
			}
			if(this.level ==1) {
				if((int)((double)this.price*2.5)<=GameCore.getGold()) {
					this.level++;
					this.range += 15;
					this.damage += (int)((double)damage*1.2);
					GameCore.decreaseGold((int)((double)this.price*2.5));
					changeSprite();
					return (int)((double)this.price*2.5);
				}
			}
			
		}
		return 0;
	}
	@Override
	public ImageView drawImageView() {
		tower = new ImageView(RenderableHolder.sniperTowerSprite[level]);
		tower.relocate(x - 12.5, y - 12.5);
		tower.setRotate(angle);
		isDrew = true;
		return tower;
	}
	
	@Override
	public void changeSprite() {
		tower.setImage(RenderableHolder.sniperTowerSprite[level]);
	}
	@Override
	public void move() {
		tower.setLayoutX(x - 12.5);
		tower.setLayoutY(y - 12.5);
		tower.setRotate(angle);
	}

	@Override
	public void destroy() {
		// tower.setVisible(false);
		tower.setImage(null);
		this.destroyed = true;
	}

	@Override
	public ImageView getImageView() {
		return tower;
	}
}
