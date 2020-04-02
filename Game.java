package ufogame;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import view.GameFrameWork;
import view.IKeyboardListener;
import view.ITickableListener;



/**
 * @author marik
 *
 */
public class Game implements ITickableListener, IKeyboardListener {



	// Idea: we want to have multiple instances of an ufo and of a projectile

	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private ArrayList<Ufo> ufos = new ArrayList<>();

//	private Ufo [] ufos = new Ufo[10];

//	private Projectile [] projectiles = new Projectile[30];

	private Ship ship;
	private int screenWidth = 900;
	private int screenHeight = 700;
	private GameFrameWork frameWork = new GameFrameWork();
	private int score;
	



	/**
	 * Initiates everything for the game. Multiple ufos and a ship are created.
	 */

	public void init() {
		frameWork.setSize(screenWidth, screenHeight);
		frameWork.setBackground(new Background("01Vorlesung\\assets\\Up_Flower_Meadow_Mineral_King - Kopie.jpg"));

		

		ship = new Ship(screenWidth / 9, 4 * screenHeight / 7, screenWidth / 1, screenWidth / 2,
				"01Vorlesung\\assets\\Biene.png");
		frameWork.addGameObject(ship);


		Ufo ufo = new Ufo(-30, screenHeight / 55, screenWidth / 1, screenWidth / 2, 2,
				"01Vorlesung\\assets\\Blume.png");
		ufos.add(ufo);
		frameWork.addGameObject(ufo);



		for (int i = 1; i < 10; i++) {
			ufos.add(new Ufo(ufos.get(i - 1).getX() - 200, ufos.get(0).getY(), ufos.get(0).getWidth(),
					ufos.get(0).getHeight(), ufos.get(0).getSpeed(), ufos.get(0).getImagePath()));
			frameWork.addGameObject(ufos.get(i));
		}

		frameWork.addTick(this);
		frameWork.addIKeyInput(this);

	}



	public void shoot() {
		// projectile erstellen
		Projectile projectile = new Projectile(ship.getX() + ship.getWidth()/4, 
				ship.getY() - ship.getWidth() / 3, ship.getWidth() / 3, ship.getWidth() / 1, 3,
				"01Vorlesung\\assets\\stachel.png");

		projectiles.add(projectile);
		frameWork.addGameObject(projectile);
		
	}


		// Variante Array
		// projectiles[0] = projectile;
		// projectiles.get(0).getWidth();
		// Variante Array
		// projectiles[0].getWidth();
		// projectiles.size();
		// Variante Array
		// projectiles.lenght

	



	@Override

	public void tick(long elapsedTime) {
		for (Ufo ufo : ufos) {
			ufo.move();

		}

		if (ufos.get(0).getX() > screenWidth) {
			frameWork.removeGameObject(ufos.get(0));
			ufos.remove(0);
			ufos.add(new Ufo(ufos.get(ufos.size() - 1).getX() - 200, ufos.get(0).getY(), ufos.get(0).getWidth(),
					ufos.get(0).getHeight(), ufos.get(0).getSpeed(), ufos.get(0).getImagePath()));
			frameWork.addGameObject(ufos.get(ufos.size() - 1));

		}

		

		for(Projectile p: projectiles) {
            p.move();
            
		}
     }

		
	
//1 Aufgabe
if (projectiles.get(0).getY() > screenHeight) {
	frameWork.removeGameObject(projectiles.get(0));
	projectiles.remove(0);
}


//3 Aufgabe
for (Projectile p: projectiles) {
	for (Ufo ufo : ufos) {
		if (checkCollided(ufo, p) == true) {
			projectiles.remove(p);
			ufos.remove(ufo);
			score += 1;
			System.out.println(score);
			
		}
	}
}



//2 Aufgabe
public boolean checkCollided(Ufo ufo, Projectile projectile) {

if (ufo.getX() < (projectile.getX() + projectile.getWidth())
		&& (ufo.getY() < (projectile.getY() + projectile.getHeight()))
		&& ((ufo.getX() + ufo.getWidth()) > (projectile.getX()))
		&& ((ufo.getY() + ufo.getHeight()) > (projectile.getY()))) {

			return true;
		}
	}
}

return false;

}


		//TODO check size of list

//			frameWork.removeGameObject(projectiles.get(0));

	



	@Override

	public int[] getKeys() {

		int [] keys = {KeyEvent.VK_SPACE};

		return keys;

	}



	@Override

	public void keyDown(int key) {

		shoot();

		

	}



}