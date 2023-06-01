package world;
import java.util.Random;

import plants.Field;

public class Plant extends Organism{

	public Plant() {
		this.strength = 0;
		this.initiative = 0;
		this.xy = new Coords(0,0);
	}
	
	public Plant(World world, Coords xy) {
		this.strength = 0;
		this.initiative = 0;
		this.world = world;
		this.xy = xy;
	}
	
	protected void checkAndSeed(int x1, int y1) {
		String comment;
		if (this.world.board[this.xy.getX() + x1][this.xy.getY() + y1] instanceof Field) {
			comment = "Plant seed(" + (this.xy.getX() + x1 + 1) + "," + (this.xy.getY() + y1 + 1) + ")";
			this.world.log(comment);
			this.world.addOrganism(new Plant(this.world, new Coords(this.xy.getX() + x1, this.xy.getY() + y1)));
		}
	}
	
	@Override
	public void action() {
		Random rand = new Random();
		int rand1 = rand.nextInt(15);
		int rand2;
		if (rand1 != 0) return;
		if (this.xy.getX() == 0) {
			rand2 = rand.nextInt(11);
			if (this.xy.getY() == 0) {
				if (rand2 == 0) checkAndSeed(0, 1);
				else if (rand2 == 1) checkAndSeed(1, 1);
				else if (rand2 == 2)checkAndSeed(1, 0);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				if (rand2 == 3) checkAndSeed(0, -1);
				else if (rand2 == 4) checkAndSeed(1, -1);
				else if (rand2 == 5) checkAndSeed(1, 0);
			}
			else {
				if (rand2 == 6) checkAndSeed(0, -1);
				else if (rand2 == 7) checkAndSeed(1, -1);
				else if (rand2 == 8) checkAndSeed(1, 0);
				else if (rand2 == 9) checkAndSeed(0, 1);
				else if (rand2 == 10) checkAndSeed(1, 1);
			}
		}
		else if (this.xy.getX() == this.world.getHeight() - 1) {
			rand2 = rand.nextInt(11);
			if (this.xy.getY() == 0) {
				if (rand2 == 0) checkAndSeed(0, 1);
				else if (rand2 == 1) checkAndSeed(-1, 1);
				else if (rand2 == 2) checkAndSeed(-1, 0);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				if (rand2 == 3) checkAndSeed(0, -1);
				else if (rand2 == 4) checkAndSeed(-1, -1);
				else if (rand2 == 5) checkAndSeed(-1, 0);
			}
			else {
				if (rand2 == 6) checkAndSeed(0, 1);
				else if (rand2 == 7) checkAndSeed(-1, 1);
				else if (rand2 == 8) checkAndSeed(-1, 0);
				else if (rand2 == 9) checkAndSeed(0, -1);
				else if (rand2 == 10) checkAndSeed(-1, -1);
			}
		}
		else if (this.xy.getY() == 0) {
			rand2 = rand.nextInt(5);
			if (rand2 == 0) checkAndSeed(0, 1);
			else if (rand2 == 1) checkAndSeed(-1, 1);
			else if (rand2 == 2) checkAndSeed(-1, 0);
			else if (rand2 == 3) checkAndSeed(1, 1);
			else if (rand2 == 4) checkAndSeed(1, 0);
		}
		else if (this.xy.getY() == this.world.getWidth() - 1) {
			rand2 = rand.nextInt(5);
			if (rand2 == 0) checkAndSeed(0, -1);
			else if (rand2 == 1) checkAndSeed(-1, -1);
			else if (rand2 == 2) checkAndSeed(-1, 0);
			else if (rand2 == 3) checkAndSeed(1, -1);
			else if (rand2 == 4) checkAndSeed(1, 0);
		}
		else {
			rand2 = rand.nextInt(8);
			if (rand2 == 0) checkAndSeed(0, 1);
			else if (rand2 == 1) checkAndSeed(1, 0);
			else if (rand2 == 2) checkAndSeed(1, 1);
			else if (rand2 == 3) checkAndSeed(-1, 0);
			else if (rand2 == 4) checkAndSeed(-1, 1);
			else if (rand2 == 5) checkAndSeed(-1, -1);
			else if (rand2 == 6) checkAndSeed(0, -1);
			else if (rand2 == 7) checkAndSeed(1, -1);
		}
		
	}
	
	@Override
	public void collision(Organism org) {
		String comment = org.getName() + org.getXY() + " eats " + this.getName() + this.getXY();
		world.log(comment);
		for (int i=0;i<this.world.organisms.size();i++) {
			if(this.world.organisms.get(i).getCoords().getX() == this.xy.getX() && this.world.organisms.get(i).getCoords().getY() == this.xy.getY()) {
				this.world.organisms.remove(i);
			}
		}
		world.board[this.xy.getX()][this.xy.getY()] = org;
		world.board[org.getCoords().getX()][org.getCoords().getY()] = new Field();
		org.setX(this.xy.getX());
		org.setY(this.xy.getY());
	}
	
	@Override
	public void draw() {
		
	}

	@Override
	public String getName() {
		return "Plant";
	}

}
