package menu;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import sharedObject.RenderableHolder;

public class TowerMenu extends Pane { //Add VBox or maybe use stackpane with imageview as button
	public TowerMenu(int width,int height) {
		super();
		this.setPrefSize(width, height);
		this.setStyle("-fx-background-color:Black;");
		
		//----Draw Background-----
		Canvas background = new Canvas(width,height);
		this.getChildren().add(background);
		GraphicsContext gc = background.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.towerMenuSprite, 0, 0);
		//------------------------
		
		//----menu----
		ImageView tower1 = new ImageView(RenderableHolder.arrowButton); tower1.setFitWidth(35); tower1.setFitHeight(35);
		tower1.setLayoutX(7); tower1.setLayoutY(40);
		ImageView tower2 = new ImageView(RenderableHolder.iceButton); tower2.setFitWidth(35); tower2.setFitHeight(35);
		tower2.setLayoutX(7);tower2.setLayoutY(80);
		ImageView tower3 = new ImageView(RenderableHolder.sniperButton); tower3.setFitWidth(35); tower3.setFitHeight(35);
		tower3.setLayoutX(7);tower3.setLayoutY(160);
		ImageView tower4 = new ImageView(RenderableHolder.fireButton); tower4.setFitWidth(35); tower4.setFitHeight(35);
		tower4.setLayoutX(7);tower4.setLayoutY(120);
		
		InputUtility.setBuyButton(tower1, 1);
		InputUtility.setBuyButton(tower2, 2);
		InputUtility.setBuyButton(tower3, 3);
		InputUtility.setBuyButton(tower4, 4);
		
		this.getChildren().addAll(tower1,tower2,tower3,tower4); 

	}
}
