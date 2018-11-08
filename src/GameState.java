import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class GameState  {
	public static final int WIDTH = 800, HEIGH = 600;
	protected ImageView bg1, bg2;
	public GraphicsContext gc;
	public Canvas canvas;
	protected MediaPlayer bgsound;
	protected Scene scene;
	public static Pane root;
	protected FadeTransition ft ;
	public abstract void run();
	public abstract void makeBgmusic();
	
	public void Fadingin() {
		ft = new FadeTransition(Duration.millis(1500), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}

	public void Fadingout() {
		ft = new FadeTransition(Duration.millis(1500), root);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.play();
	}

	
	public ImageView getBg1() {
		return bg1;
	}
	
	public void setBg1(ImageView bg1) {
		this.bg1 = bg1;
	}

	public ImageView getBg2() {
		return bg2;
	}

	public void setBg2(ImageView bg2) {
		this.bg2 = bg2;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeigh() {
		return HEIGH;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public MediaPlayer getBgsound() {
		return bgsound;
	}

	public void setBgsound(MediaPlayer bgsound) {
		this.bgsound = bgsound;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public static Pane getRoot() {
		return root;
	}
}