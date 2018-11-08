import java.util.Timer;
import java.util.TimerTask;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import java.nio.file.Paths;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu extends GameState {
	private boolean buttonclick = false;

	public Menu() {
		root = new Pane();
		Image image = new Image(ClassLoader.getSystemResource("menubg.png").toString());
		bg1 = new ImageView(image);
		bg2 = new ImageView(image);
		bg1.setFitHeight(HEIGH);
		bg2.setFitHeight(HEIGH);
		bg2.setFitWidth(WIDTH);
		bg1.setFitWidth(WIDTH);
		bg2.setLayoutX(WIDTH);
		canvas = new Canvas(WIDTH, HEIGH);
		gc = canvas.getGraphicsContext2D();
		makeBgmusic();
		creatBg();
		draw();
		
		scene = new Scene(root);
	}

	public void creatBg() {
		root.getChildren().add(getBg1());
		root.getChildren().add(getBg2());
		root.getChildren().add(canvas);
	}

	public void draw() {
		// draw all text
		gc.setFont(Font.loadFont(ClassLoader.getSystemResource("SOV_sila.ttf").toString(), 100));
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		gc.fillText("Tower Defend", WIDTH / 2 - 225, 150);
		gc.strokeText("Tower Defend", WIDTH / 2 - 225, 150);

		gc.setFill(Color.ORANGE);
		gc.setStroke(Color.BLACK);
		gc.setFont(Font.loadFont(ClassLoader.getSystemResource("SOV_sila.ttf").toString(), 48));

		gc.fillText("Start", WIDTH / 2 - 50, 300);
		gc.strokeText("Start", WIDTH / 2 - 50, 300);
		addHitbox(root, gc, "Start", WIDTH / 2 - 50, 300);

		gc.fillText("Help", WIDTH / 2 - 50, 350);
		gc.strokeText("Help", WIDTH / 2 - 50, 350);
		addHitbox(root, gc, "Help", WIDTH / 2 - 50, 350);

		gc.fillText("Exit", WIDTH / 2 - 50, 400);
		gc.strokeText("Exit", WIDTH / 2 - 50, 400);
		addHitbox(root, gc, "Exit", WIDTH / 2 - 50, 400);

	}

	protected void addHitbox(Pane root, GraphicsContext gc, String string, int i, int j) {
		// set each text hit box when it pressed and to change color when it hovered
		FontLoader fl = Toolkit.getToolkit().getFontLoader();
		double fw = fl.computeStringWidth(string, gc.getFont());
		double fh = fl.getFontMetrics(gc.getFont()).getLineHeight();
		Rectangle rec = new Rectangle(i, j - 22, fw, fh - 40);
		rec.setOpacity(0.0);
		rec.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				gc.setFill(Color.WHITE);
				gc.fillText(string, i, j);
				gc.strokeText(string, i, j);
			}
		});
		rec.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				gc.setFill(Color.ORANGE);
				gc.fillText(string, i, j);
				gc.strokeText(string, i, j);
			}
		});
		rec.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				if (string == "Start") {
					if (buttonclick == false) {
						buttonclick = true;
						Fadingout();
						new Timer().schedule(new TimerTask() {
							public void run() {
								bgsound.stop();
								goGameScene();
							}
						}, 1500); 
					}
				}
				if (string == "Help") {
					Pane help = new Pane();
					Canvas canvas = new Canvas(WIDTH,HEIGH);
					GraphicsContext gc = canvas.getGraphicsContext2D();
					help.getChildren().add(canvas);
					gc.setFill(Color.WHITE);
					gc.setFont(Font.loadFont(ClassLoader.getSystemResource("SOV_sila.ttf").toString(), 48));
					gc.drawImage(new Image(ClassLoader.getSystemResource("helpmenu.png").toString()), 200, 80,400,400);
					gc.fillText("Don't Let These Enemy", 220, 150);
					gc.fillText("Go To Your Gems", 255, 260);
					gc.fillText("By Placing Tower", 250, 300);
					gc.drawImage(new Image(ClassLoader.getSystemResource("cavemove_01.png").toString()), 220, 155,68,80);
					gc.drawImage(new Image(ClassLoader.getSystemResource("crapmove_01.png").toString()), 290, 155,83,60);
					gc.drawImage(new Image(ClassLoader.getSystemResource("rockmove_01.png").toString()), 390, 155,43,80);
					gc.drawImage(new Image(ClassLoader.getSystemResource("slime1.png").toString()), 450, 165,50,50);
					gc.drawImage(new Image(ClassLoader.getSystemResource("wormmove_01.png").toString()), 500, 155,87,74);
					gc.drawImage(new Image(ClassLoader.getSystemResource("gems.png").toString()), 320, 320,159,154);
					root.getChildren().add(help);
					help.setOnMouseClicked(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent t) {
							root.getChildren().remove(help);
						}
					});
				}
				if (string == "Exit") {
					// delay when click exist buttom for 2 second
					if (buttonclick == false) {
						buttonclick = true;
						Fadingout();
						new Timer().schedule(new TimerTask() {
							public void run() {
								System.exit(0);
								// Platform.exit();
							}
						}, 1500);
					}
				}
			}

		});

		root.getChildren().add(rec);
	}

	public void run() {
		bgrun();
	}

	public void goGameScene() {
		// change game scene in gemdefender class to game
		GemDefender.gs = new Game();
	}

	public void bgrun() {
		// make background loop
		double x1 = bg1.getLayoutX() - 1;
		double x2 = bg2.getLayoutX() - 1;
		bg1.setLayoutX(x1);
		if (bg1.getLayoutX() < 1 || bg2.getLayoutX() < 3)
			bg2.setLayoutX(x2);
		if (bg2.getLayoutX() == 1)
			bg1.setLayoutX(WIDTH);
		if (bg1.getLayoutX() == 0)
			bg2.setLayoutX(WIDTH);
	}

	@Override
	public void makeBgmusic() {
		// TODO Auto-generated method stub
		bgsound = new MediaPlayer(new Media(ClassLoader.getSystemResource("bgmusic.mp3").toString()));
		bgsound.setOnEndOfMedia(new Runnable() {
			public void run() {
				bgsound.seek(Duration.seconds(1));
			}
		});
		bgsound.play();
	}

}
