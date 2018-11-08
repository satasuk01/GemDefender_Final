package logic;

import core.GameCore;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Field implements IRenderable {
	public static int[][] field = new int[30][20]; // one block 25x25px

	public static double getPositionX(int column) {
		return column * 25 + 12.5;
	}

	public static double getPositionY(int row) {
		return row * 25 + 12.5;
	}

	public int getTerrain(int row, int column) {
		if (row > 75 || column > 50 || column < 0 || row < 0) {
			return -1;
		}
		return field[row][column];
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		return -999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		Image img = RenderableHolder.mapSprite;
		gc.drawImage(img, 0, 0);
		// Text that show what wave is spawning right now
		gc.setFont(Font.loadFont(ClassLoader.getSystemResource("SOV_sila.ttf").toString(), 150));
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		//tell what wave is now 
		if (GameLogic.getWait() == true) {
			gc.fillText("WAVE " + GameLogic.getWave(), 210, 270);
			gc.strokeText("WAVE " + GameLogic.getWave(), 210, 270);
		}
		// when click buy tower button ,tower image will follow mouse cursor
		if (GameCore.getBuyStatus() != 0) {
			double mousex = InputUtility.mouseX;
			double mousey = InputUtility.mouseY;
			int row = (int) mousex / 25;
			int column = (int) mousey / 25;
			double x = getPositionX(row);
			double y = getPositionY(column);
			if (GameCore.getBuyStatus() == 1 && mousex < 740 && mousex > 5 && mousey < 490 && mousey > 5) {
				Image image = RenderableHolder.arrowTowerSprite[0];
				gc.drawImage(image, x - 12.5, y - 12.5);
				gc.setLineWidth(1.0);
				gc.strokeArc(x - 60, y - 60, 60 * 2, 60 * 2, 0, 360, ArcType.OPEN);
			}
			if (GameCore.getBuyStatus() == 2 && mousex < 740 && mousex > 5 && mousey < 490 && mousey > 5) {
				Image image = RenderableHolder.iceTowerSprite[0];
				gc.drawImage(image, x - 12.5, y - 12.5);
				gc.setLineWidth(1.0);
				gc.strokeArc(x - 55, y - 55, 55 * 2, 55 * 2, 0, 360, ArcType.OPEN);
			}
			if (GameCore.getBuyStatus() == 3 && mousex < 740 && mousex > 5 && mousey < 490 && mousey > 5) {
				Image image = RenderableHolder.sniperTowerSprite[0];
				gc.drawImage(image, x - 12.5, y - 12.5);
				gc.setLineWidth(1.0);
				gc.strokeArc(x - 100, y - 100, 100 * 2, 100 * 2, 0, 360, ArcType.OPEN);
			}
			if (GameCore.getBuyStatus() == 4 && mousex < 740 && mousex > 5 && mousey < 490 && mousey > 5) {
				Image image = RenderableHolder.fireTowerSprite[0];
				gc.drawImage(image, x - 12.5, y - 12.5);
				gc.setLineWidth(1.0);
				gc.strokeArc(x - 65, y - 65, 65 * 2, 65 * 2, 0, 360, ArcType.OPEN);
			}
		}
	}

}
