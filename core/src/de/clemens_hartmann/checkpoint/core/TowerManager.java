package de.clemens_hartmann.checkpoint.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.clemens_hartmann.checkpoint.Checkpoint;

public class TowerManager {
	private static List<Tower> towers = new LinkedList<Tower>();
	
	//Update all Towers
	public void update(float delta) {
		//TODO
		Iterator<Tower> iter = towers.iterator();
		while(iter.hasNext()) {
			Tower tower = iter.next();
			tower.update(delta);
		}
	}
	
	public static void addTower(TowerTypes towerType, int x, int y) {
		if(towerType == TowerTypes.TowerQuickfire)
			towers.add(new TowerQuickfire(towerType, x, y));
		if(towerType == TowerTypes.TowerCannon)
			towers.add(new TowerCannon(towerType, x, y));
		if(towerType == TowerTypes.TowerIce)
			towers.add(new TowerIce(towerType, x, y));
	}
	
	public static boolean findTower(int x, int y) {
		System.out.println("in method");
		Iterator<Tower> iter = towers.iterator();
		while(iter.hasNext()) {
			Tower tower = iter.next();
			System.out.println(tower.getX() + " : " + x);
			if(tower.getX() == x && tower.getY() == y)
				return true;
		}
		return false;
	}
	
	public void draw(final Checkpoint game) {
		game.batch.begin();
		for(Tower tower : towers) {
			tower.draw(game);
		}
		game.batch.end();
	}
	
	public void dispose() {
		for(Tower tower : towers) {
			tower.dispose();
		}
		towers.clear();
	}
}
