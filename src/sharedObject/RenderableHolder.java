package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import logic.GameLogic;
import logic.Tower;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	
	public static Image mapSprite;
	public static Image bottomMenuSprite;
	public static Image towerMenuSprite;
	public static Image arrowButton,sniperButton,iceButton,fireButton;
	public static Image sellButton;
	public static Image[] slimeSprite,wormSprite,crapSprite,caveSprite,rockSprite;
	public static Image[] arrowTowerSprite,iceTowerSprite,sniperTowerSprite,fireTowerSprite;
	public static Image upgradeButton;
	//public static AudioClip  explosionSound;

	static {
		loadResource();
	}

	public RenderableHolder() { 
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		//mapSprite = new Image("file:res/NewMap.png");
		upgradeButton = new Image(ClassLoader.getSystemResource("upgrade.png").toString());
		mapSprite = new Image(ClassLoader.getSystemResource("NewMap.png").toString());
		bottomMenuSprite = new Image(ClassLoader.getSystemResource("BottomMenu.png").toString());
		towerMenuSprite = new Image(ClassLoader.getSystemResource("TowerMenu.png").toString()); 
		sellButton = new Image(ClassLoader.getSystemResource("remove.png").toString());
		iceButton = new Image(ClassLoader.getSystemResource("IceButton.png").toString());
		arrowButton = new Image(ClassLoader.getSystemResource("ArrowButton.png").toString());
		sniperButton = new Image(ClassLoader.getSystemResource("SniperButton.png").toString());
		fireButton = new Image(ClassLoader.getSystemResource("FireButton.png").toString());
		
		slimeSprite = new Image[3];
		slimeSprite[0] = new Image(ClassLoader.getSystemResource("slime0.png").toString());
		slimeSprite[1] = new Image(ClassLoader.getSystemResource("slime1.png").toString());
		slimeSprite[2] = new Image(ClassLoader.getSystemResource("slime2.png").toString());
		
		wormSprite = new Image[17];
		wormSprite[0] = new Image(ClassLoader.getSystemResource("wormmove_01.png").toString());
		wormSprite[1] = new Image(ClassLoader.getSystemResource("wormmove_02.png").toString());
		wormSprite[2] = new Image(ClassLoader.getSystemResource("wormmove_03.png").toString());
		wormSprite[3] = new Image(ClassLoader.getSystemResource("wormmove_04.png").toString());
		wormSprite[4] = new Image(ClassLoader.getSystemResource("wormmove_05.png").toString());
		wormSprite[5] = new Image(ClassLoader.getSystemResource("wormmove_06.png").toString());
		wormSprite[6] = new Image(ClassLoader.getSystemResource("wormmove_07.png").toString());
		wormSprite[7] = new Image(ClassLoader.getSystemResource("wormmove_08.png").toString());
		wormSprite[8] = new Image(ClassLoader.getSystemResource("wormmove_09.png").toString());
		wormSprite[9] = new Image(ClassLoader.getSystemResource("wormmove_10.png").toString());
		wormSprite[10] = new Image(ClassLoader.getSystemResource("wormmove_11.png").toString());
		wormSprite[11] = new Image(ClassLoader.getSystemResource("wormmove_12.png").toString());
		wormSprite[12] = new Image(ClassLoader.getSystemResource("wormup_01.png").toString());
		wormSprite[13] = new Image(ClassLoader.getSystemResource("wormup_02.png").toString());
		wormSprite[14] = new Image(ClassLoader.getSystemResource("wormup_03.png").toString());
		wormSprite[15] = new Image(ClassLoader.getSystemResource("wormup_04.png").toString());
		wormSprite[16] = new Image(ClassLoader.getSystemResource("wormup_05.png").toString());
		
		caveSprite = new Image[12];
		caveSprite[0] = new Image(ClassLoader.getSystemResource("cavemove_01.png").toString());
		caveSprite[1] = new Image(ClassLoader.getSystemResource("cavemove_02.png").toString());
		caveSprite[2] = new Image(ClassLoader.getSystemResource("cavemove_03.png").toString());
		caveSprite[3] = new Image(ClassLoader.getSystemResource("cavemove_04.png").toString());
		caveSprite[4] = new Image(ClassLoader.getSystemResource("cavemove_05.png").toString());
		caveSprite[5] = new Image(ClassLoader.getSystemResource("cavemove_06.png").toString());
		caveSprite[6] = new Image(ClassLoader.getSystemResource("caveup_01.png").toString());
		caveSprite[7] = new Image(ClassLoader.getSystemResource("caveup_02.png").toString());
		caveSprite[8] = new Image(ClassLoader.getSystemResource("caveup_03.png").toString());
		caveSprite[9] = new Image(ClassLoader.getSystemResource("caveup_04.png").toString());
		caveSprite[10] = new Image(ClassLoader.getSystemResource("caveup_05.png").toString());
		caveSprite[11] = new Image(ClassLoader.getSystemResource("caveup_06.png").toString());
		
		rockSprite = new Image[8];
		rockSprite[0] = new Image(ClassLoader.getSystemResource("rockmove_01.png").toString());
		rockSprite[1] = new Image(ClassLoader.getSystemResource("rockmove_02.png").toString());
		rockSprite[2] = new Image(ClassLoader.getSystemResource("rockmove_03.png").toString());
		rockSprite[3] = new Image(ClassLoader.getSystemResource("rockmove_04.png").toString());
		rockSprite[4] = new Image(ClassLoader.getSystemResource("rockup_01.png").toString());
		rockSprite[5] = new Image(ClassLoader.getSystemResource("rockup_02.png").toString());
		rockSprite[6] = new Image(ClassLoader.getSystemResource("rockup_03.png").toString());
		rockSprite[7] = new Image(ClassLoader.getSystemResource("rockup_04.png").toString());
	
		
		crapSprite = new Image[14];
		crapSprite[0] = new Image(ClassLoader.getSystemResource("crapmove_01.png").toString());
		crapSprite[1] = new Image(ClassLoader.getSystemResource("crapmove_02.png").toString());
		crapSprite[2] = new Image(ClassLoader.getSystemResource("crapmove_03.png").toString());
		crapSprite[3] = new Image(ClassLoader.getSystemResource("crapmove_04.png").toString());
		crapSprite[4] = new Image(ClassLoader.getSystemResource("crapmove_05.png").toString());
		crapSprite[5] = new Image(ClassLoader.getSystemResource("crapmove_06.png").toString());
		crapSprite[6] = new Image(ClassLoader.getSystemResource("crapmove_07.png").toString());
		crapSprite[7] = new Image(ClassLoader.getSystemResource("crapmove_08.png").toString());
		crapSprite[8] = new Image(ClassLoader.getSystemResource("crapup_01.png").toString());
		crapSprite[9] = new Image(ClassLoader.getSystemResource("crapup_02.png").toString());
		crapSprite[10] = new Image(ClassLoader.getSystemResource("crapup_03.png").toString());
		crapSprite[11] = new Image(ClassLoader.getSystemResource("crapup_04.png").toString());
		crapSprite[12] = new Image(ClassLoader.getSystemResource("crapup_05.png").toString());
		crapSprite[13] = new Image(ClassLoader.getSystemResource("crapup_06.png").toString());
		
		iceTowerSprite = new Image[3];
		iceTowerSprite[0] = new Image(ClassLoader.getSystemResource("IceTower_1.png").toString());
		iceTowerSprite[1] = new Image(ClassLoader.getSystemResource("IceTower_2.png").toString());
		iceTowerSprite[2] = new Image(ClassLoader.getSystemResource("IceTower_3.png").toString());
		
		arrowTowerSprite = new Image[3];
		arrowTowerSprite[0] = new Image(ClassLoader.getSystemResource("ArrowTower_1.png").toString());
		arrowTowerSprite[1] = new Image(ClassLoader.getSystemResource("ArrowTower_2.png").toString());
		arrowTowerSprite[2] = new Image(ClassLoader.getSystemResource("ArrowTower_3.png").toString());
		
		sniperTowerSprite = new Image[3];
		sniperTowerSprite[0] = new Image(ClassLoader.getSystemResource("SniperTower_1.png").toString());
		sniperTowerSprite[1] = new Image(ClassLoader.getSystemResource("SniperTower_2.png").toString());
		sniperTowerSprite[2] = new Image(ClassLoader.getSystemResource("SniperTower_3.png").toString());
	
		fireTowerSprite = new Image[3];
		fireTowerSprite[0] = new Image(ClassLoader.getSystemResource("FireTower_1.png").toString());
		fireTowerSprite[1] = new Image(ClassLoader.getSystemResource("FireTower_2.png").toString());
		fireTowerSprite[2] = new Image(ClassLoader.getSystemResource("FireTower_3.png").toString());
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed()) {
				entities.remove(i);
			}
			
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
	
	public List<Tower> getTowers(){
		List<Tower> towerList = new ArrayList<Tower>();
		for(IRenderable entity: RenderableHolder.getInstance().getEntities()) {
			if(entity instanceof Tower && entity.isDestroyed() == false && entity.isVisible()) {
				towerList.add((Tower)entity);
			}
		}
		return towerList;
	}
}
