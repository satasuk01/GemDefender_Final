package input;

import java.util.List;

import core.GameCore;
import drawing.PaneForRenderImageViews;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import logic.GameLogic;
import logic.Tower;

public class InputUtility {

	public static double mouseX, mouseY;
	public static boolean mouseOnScreen = true;
	public static int hover;

	public static void addImageViewEvents(Tower tower) {

		EventHandler<MouseEvent> hover = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					GameCore.setSelectedTower(tower);
				}

			}
		};
		tower.getImageView().setOnMousePressed(hover);
	}
	public static void setSellButton(ImageView btn) {

		EventHandler<MouseEvent> hover = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					GameCore.sellTower();
					;
				}

			}
		};
		btn.setOnMousePressed(hover);
	}

	public static void setUpgradeButton(ImageView btn) {

		EventHandler<MouseEvent> hover = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					GameCore.upgradeTower();
				}

			}
		};
		btn.setOnMousePressed(hover);
	}

	public static void setBuyButton(ImageView btn, int status) {
		btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hover = status;
			}
		});
		btn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hover = 0;
			}
		});
		EventHandler<MouseEvent> hover = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					deSelected();
					GameCore.setBuyStatus(status);
				}

			}
		};
		btn.setOnMousePressed(hover);

	}

	public static void mouseEventOnField(PaneForRenderImageViews pane) { // for Buy Tower
		// get mouse x and mouse y when mouse enter and move across the field
		pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mouseX = event.getX();
				mouseY = event.getY();
			}
		});

		EventHandler<MouseEvent> hover = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY) {
					// if right mouse button is clicked cancel buy then set buy status at 0 and
					// selected tower
					deSelected();
					GameCore.setBuyStatus(0);
				}
				if (event.getButton() == MouseButton.PRIMARY) {
					// Calculate row and column
					int row = (int) mouseY / 25;
					int column = (int) mouseX / 25;
					if (GameCore.getBuyStatus() != 0 && GameCore.checkCanplace(row, column)) {
						GameCore.placeTower(row, column);
					}
				}
			}
		};
		pane.setOnMousePressed(hover);
	}
	public static void deSelected() {
		List<Tower> towerList = GameLogic.getTower();
		if (towerList.size() != 0) {
			for (Tower t : towerList) {
				t.setSelected(false);
				t.getImageView().setEffect(null);
			}
		}
	}

}
