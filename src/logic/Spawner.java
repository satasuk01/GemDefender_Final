package logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import enemies.Cave;
import enemies.Crap;
import enemies.Enemy;
import enemies.Rock;
import enemies.Slime;
import enemies.Worm;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import sharedObject.RenderableHolder;

public class Spawner extends Entity {
	int radius;
	private int wave = 1;
	private int time;

	public Spawner(int row, int column) {
		super();
		this.radius = 12;
		this.x = Field.getPositionX(column);
		this.y = Field.getPositionY(row);
		this.z = -100;
		this.wave = 1;
		this.time = 150;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(new Image(ClassLoader.getSystemResource("spawner.png").toString()), x - 25, y - 25, 50, 50);
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void nextWave() {
		wave += 1;

	}

	public int getWave() {
		return wave;
	}

	public boolean update() {
		if (GameLogic.getEnemy().isEmpty()) {
			nextWave();
			return true;
		}
		return false;
	}

	public List<Pair<Enemy, Integer>> WaveList() {
		List<Pair<Enemy, Integer>> Wavelist = new LinkedList<Pair<Enemy, Integer>>();
		// set how fast enemy gonna spawn
		setTime(20);
		// for many type of enemy,first in pair is enemy type second is amount of enemy
		// example 7 type of enemy
		if(wave%7==0&&wave%5!=0) {
			Wavelist.add(new Pair<Enemy, Integer>(new Slime(160+(int) (wave*5)), 3 + (int) Math.floor((double) wave*1.2)));
		}
		if(wave%5==0) {
			Wavelist.add(new Pair<Enemy, Integer>(new Worm(75+(int) (wave*5)), (int) (1.0 * (double) wave)));
			Wavelist.add(new Pair<Enemy, Integer>(new Slime(100+(int) (wave*5)), 3 + (int) Math.floor((double) wave / 2.0)));
			Wavelist.add(new Pair<Enemy, Integer>(new Crap(220+(int) (wave*10)), 1+(int) Math.floor((double) wave / 3.0)));
			Wavelist.add(new Pair<Enemy, Integer>(new Cave(300+(int) (wave*15)), 1+(int) Math.floor((double) wave / 4.0)));
			Wavelist.add(new Pair<Enemy, Integer>(new Rock(450+(int) (wave*wave*5.5)), 1 + (int) Math.floor((double) wave / 5.0)));
			Wavelist.add(new Pair<Enemy, Integer>(new Worm(75+(int) (wave*5)), (int) (0.5 * (double) wave)));
			Wavelist.add(new Pair<Enemy, Integer>(new Slime(100+(int) (wave*5)), 3 + (int) Math.floor((double) wave / 4.0)));
			Wavelist.add(new Pair<Enemy, Integer>(new Crap(220+(int) (wave*10)), 1+(int) Math.floor((double) wave / 8.0)));
			Wavelist.add(new Pair<Enemy, Integer>(new Cave(300+(int) (wave*15)), 1+(int) Math.floor((double) wave / 8.0)));
			Wavelist.add(new Pair<Enemy, Integer>(new Rock(450+(int) (wave*wave*5.5)), 1 + (int) Math.floor((double) wave / 10.0)));
		}else {
			Wavelist.add(new Pair<Enemy, Integer>(new Worm(75+(int) (wave*7)), (int) (1.0 * (double) wave)));
			Wavelist.add(new Pair<Enemy, Integer>(new Slime(100+(int) (wave*8)), 3 + (int) Math.floor((double) wave / 2.0)));
			Wavelist.add(new Pair<Enemy, Integer>(new Crap(220+(int) (wave*11)), 1+(int) Math.floor((double) wave / 3.0)));
			Wavelist.add(new Pair<Enemy, Integer>(new Cave(300+(int) (wave*wave*2)), 1+(int) Math.floor((double) wave / 4.0)));
			Wavelist.add(new Pair<Enemy, Integer>(new Rock(450+(int) (wave*wave*5.5)), 1 + (int) Math.floor((double) wave / 5.0)));
		}
		return Wavelist;
	}

	// want to use pair class
	public class Pair<A, B> {
		private A first;
		private B second;

		public Pair(A first, B second) {
			super();
			this.first = first;
			this.second = second;
		}

		public A getFirst() {
			return first;
		}

		public B getSecond() {
			return second;
		}
	}

}
