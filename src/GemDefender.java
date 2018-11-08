import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

//this class is like main class that run animationtimer and will change scene through all game state
//use method run to handle that game state


public class GemDefender extends Application {
	public static final int WIDTH = 800 , HEIGH = 600;
	public static GameState gs;
	public static Stage window;
	public static int state = 0;
    public void start(Stage primaryStage) {
        window = primaryStage;
        gs = new Menu();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	gs.run();
            	window.setScene(gs.getScene());
            }
        }.start();
        window.setHeight(HEIGH);
        window.setWidth(WIDTH);
        window.setResizable(false);
        window.setTitle("Gem Defender");      
        window.show();
    }
    
    public static GameState getGs() {
		return gs;
	}


	public  void setGs(GameState gs) {
		this.gs = gs;
	}


	public static Stage getWindow() {
		return window;
	}


	public void setWindow(Stage window) {
		this.window = window;
	}


	public static int getWidth() {
		return WIDTH;
	}


	public static int getHeigh() {
		return HEIGH;
	}


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() {
		System.exit(0);
	}
}
