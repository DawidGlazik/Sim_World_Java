package animals;
import world.*;

import java.util.Random;

import plants.Field;

public class Turtle extends Animal{

	public Turtle() {
		this.strength = 2;
		this.initiative = 1;
		this.age = 0;
	}
	
	public Turtle(World world, Coords xy) {
		this.world = world;
		this.xy = xy;
		this.strength = 2;
		this.initiative = 1;
		this.age = 0;
	}
	public Turtle(World world, Coords xy, int sila, int age) {
		this.world = world;
		this.xy = xy;
		this.strength = sila;
		this.initiative = 1;
		this.age = age;
	}
	
	@Override
	protected void birth(int x1, int y1) {
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Field) {
			String comment = "New birth: " + this.getName() + "(" + (this.xy.getX()+x1+1) + "," + (this.xy.getY()+y1+1) + ")";
			this.world.log(comment);
			this.world.addOrganism(new Turtle(this.world, new Coords(this.xy.getX()+x1,this.xy.getY()+y1)));
		}
	}
	
	@Override
	public void action() {
		Random rand = new Random();
		int doMove = rand.nextInt(4);
		if (doMove != 0) return;
		super.action();
	}
	
	@Override
	public void collision(Organism org) {
		String comment;
		if (org instanceof Turtle) {
			if (this.age > 2) reproduction();
			else if(org.getStrength() < 5) {
				comment = this.getName() + this.getXY() + " stops " + org.getName() + org.getXY();
				this.world.log(comment);
			}else if(org.getStrength() > this.strength) {
				comment = org.getName() + org.getXY() + " defeats " + this.getName() + this.getXY();
				this.world.log(comment);
				for (int i = 0; i < this.world.organisms.size(); i++) {
					if (this.world.organisms.get(i).getCoords().getX() == this.xy.getX() && this.world.organisms.get(i).getCoords().getY() == this.xy.getY()) {
						this.world.organisms.remove(i);
					}
				}
				world.board[this.xy.getX()][this.xy.getY()] = org;
				world.board[org.getCoords().getX()][org.getCoords().getY()] = new Field();
				org.setX(this.xy.getX());
				org.setY(this.xy.getY());
			}else {
				comment = org.getName() + org.getXY() + " beaten by " + this.getName() + this.getXY();
				this.world.log(comment);
				this.world.board[org.getCoords().getX()][org.getCoords().getY()] = new Field();
				for (int i = 0; i < this.world.organisms.size(); i++) {
					if (this.world.organisms.get(i).getCoords().getX() == org.getCoords().getX() && this.world.organisms.get(i).getCoords().getY() == org.getCoords().getY()) {
						this.world.organisms.remove(i);
					}
				}
			}
		}
	}
	
	@Override
	public String getName() {
		return "Turtle";
	}
}
