package animals;
import world.*;

import java.util.Random;

import plants.Field;

public class Antelope extends Animal{

	public Antelope() {
		this.strength = 4;
		this.initiative = 4;
		this.age = 0;
	}
	
	public Antelope(World world, Coords xy) {
		this.world = world;
		this.xy = xy;
		this.strength = 4;
		this.initiative = 4;
		this.age = 0;
	}
	public Antelope(World world, Coords xy, int strength, int age) {
		this.world = world;
		this.xy = xy;
		this.strength = strength;
		this.initiative = 4;
		this.age = age;
	}
	
	@Override
	public void action() {
		Random rand = new Random();
		int lot;
		if (this.xy.getX() == 0) {
			if (this.xy.getY() == 0) {
				lot = rand.nextInt(5);
				if (lot == 0) movement(0, 1);
				else if (lot == 1) movement(1, 1);
				else if (lot == 2) movement(1, 0);
				else if (lot == 3) movement(0, 2);
				else if (lot == 4) movement(2, 0);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				lot = rand.nextInt(5);
				if (lot == 0) movement(0, -1);
				else if (lot == 1) movement(1, -1);
				else if (lot == 2) movement(1, 0);
				else if (lot == 3) movement(0, -2);
				else if (lot == 4) movement(2, 0);
			}
			else {
				lot = rand.nextInt(8);
				if (lot == 0) movement(0, -1);
				else if (lot == 1) movement(1, -1);
				else if (lot == 2) movement(1, 0);
				else if (lot == 3) movement(0, 1);
				else if (lot == 4) movement(1, 1);
				else if (lot == 5 && this.xy.getY() != this.world.getWidth() - 2) movement(0, 2);
				else if (lot == 6 && this.xy.getX() != this.world.getHeight() - 2) movement(2, 0);
				else if (lot == 7 && this.xy.getY() != 1) movement(0, -2);
			}
		}
		else if (this.xy.getX() == this.world.getHeight() - 1) {
			if (this.xy.getY() == 0) {
				lot = rand.nextInt(5);
				if (lot == 0) movement(0, 1);
				else if (lot == 1) movement(-1, 1);
				else if (lot == 2) movement(-1, 0);
				else if (lot == 3) movement(-2, 0);
				else if (lot == 4) movement(0, 2);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				lot = rand.nextInt(5);
				if (lot == 0) movement(0, -1);
				else if (lot == 1) movement(-1, -1);
				else if (lot == 2) movement(-1, 0);
				else if (lot == 3) movement(-2, 0);
				else if (lot == 4) movement(0, -2);
			}
			else {
				lot = rand.nextInt(8);
				if (lot == 0) movement(0, 1);
				else if (lot == 1) movement(-1, 1);
				else if (lot == 2) movement(-1, 0);
				else if (lot == 3) movement(0, -1);
				else if (lot == 4) movement(-1, -1);
				else if (lot == 5 && this.xy.getY() != this.world.getWidth() - 2) movement(0, 2);
				else if (lot == 6 && this.xy.getX() != 1) movement(-2, 0);
				else if (lot == 7 && this.xy.getY() != 1) movement(0, -2);
			}
		}
		else if (this.xy.getY() == 0) {
			lot = rand.nextInt(8);
			if (lot == 0) movement(0, 1);
			else if (lot == 1) movement(-1, 1);
			else if (lot == 2) movement(-1, 0);
			else if (lot == 3) movement(1, 1);
			else if (lot == 4) movement(1, 0);
			else if (lot == 5) movement(0, 2);
			else if (lot == 6 && this.xy.getX() != 1) movement(-2, 0);
			else if (lot == 7 && this.xy.getX() != this.world.getHeight() - 2) movement(2, 0);
		}
		else if (this.xy.getY() == this.world.getWidth() - 1) {
			lot = rand.nextInt(8);
			if (lot == 0) movement(0, -1);
			else if (lot == 1) movement(-1, -1);
			else if (lot == 2) movement(-1, 0);
			else if (lot == 3) movement(1, -1);
			else if (lot == 4) movement(1, 0);
			else if (lot == 5) movement(0, -2);
			else if (lot == 6 && this.xy.getX() != 1) movement(-2, 0);
			else if (lot == 7 && this.xy.getX() != this.world.getHeight() - 2) movement(2, 0);
		}
		else {
			lot = rand.nextInt(12);
			if (lot == 0) movement(0, 1);
			else if (lot == 1) movement(1, 0);
			else if (lot == 2) movement(1, 1);
			else if (lot == 3) movement(-1, 0);
			else if (lot == 4) movement(-1, 1);
			else if (lot == 5) movement(-1, -1);
			else if (lot == 6) movement(0, -1);
			else if (lot == 7) movement(1, -1);
			else if (lot == 8 && this.xy.getX() != 1) movement(-2, 0);
			else if (lot == 9 && this.xy.getX() != this.world.getHeight() - 2) movement(2, 0);
			else if (lot == 10 && this.xy.getY() != this.world.getWidth() - 2) movement(0, 2);
			else if (lot == 11 && this.xy.getY() != 1) movement(0, -2);
		}
	}
	
