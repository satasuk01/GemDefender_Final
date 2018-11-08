package menu;

import core.GameCore;
import input.InputUtility;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.ArrowTower;
import logic.FireTower;
import logic.GameLogic;
import logic.IceTower;
import logic.SniperTower;
import logic.Tower;
import sharedObject.RenderableHolder;



public class BottomMenu extends StackPane { //add GridPane or maybe use stackpane with imageview as button
	
	private  GraphicsContext gcStatus;
	private Thread animationThread;
	private boolean buttonAdded;
	private ImageView upgradeButton;
	private ImageView sellButton;
	public BottomMenu(double width,double height) {
		super();
		this.setPrefHeight(height);
		this.setPrefWidth(width);
		this.setPrefSize(width, height);
		this.setStyle("-fx-background-color:blue;");
		
		//----Draw Background-----
		Canvas background = new Canvas(width,height);
		this.getChildren().add(background);
		GraphicsContext gc = background.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.bottomMenuSprite, 0, 0);
		//------------------------
		//-----Menu (GridPane)
		GridPane menu = new GridPane();
		
		
		//-----------------
		this.getChildren().add(menu);
		
		//---Draw Status menu
		Canvas status = new Canvas(width,height);
		this.getChildren().add(status);
		gcStatus = status.getGraphicsContext2D();
		//----animation Thread---
		animationThread = new Thread(new Runnable() {
			@Override
			public void run() {
				Runnable draw = new Runnable() {
					@Override
					public void run() {
						drawStatus();
					}
				};
				while (true) {
					
					try {
						Thread.sleep(300);
						Platform.runLater(draw);
					} catch (InterruptedException e) {
						// e.printStackTrace();
						animationThread.interrupt();
						return;
					}
				}
			}

		}, "animation");
		animationThread.start();
		//-------------------------
		
	}
	
	public void drawStatus() {
		gcStatus.clearRect(0, 0, this.getPrefWidth(), this.getPrefHeight());
		//----Score and Gold---
		gcStatus.setFont(Font.loadFont(ClassLoader.getSystemResource("SOV_sila.ttf").toString(), 25));
		gcStatus.setFill(Color.ORANGE);
		gcStatus.setStroke(Color.BLACK);
		gcStatus.strokeText("Score : "+GameCore.getScore(), 675, 60);
		gcStatus.fillText("Score : "+GameCore.getScore(), 675, 60);
		gcStatus.strokeText("Gold : "+GameCore.getGold(), 575, 20);
		gcStatus.fillText("Gold : "+GameCore.getGold(), 575, 20);
		//mouse hover over tower menu button 
		if(InputUtility.hover != 0) {
			if(InputUtility.hover == 1)printStatus(new ArrowTower(100,100)); 
			if(InputUtility.hover == 2)printStatus(new IceTower(100,100)); 
			if(InputUtility.hover == 3)printStatus(new SniperTower(100,100)); 
			if(InputUtility.hover == 4)printStatus(new FireTower(100,100)); 
		}
		
		//---------------------
		//---Tower select----
		if(GameCore.getSelectedTower()!=null&&GameCore.getSelectedTower().isSelected()) {
			
			gcStatus.setStroke(Color.WHITE);
			gcStatus.setFill(Color.WHITE);
			gcStatus.setFont(Font.loadFont(ClassLoader.getSystemResource("neuropol.ttf").toString(), 11));
			Tower selected = GameCore.getSelectedTower();
			printStatus(selected);
			
			gcStatus.strokeText("Sell price : "+selected.getPrice()/2, 150, 20);
			gcStatus.strokeText("Upgrade cost : "+selected.getUpgradePrice(),150,35);
			
			//---Image View---
			if(!buttonAdded) {
				upgradeButton = new ImageView(RenderableHolder.upgradeButton);
				/*upgradeButton.setLayoutX(150);
				upgradeButton.setLayoutY(40);*/
				upgradeButton.setTranslateX(-220);
				upgradeButton.setTranslateY(5);
				this.getChildren().add(upgradeButton);
				InputUtility.setUpgradeButton(upgradeButton);
				
				sellButton = new ImageView(RenderableHolder.sellButton);
				sellButton.setTranslateX(-180);
				sellButton.setTranslateY(5);
				this.getChildren().add(sellButton);
				InputUtility.setSellButton(sellButton);
				buttonAdded = true;
			}
			//----------------
			//TODO add upgrade cost, upgrade button ,fireRate
		}else {
			
			if(buttonAdded) {
				buttonAdded=false;
			}
			this.getChildren().remove(upgradeButton);
			this.getChildren().remove(sellButton);
		}
	}
	public  void printStatus(Tower selected) {
		gcStatus.setStroke(Color.WHITE);
		gcStatus.setFill(Color.WHITE);
		gcStatus.setFont(Font.loadFont(ClassLoader.getSystemResource("neuropol.ttf").toString(), 11));
		gcStatus.strokeText("Damage : "+selected.getDamage(),10,20);	
		gcStatus.strokeText("AoE : "+selected.isSplash(), 10, 35);
		gcStatus.strokeText("Level : "+(selected.getLevel()+1), 10, 50);
		gcStatus.strokeText("Fire rate : "+selected.getFireRate(), 10, 65);
		gcStatus.strokeText("Range : "+selected.getRange(), 10, 80);
	}
}
