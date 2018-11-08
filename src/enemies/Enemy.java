package enemies;

import core.GameCore;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.CollidableEntity;
import logic.Field;
import sharedObject.RenderableHolder;

public abstract class Enemy extends CollidableEntity {
	protected int maxHp;
	protected int hp;
	protected double normalSpeed;
	protected double speed;
	protected boolean freezed = false,goldAdded = false,up = false;
	protected int freezedDuration = 0;
	protected int stage = 1;
	protected int price ,score ;
	public void move() {
		//Stage1
		if(stage == 1) {
			up =false ;
			this.y += speed;
			if(y>Field.getPositionY(17)) {
				this.y = Field.getPositionY(17);
				this.x = Field.getPositionX(3);
				stage = 2;
			}
		}
		
		if(stage == 2) {
			up = false ;
			this.x += speed;
			if(x>Field.getPositionX(6)) {
				this.y = Field.getPositionY(17);
				this.x = Field.getPositionX(6);
				stage = 3;
			}
		}
		
		if(stage ==3) {
			up = true;
			this.y -=speed;
			if(y<Field.getPositionY(3)) {
				this.y = Field.getPositionY(3);
				this.x = Field.getPositionX(6);
				stage = 4;
			}
		}
		
		if(stage == 4) {
			up = false; 
			this.x += speed;
			if(x>Field.getPositionX(9)){
				this.x = Field.getPositionX(9);
				this.y = Field.getPositionY(3);
				stage = 5;
			}
		}
		
		if(stage == 5) {
			this.y += speed;
			if(y>Field.getPositionY(16)){
				this.x = Field.getPositionX(9);
				this.y = Field.getPositionY(16);
				stage = 6;
			}
		}
		if(stage == 6) {
			this.x += speed;
			if(x>Field.getPositionX(13)){
				this.x = Field.getPositionX(13);
				this.y = Field.getPositionY(16);
				stage = 7;
			}
		}
		if(stage == 7) {
			this.y += speed;
			if(y>Field.getPositionY(18)){
				this.x = Field.getPositionX(13);
				this.y = Field.getPositionY(18);
				stage = 8;
			}
		}
		if(stage == 8) {
			this.x += speed;
			if(x>Field.getPositionX(15)){
				this.x = Field.getPositionX(15);
				this.y = Field.getPositionY(18);
				stage = 9;
			}
		}
		if(stage == 9) {
			up = true;
			this.y -= speed;
			if(y<Field.getPositionY(1)){
				this.x = Field.getPositionX(15);
				this.y = Field.getPositionY(1);
				stage = 10;
			}
		}
		if(stage == 10) {
			up = false;
			this.x += speed;
			if(x>Field.getPositionY(19)){
				this.x = Field.getPositionX(19);
				this.y = Field.getPositionY(1);
				stage = 11;
			}
		}
		if(stage == 11) {
			this.y += speed;
			if(y>Field.getPositionY(17)){
				this.x = Field.getPositionX(19);
				this.y = Field.getPositionY(17);
				stage = 12;
			}
		}
		if(stage == 12) {
			this.x += speed;
			if(x>Field.getPositionY(23)){
				this.x = Field.getPositionX(23);
				this.y = Field.getPositionY(17);
				stage =13;
			}
		}
		if(stage == 13) {
			up=true;
			this.y -= speed;
			if(y<Field.getPositionY(2)){
				this.x = Field.getPositionX(23);
				this.y = Field.getPositionY(2);
				stage = 14;
			}
		}
		if(stage == 14) {
			up = false;
			this.x += speed;
			if(x>Field.getPositionY(27)){
				this.x = Field.getPositionX(27);
				this.y = Field.getPositionY(2);
				stage = 15;
			}
		}
		if(stage == 15) {
			this.y += speed;
			if(y>Field.getPositionY(19)){
				this.x = Field.getPositionX(27);
				this.y = Field.getPositionY(19);
				this.speed = 0;
			}
		}
	}
	public void getHit(int damage) {
		this.hp -= damage;
		if(hp<=0) {
			if(!goldAdded) {
				GameCore.addGold(price);
				GameCore.addScore(score);
				goldAdded = true;
			}
			this.destroyed = true;
		}

	}
	public void freeze(int duration,int level) {
		if(!freezed) {
			speed = normalSpeed/(1.75+level);
			freezed = true;
			freezedDuration=duration;
		}
	}

	public void update() {
		//Decrease freeze time
		if(freezed) {
			freezedDuration--;
			if(freezedDuration<=0) {
				freezed = false;
				speed = normalSpeed;
			}
		}
		//--------------------
		move();
	}
	@Override
	public abstract void draw(GraphicsContext gc);
	
	//---------Getters and Setters--------------

	//------------------------------------------
	
}
