package enemies;

import core.GameCore;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Field;
import sharedObject.RenderableHolder;

public class Crap extends Enemy {

	public Crap(int maxhp) { // speed = 1.5 hp=100
		this.normalSpeed = 1.2;
		speed = normalSpeed;
		this.maxHp = maxhp;
		hp = maxHp;
		this.radius = 20;
		this.x = Field.getPositionX(3);
		this.y = Field.getPositionY(0);
		this.z = 0;
		this.price = 5;
		this.score = 1;
	}

	private int animationFrame = 0;

	@Override
	public void draw(GraphicsContext gc) {
		if (hp > 0 && hp != this.maxHp) {
			gc.setFill(Color.rgb((int) (255 * (1 - (double) (hp) / (double) (maxHp))),
					(int) (255 * (double) (hp) / (double) (maxHp)), 0));
			gc.fillRect(x - 12.5, y - 16, 25 * (double) (hp) / (double) (maxHp), 3);
		}
		// -------------------------
		// ---Draw Image----
		if (up == false) {
			if (animationFrame % 90 < 11.25) {
				gc.drawImage(RenderableHolder.crapSprite[0], this.x - 20, this.y - 20, 40, 40);
			} else if (animationFrame % 90 < 22.5 && animationFrame % 90 >= 30) {
				gc.drawImage(RenderableHolder.crapSprite[1], this.x - 20, this.y - 20, 40, 40);
			} else if (animationFrame % 90 < 33.75 && animationFrame % 90 >= 30) {
				gc.drawImage(RenderableHolder.crapSprite[2], this.x - 20, this.y - 20, 40, 40);
			} else if (animationFrame % 90 < 45.00 && animationFrame % 90 >= 30) {
				gc.drawImage(RenderableHolder.crapSprite[3], this.x - 20, this.y - 20, 40, 40);
			} else if (animationFrame % 90 < 56.25 && animationFrame % 90 >= 30) {
				gc.drawImage(RenderableHolder.crapSprite[4], this.x - 20, this.y - 20, 40, 40);
			} else if (animationFrame % 90 < 67.5 && animationFrame % 90 >= 30) {
				gc.drawImage(RenderableHolder.crapSprite[5], this.x - 20, this.y - 20, 40, 40);
			} else if (animationFrame % 90 < 78.75 && animationFrame % 90 >= 30) {
				gc.drawImage(RenderableHolder.crapSprite[6], this.x - 20, this.y - 20, 40, 40);
			} else {
				gc.drawImage(RenderableHolder.crapSprite[7], this.x - 20, this.y - 20, 40, 40);
			}

		} else {
			if (animationFrame % 90 < 15) {
				gc.drawImage(RenderableHolder.crapSprite[8], this.x - 20, this.y - 20, 40, 40);
			} else if (animationFrame % 90 < 30 && animationFrame % 90 >= 30) {
				gc.drawImage(RenderableHolder.crapSprite[9], this.x - 20, this.y - 20, 40, 40);
			} else if (animationFrame % 90 < 45 && animationFrame % 90 >= 30) {
				gc.drawImage(RenderableHolder.crapSprite[10], this.x - 20, this.y - 20, 40, 40);
			} else if (animationFrame % 90 < 60 && animationFrame % 90 >= 30) {
				gc.drawImage(RenderableHolder.crapSprite[11], this.x - 20, this.y - 20, 40, 40);
			}else if (animationFrame % 90 < 75 && animationFrame % 90 >= 30) {
				gc.drawImage(RenderableHolder.crapSprite[12], this.x - 20, this.y - 20, 40, 40);
			}else {
				gc.drawImage(RenderableHolder.crapSprite[13], this.x - 20, this.y - 20, 40, 40);
			}

		}
		animationFrame++;

		// ------------
	}
}

// ---------Getters and Setters--------------

// ------------------------------------------
