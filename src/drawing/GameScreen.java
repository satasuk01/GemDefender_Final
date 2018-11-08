package drawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.Tower;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameScreen extends Canvas {
	public GraphicsContext gc ;
	public GameScreen(double width, double height) {
		super(width, height);
		gc = this.getGraphicsContext2D();
		this.setVisible(true);
		// addListerner();
	}
	public void paintComponent() {
		gc.setFill(Color.BLACK);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}

	}
	public void clear() {
		RenderableHolder.getInstance().getEntities().clear();
	}
}
