
import java.util.Timer;
import java.util.TimerTask;

import core.GameCore;
import drawing.GameScreen;
import drawing.PaneForRenderImageViews;
import enemies.Enemy;
import input.InputUtility;
/*import input.InputUtility;*/
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GameLogic;
import menu.BottomMenu;
import menu.TowerMenu;
import sharedObject.RenderableHolder;

public class Game extends GameState {
	public Thread wait;
	private GameScreen gameScreen;
	private PaneForRenderImageViews pr;
	private GameLogic logic;
	public boolean pause;
	private Rectangle pauserec,endrec;
	private StackPane root2;
	private Pane pausepane,endpane;

	public Game() {
		// root and scene is from game state class
		// ---Base---
		root = new GridPane();
		root.setPrefWidth(WIDTH);
		root.setMaxWidth(WIDTH);
		root.setMinWidth(WIDTH);
		root.setPrefHeight(HEIGH);
		root.setMaxHeight(HEIGH);
		root.setMinHeight(HEIGH);
		
		GridPane topbase = new GridPane();
		GridPane bottombase = new GridPane();
		((GridPane) root).add(bottombase, 0, 1);
		((GridPane) root).add(topbase, 0, 0);
		// ---Tower Select Menu
		TowerMenu towerMenu = new TowerMenu(50, 500);
		topbase.add(towerMenu, 1, 0);
		// ---Bottom Menu
		BottomMenu bottomMenu = new BottomMenu(800, 100);
		bottombase.add(bottomMenu, 0, 0);
		// ---MainGame:Development stage----
		root2 = new StackPane();

		logic = new GameLogic();
		gameScreen = new GameScreen(750, 500);
		pr = new PaneForRenderImageViews(750, 500);
		InputUtility.mouseEventOnField(pr);
		// use make pause and end game method to initialize pause 
		makePause();
		makeEnd();
		// add rectangle for pause button
		pauserec = new Rectangle(687, 505, 70, 23);
		pauserec.setFill(Color.TRANSPARENT);
		pauserec.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				pause = !pause;
			}
			
		});
		pr.getChildren().add(pauserec);
		root2.getChildren().add(gameScreen);
		root2.getChildren().add(pr);
		root2.getChildren().add(endpane);
		root2.getChildren().add(pausepane);
		
		// gameScreen.requestFocus();

		topbase.add(root2, 0, 0);
		// ------------------------
		// add back ground music
		makeBgmusic();
		bgsound.play();
		// set fading effect
		Fadingin();
		// ---Add to base

		scene = new Scene(root);

	}

	// animation timer will only run in gem defender class so we can have various
	// type of game state
	@Override
	public void run() {
		gameScreen.paintComponent();
		wait = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (this) {
					try {
						if (GameLogic.getWait()) {
							Thread.sleep(1000);
							GameLogic.wait = false;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					}
				}
			}
		});
		if (GameLogic.end == false && pause == false) {
			pr.paintImageView();
			RenderableHolder.getInstance().update();
			logic.logicUpdate();
			wait.start();
		}
		if (GameLogic.end == true&&pause == false ) {
			gameScreen.paintComponent();
			pause = true;
			bgsound.stop();
			bgsound = new MediaPlayer(new Media(ClassLoader.getSystemResource("endgamemusic.mp3").toString()));
			bgsound.setOnEndOfMedia(new Runnable() {
				public void run() {
					bgsound.seek(Duration.seconds(0));
				}
			});
			bgsound.play();
			gameScreen.gc.setFill(Color.RED);
			gameScreen.gc.fillText("WAVE " + GameLogic.getWave(), 210, 270);
			gameScreen.gc.strokeText("WAVE " + GameLogic.getWave(), 210, 270);
			gameScreen.gc.setFont(Font.loadFont(ClassLoader.getSystemResource("SOV_sila.ttf").toString(), 150));
			gameScreen.gc.fillText("DEFEATED" , 110, 200);
			gameScreen.gc.strokeText("DEFEATED"  , 110, 200);
			gameScreen.gc.setFont(Font.loadFont(ClassLoader.getSystemResource("SOV_sila.ttf").toString(), 80));
			gameScreen.gc.fillText("Score : "+GameCore.getScore() , 250, 310);
			gameScreen.gc.strokeText("Score : "+ GameCore.getScore()  , 250, 310);
			gameScreen.gc.fillText("Menu" , 300, 350);
			gameScreen.gc.strokeText("Menu"  , 300, 350);
			//---Clear enemy---
			gameScreen.clear();
			endpane.setVisible(true);
			//-----------------
			
		}
		if (pause == true&&GameLogic.end == false ) {
			pauserec.setVisible(false);
			pausepane.setVisible(true);
		}
		// InputUtility.updateInputState();
	}

	@Override
	public void makeBgmusic() {
		bgsound = new MediaPlayer(new Media(ClassLoader.getSystemResource("gamebgmusic.mp3").toString()));
		bgsound.setOnEndOfMedia(new Runnable() {
			public void run() {
				bgsound.seek(Duration.seconds(0));
			}
		});
		bgsound.play();
	} 

	public void makePause() {
		pausepane = new Pane();
		ImageView pausemenu = new ImageView(new Image(ClassLoader.getSystemResource("pause1.png").toString()));
		pausemenu.setLayoutX(300);
		pausemenu.setLayoutY(150);
		pausemenu.setFitWidth(146);
		pausemenu.setFitHeight(183);
		ImageView menubutton = new ImageView(new Image(ClassLoader.getSystemResource("pausemenu.png").toString()));
		menubutton.setLayoutX(318);
		menubutton.setLayoutY(220);
		menubutton.setFitWidth(110);
		menubutton.setFitHeight(35);
		menubutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				bgsound.stop();
				gameScreen.clear();
				GemDefender.gs = new Menu();
			}
		});
		ImageView resumebutton = new ImageView(new Image(ClassLoader.getSystemResource("resume.png").toString()));
		resumebutton.setLayoutX(335);
		resumebutton.setLayoutY(283);
		resumebutton.setFitWidth(77);
		resumebutton.setFitHeight(35);
		resumebutton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				pausepane.setVisible(false);
				pause = false;
				pauserec.setVisible(true);
			}

		});
		pausepane.getChildren().addAll(pausemenu,menubutton,resumebutton);
		pausepane.setVisible(false);
	}
	public void makeEnd() {
		endpane = new Pane();
		endrec = new Rectangle(305,315,122,35);
		endrec.setOpacity(0.0);
		endpane.getChildren().add(endrec);
		endrec.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				gameScreen.gc.setFill(Color.WHITE);
				gameScreen.gc.fillText("Menu" , 300, 350);
				gameScreen.gc.strokeText("Menu"  , 300, 350);
			}
		});
		endrec.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				gameScreen.gc.setFill(Color.RED);
				gameScreen.gc.fillText("Menu" , 300, 350);
				gameScreen.gc.strokeText("Menu"  , 300, 350);
			}
		});
		endrec.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				bgsound.stop();
				GemDefender.gs = new Menu();
				}
		});
		endpane.setVisible(false);
	}

}
