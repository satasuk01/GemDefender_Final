package enemies;

import core.GameCore;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Field;
import sharedObject.RenderableHolder;

public class Slime extends Enemy {
	
	
	public Slime(int maxhp) { //speed = 1.5 hp=100
		this.normalSpeed = 1;
		speed = normalSpeed;
		this.maxHp = maxhp;
		hp = maxHp;
		this.radius = 12;
		this.x = Field.getPositionX(3);
		this.y = Field.getPositionY(0);
		this.z = 0;
		this.price = 5;
		this.score = 1;
	}
	
	private int animationFrame=0;
	
	@Override
	public void draw(GraphicsContext gc) {
		if(hp>0&&hp!=this.maxHp) {
			gc.setFill(Color.rgb((int)(255 *(1-(double)(hp)/(double)(maxHp))), (int)(255 *(double)(hp)/(double)(maxHp)), 0));
			gc.fillRect(x-12.5, y-16, 25*(double)(hp)/(double)(maxHp), 3);
		}
		//-------------------------
		//---Draw Image----
		if(animationFrame%90<30) {
			gc.drawImage(RenderableHolder.slimeSprite[0], this.x-12.5, this.y-12.5);
		}else if(animationFrame%90<60 && animationFrame%90>=30) {
			gc.drawImage(RenderableHolder.slimeSprite[1], this.x-12.5, this.y-12.5);
		}else {
			gc.drawImage(RenderableHolder.slimeSprite[2], this.x-12.5, this.y-12.5);
		}
		animationFrame++;
		
		
		//------------
		
	}
	
	//---------Getters and Setters--------------

	//------------------------------------------
	
}