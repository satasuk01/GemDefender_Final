package logic;

import enemies.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Gem extends CollidableEntity{
	private int hp;
	private int maxHP;
	public Gem(int row,int column) {
		super();
		this.radius = 12;
		this.x = Field.getPositionX(column);
		this.y = Field.getPositionY(row);
		this.z = -100;
		this.hp=20;
		this.maxHP =20;
	}
	public void update() {
		for(Enemy enemy: GameLogic.getEnemy()) {
			if(collideWith(enemy)) {
				enemy.destroyed = true;
				this.hp -= 1;
			}
		}
	}
	@Override 
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
		gc.drawImage(new Image(ClassLoader.getSystemResource("gems.png").toString()), x-25, y-25, 50, 50);
		
		//-----HP Bar-----------
		if(hp>0) {

			gc.setFill(Color.rgb((int)(255 *(1-(double)(hp)/(double)(maxHP))), (int)(255 *(double)(hp)/(double)(maxHP)), 0));
			gc.fillRect(x-12.5, y-16, 25*(double)(hp)/(double)(maxHP), 5);

		}
		//-------------------------		
	}
	public int getHp() {
		return hp;
	}
}
