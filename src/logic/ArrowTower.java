package logic;

import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import enemies.Enemy;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.shape.ArcType;
import sharedObject.RenderableHolder;

public class ArrowTower extends Tower {


	public ArrowTower(int row, int column) {
		this.price = 50;
		this.damage = 12;
		this.splashAttack = false;
		this.range = 60;
		this.fireRate = 20;
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
			Arrow arrow = new Arrow(x, y, lockedEnemy,this.damage);
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
	public ImageView drawImageView() {
		tower = new ImageView(RenderableHolder.arrowTowerSprite[level]);
		tower.relocate(x - 12.5, y - 12.5);
		tower.setRotate(angle);
		isDrew = true;
		return tower;
	}
	
	@Override
	public void changeSprite() {
		tower.setImage(RenderableHolder.arrowTowerSprite[level]);
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
