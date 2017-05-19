package de.clemens_hartmann.checkpoint.core;

import java.util.Iterator;
import java.util.List;

import de.clemens_hartmann.checkpoint.Checkpoint;
import de.clemens_hartmann.checkpoint.Config;

public class BulletArtillery extends Bullet {

	private float angle;
	
	public BulletArtillery(BulletTypes bulletType, Enemy target, float x, float y) {
		super(bulletType, target, x, y);
		double tempAngle = Math.atan2(target.getY() - y, target.getX() - x);
		angle = (float) Math.toDegrees(tempAngle) - 90;
	}

	@Override
	public void update(float delta) {
		x += direction.x * bulletType.speed * (delta/100);  
		y += direction.y * bulletType.speed * (delta/100);
		if(x > Config.WIDTH || x < 0 || y > Config.HEIGHT || y < 0)
			isAlive = false;
		Iterator<Enemy> iter = EnemyManager.getEnemys().iterator();
		while(iter.hasNext()) {
			Enemy enemy = iter.next();
			if(hitbox.overlaps(enemy.getHitbox())) {
				List<Enemy> enemysInRange = EnemyManager.findEnemys(x, y, bulletType.explosionRange);
				Iterator<Enemy> inRange = enemysInRange.iterator();
				while(inRange.hasNext()) {
					Enemy e = inRange.next();
					e.damage(bulletType.damage);
				}
				isAlive = false;
				return;
			}
		}
		hitbox.setPosition(x, y);
	}
	
	@Override
	public void draw(final Checkpoint game) {
		game.batch.draw(getTexture(), x, y, TEX_SIZE/2, TEX_SIZE/2, TEX_SIZE, TEX_SIZE, 1, 1,
				angle, 0, 0, TEX_SIZE, TEX_SIZE, false, false);
	}
}
