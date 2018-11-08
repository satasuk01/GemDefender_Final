package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import enemies.Enemy;
import logic.Spawner.Pair;
import sharedObject.RenderableHolder;

//TODO make it private and create a singleton pattern(create one instance)
public class GameLogic {
	public static List<Entity> gameObjectContainer;
	private Spawner spawner;
	private Gem gem;
	public static boolean wait = false, end = false;
	public static int wave;

	public GameLogic() {
		GameLogic.gameObjectContainer = new ArrayList<Entity>();
		Field field = new Field();
		RenderableHolder.getInstance().add(field);
		gem = new Gem(19, 27);
		addNewObject(gem);
		spawner = new Spawner(0, 3);
		addNewObject(spawner);
	}

	private void removeDestroyed() {
		for (int i = gameObjectContainer.size() - 1; i >= 0; i--) {

			if (gameObjectContainer.get(i).isDestroyed()) {
				gameObjectContainer.remove(i);
			}

		}
	}

	protected void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}

	public static List<Enemy> getEnemy() {
		List<Enemy> enemyList = new ArrayList<Enemy>();
		for (Entity entity : gameObjectContainer) {
			if (entity instanceof Enemy && entity.isDestroyed() == false) {
				enemyList.add((Enemy) entity);
			}
		}
		return enemyList;
	}

	public static List<Tower> getTower() {
		List<Tower> towerList = new ArrayList<Tower>();
		for (Entity entity : gameObjectContainer) {
			if (entity instanceof Tower && entity.isDestroyed() == false) {
				towerList.add((Tower) entity);
			}
		}
		return towerList;
	}

	public static List<Bullet> getBullet() {
		List<Bullet> bulletList = new ArrayList<Bullet>();
		for (Entity entity : gameObjectContainer) {
			if (entity instanceof Bullet && entity.isDestroyed() == false) {
				bulletList.add((Bullet) entity);
			}
		}
		return bulletList;
	}

	public static boolean getWait() {
		return wait;
	}

	public static int getWave() {
		return wave;
	}

	int counter = 0;
	int top = 150;
	int tick = 0;
	int spawned = 0;
	int enemytype = 0;

	public void logicUpdate() {
		// ----Remove Unused Items
		removeDestroyed();
		top = spawner.getTime();
		List<Pair<Enemy, Integer>> Wave = spawner.WaveList();
		wave = spawner.getWave();
		if (getEnemy().isEmpty())
			wait = true;
		// ----Enemy Update----
		for (Enemy enemy : getEnemy()) {
			enemy.update();
		}
		// ----Tower Update----
		for (Tower tower : getTower()) {
			tower.update();
		}
		for (Bullet bullet : getBullet()) {
			bullet.update();
		}
		if (counter > top && enemytype < Wave.size()) {
			Enemy enemy = Wave.get(enemytype).getFirst();
			int amount = Wave.get(enemytype).getSecond();
			addNewObject(enemy);
			spawned++;
			if (spawned == amount) {
				spawned = 0;
				enemytype++;
			}
			counter = 0;
		}
		if (enemytype == Wave.size() && spawner.update()) {
			enemytype = 0;
		}
		counter++;
		tick++;
		gem.update();
		if (gem.getHp() <= 0) {
			end = true;
		}
	}
}