	@Override
	public void collision(Organism org) {
		Random rand = new Random();
		int lot = rand.nextInt(2);
		if (org instanceof Antelope) {
			if (this.getAge() > 2) reproduction();
		}else if(lot == 0) {
			String comment = this.getName() + this.getXY() + " flees.";
			this.world.log(comment);
			if (this.xy.getY() != world.getWidth() - 1 && this.world.board[this.xy.getX()][this.xy.getY() + 1] instanceof Field) {
				world.board[this.xy.getX()][this.xy.getY() + 1] = world.board[this.xy.getX()][this.xy.getY()];
				world.board[this.xy.getX()][this.xy.getY()] = new Field();
				this.xy.setY(this.xy.getY()+1);
			}
			else if (this.xy.getX() != world.getHeight() - 1 && this.world.board[this.xy.getX() + 1][this.xy.getY()] instanceof Field) {
				world.board[this.xy.getX() + 1][this.xy.getY()] = world.board[this.xy.getX()][this.xy.getY()];
				world.board[this.xy.getX()][this.xy.getY()] = new Field();
				this.xy.setX(this.xy.getX()+1);
			}
			else if (this.xy.getY() != 0 && this.world.board[this.xy.getX()][this.xy.getY() - 1] instanceof Field) {
				world.board[this.xy.getX()][this.xy.getY() - 1] = world.board[this.xy.getX()][this.xy.getY()];
				world.board[this.xy.getX()][this.xy.getY()] = new Field();
				this.xy.setY(this.xy.getY()-1);
			}
			else if (this.xy.getX() != 0 && this.world.board[this.xy.getX() - 1][this.xy.getY()] instanceof Field) {
				world.board[this.xy.getX() - 1][this.xy.getY()] = world.board[this.xy.getX()][this.xy.getY()];
				world.board[this.xy.getX()][this.xy.getY()] = new Field();
				this.xy.setX(this.xy.getX()-1);
			}
		}else if(org.getStrength() >= this.strength) {
			String comment = org.getName() + org.getXY() + " defeats " + this.getName() + this.getXY();
			this.world.log(comment);
			for (int i=0;i<this.world.organisms.size();i++) {
				if (this.world.organisms.get(i).getCoords().getX() == this.xy.getX() && this.world.organisms.get(i).getCoords().getY() == this.xy.getY()) {
					this.world.organisms.remove(i);
				}
			}
			world.board[this.xy.getX()][this.xy.getY()] = org;
			world.board[org.getCoords().getX()][org.getCoords().getY()] = new Field();
			org.setX(this.xy.getX());
			org.setY(this.xy.getY());
		}else {
			String comment = org.getName() + org.getXY() + " beaten by: " + this.getName() + this.getXY();
			this.world.log(comment);
			world.board[org.getCoords().getX()][org.getCoords().getY()] = new Field();
			for (int i=0;i<this.world.organisms.size();i++) {
				if (this.world.organisms.get(i).getCoords().getX() == org.getCoords().getX() && this.world.organisms.get(i).getCoords().getY() == org.getCoords().getY()) {
					this.world.organisms.remove(i);
				}
			}
		}
	}
	
	@Override
	protected void birth(int x1, int y1) {
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Field) {
			String comment = "New birth: " + this.getName() + "(" + (this.xy.getX()+x1+1) + "," + (this.xy.getY()+y1+1) + ")";
			this.world.log(comment);
			this.world.addOrganism(new Antelope(this.world, new Coords(this.xy.getX()+x1,this.xy.getY()+y1)));
		}
	}
	
	@Override
	public String getName() {
		return "Antelope";
	}
}
