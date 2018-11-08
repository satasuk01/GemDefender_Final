package logic;

import java.util.List;

import core.GameCore;
import enemies.Enemy;
import javafx.application.Platform;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Tower extends Entity {
	protected int row;
	protected int column;
	protected ImageView tower;
	protected int level,price,radius,range,damage,fireRate,fireCount;
	protected boolean eventAdded,splashAttack,isDrew,isSelected;
	protected double angle;
	protected int inRange(Enemy enemy){
		return (int) Math.hypot(this.x-enemy.x, this.y-enemy.y);
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	protected boolean isInRange(Enemy enemy) {
		return Math.hypot(this.x-enemy.x, this.y-enemy.y) <= this.range+enemy.radius;
	}
	public int getRow() {
		return row;
	}
	public int getRange() {
		return range;
	}
	public int getColumn() {
		return column;
	}
	public boolean isSplash() {
		return splashAttack;
	}
	public int getLevel() {
		return level;
	}
	public boolean isDrew() {
		return isDrew;
	}
	public boolean isEventAdded() {
		return eventAdded;
	}
	public int getPrice() {
		return price;
	}
	public double getFireRate() {
		return Math.round(((1/(double)fireRate)) * 1000.0) / 100.0;
		
	}
	public int getDamage() {
		return damage;
	}
	public int getUpgradePrice() {
		if(this.level==0) {
			return (int)((double)this.price*1.5);
		}if(this.level == 1) {
			return (int)((double)this.price*2.5);
		}else {
			return 0;
		}
	}
	public int upgrade() { //level 0 price*1.5 level 1 price*2.5
		if(this.level>=2) {
			return 0;
		}else {
			if(this.level ==0) {
				if((int)((double)this.price*1.5)<=GameCore.getGold()) {
					this.level++;
					this.damage += (int)((double)damage*1.5);
					GameCore.decreaseGold((int)((double)this.price*1.5));
					changeSprite();
					return (int)((double)this.price*2.5);
				}
			}
			if(this.level ==1) {
				if((int)((double)this.price*2.5)<=GameCore.getGold()) {
					this.level++;
					this.damage += (int)((double)damage*1.2);
					GameCore.decreaseGold((int)((double)this.price*2.5));
					changeSprite();
					return (int)((double)this.price*2.5);
				}
			}
			
		}
		return 0;
	}
	public void deSelected() {
		List<Tower> towerList =  GameLogic.getTower(); 
		for(Tower t:towerList) {
			t.setSelected(false);
			t.getImageView().setEffect(null);
		}
	}
	
	
	public void selected() {
		deSelected();
		setSelected(true);
		if (isSelected == true) {
			
			Lighting lighting = new Lighting();
			lighting.setDiffuseConstant(1.0);
			lighting.setSpecularConstant(0.0);
			lighting.setSpecularExponent(0.0);
			lighting.setSurfaceScale(0.0);
			lighting.setLight(new Light.Distant(90, 90, Color.BLUE));
			tower.setEffect(lighting);
			
		} else
			tower.setEffect(null);
			
	}

	public abstract void update();
	public abstract ImageView drawImageView();
	public abstract void move();
	public abstract void destroy();
	public abstract ImageView getImageView();
	public abstract void changeSprite();
	
}
