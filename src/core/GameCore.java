package core;

import logic.ArrowTower;
import logic.FireTower;
import logic.GameLogic;
import logic.IceTower;
import logic.SniperTower;
import logic.Tower;
import sharedObject.RenderableHolder;

public class GameCore { // Maybe make it as a singleton
	private static int gold = 999450;
	private static int score = 0;
	private static Tower selectedTower = null;
	private static int buyStatus = 0; // 0 is nothing, 1 is an ArrowTower, 2 is an IceTower
	private static int arrowTowerPrice = 50, iceTowerPrice = 80, sniperTowerPrice = 100, fireTowerPrice = 80;
	private static boolean canPlace;

	public static int getScore() {
		return score;
	}

	public static void addScore(int sc) {
		score += sc;
	}

	public static int getGold() {
		return gold;
	}

	public static void addGold(int amount) {
		gold += amount;
	}

	public static void decreaseGold(int amount) {
		gold -= amount;
		if (gold < 0)
			gold = 0;
	}

	public static void setSelectedTower(Tower tower) {

		tower.setSelected(true);
		tower.selected();
		selectedTower = tower;
	}

	public static Tower getSelectedTower() {
		return selectedTower;
	}

	public static void upgradeTower() {
		if (selectedTower != null) {
			selectedTower.upgrade();
		}
	}

	public static void sellTower() {
		if (selectedTower != null && selectedTower.isSelected() == true) {
			addGold(selectedTower.getPrice() / 2);
			selectedTower.destroy();
			selectedTower = null;
		}
	}

	public static void setBuyStatus(int number) {

		if (number == 1) {
			if (gold >= arrowTowerPrice) {
				buyStatus = 1;
				return;
			}
			buyStatus = 0;
			return;
		}
		if (number == 2) {
			if (gold >= iceTowerPrice) {
				buyStatus = 2;
				return;
			}
			buyStatus = 0;
			return;
		}

		if (number == 3) {
			if (gold >= sniperTowerPrice) {
				buyStatus = 3;
				return;
			}
			buyStatus = 0;
			return;
		}

		if (number == 4) {
			if (gold >= fireTowerPrice) {
				buyStatus = 4;
				return;
			}
			buyStatus = 0;
			return;
		}
		buyStatus = number; // TODO set If the gold is enough to buy
	}

	public static int getBuyStatus() {
		return buyStatus;
	}

	public static void placeTower(int row, int column) {
		// -----------------------------------------------
		if (checkCanplace(row , column)) {
			if (buyStatus == 1) {
				GameCore.decreaseGold(arrowTowerPrice);
				ArrowTower arrowTower = new ArrowTower(row, column);
				GameLogic.gameObjectContainer.add(arrowTower);
				RenderableHolder.getInstance().add(arrowTower);
			}
			if (buyStatus == 3) {
				GameCore.decreaseGold(sniperTowerPrice);
				SniperTower snip = new SniperTower(row, column);
				GameLogic.gameObjectContainer.add(snip);
				RenderableHolder.getInstance().add(snip);
			}
			if (buyStatus == 2) {
				GameCore.decreaseGold(iceTowerPrice);
				IceTower iceTower = new IceTower(row, column);
				GameLogic.gameObjectContainer.add(iceTower);
				RenderableHolder.getInstance().add(iceTower);
			}
			if (buyStatus == 4) {
				GameCore.decreaseGold(fireTowerPrice);
				FireTower fire = new FireTower(row, column);
				GameLogic.gameObjectContainer.add(fire);
				RenderableHolder.getInstance().add(fire);
			}
		} 
		
		buyStatus = 0;
	}

	public static boolean checkCanplace(int row, int column) {
		canPlace  =true ;
		if (buyStatus != 0) {
			for (Tower tower : GameLogic.getTower()) {
				if (tower.getRow() == row && tower.getColumn() == column) {
					canPlace = false;
				}
			}
			if (row == 20 || column == 30) {
				canPlace = false;
			}
			// TODO Don't let player place the tower on the path
			for (int rowc = 0; rowc < 18; rowc++) {
				if (row == rowc && column == 3) {
					canPlace = false;
				}
			}
			for (int columnc = 3; columnc < 7; columnc++) {
				if (column == columnc && row == 17) {
					canPlace = false;
				}
			}
			for (int rowc = 17; rowc > 2; rowc--) {
				if (row == rowc && column == 6) {
					canPlace = false;
				}
			}
			for (int columnc = 6; columnc < 10; columnc++) {
				if (column == columnc && row == 3) {
					canPlace = false;
				}
			}
			for (int rowc = 3; rowc < 17; rowc++) {// 5
				if (row == rowc && column == 9) {
					canPlace = false;
				}
			}
			for (int columnc = 9; columnc < 14; columnc++) {
				if (column == columnc && row == 16) {
					canPlace = false;
				}
			}
			for (int rowc = 16; rowc < 19; rowc++) {
				if (row == rowc && column == 13) {
					canPlace = false;
				}
			}
			for (int columnc = 13; columnc < 16; columnc++) {
				if (column == columnc && row == 18) {
					canPlace = false;
				}
			}
			for (int rowc = 18; rowc > 0; rowc--) {
				if (row == rowc && column == 15) {
					canPlace = false;
				}
			}
			for (int columnc = 15; columnc < 20; columnc++) {
				if (column == columnc && row == 1) {
					canPlace = false;
				}
			}
			for (int rowc = 1; rowc < 18; rowc++) {
				if (row == rowc && column == 19) {
					canPlace = false;
				}
			}
			for (int columnc = 19; columnc < 24; columnc++) {
				if (column == columnc && row == 17) {
					canPlace = false;
				}
			}
			for (int rowc = 17; rowc > 1; rowc--) {
				if (row == rowc && column == 23) {
					canPlace = false;
				}
			}
			for (int columnc = 23; columnc < 28; columnc++) {
				if (column == columnc && row == 2) {
					canPlace = false;
				}
			}
			for (int rowc = 2; rowc < 31; rowc++) {
				if (row == rowc && column == 27) {
					canPlace = false;
				}
			}
		}
		return canPlace;
	}
}