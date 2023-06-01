package world;
import java.util.Random;

import plants.Field;

public class Animal extends Organism{

	public Animal() {
		this.strength = 0;
		this.initiative = 0;
		this.xy = new Coords(0,0);
	}
	
	public Animal(World swiat, Coords xy) {
		this.strength = 0;
		this.initiative = 0;
		this.world = swiat;
		this.xy = xy;
	}
	
	protected void movement(int x1, int y1) {
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Field) {
			this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] = this.world.board[this.xy.getX()][this.xy.getY()];
			this.world.board[this.xy.getX()][this.xy.getY()] = new Field();
			this.xy.setX(this.xy.getX() + x1);
			this.xy.setY(this.xy.getY() + y1);
		}
		else {
			this.world.board[this.xy.getX()+x1][this.xy.getY()+y1].collision(this);
		}
	}
	
	protected void birth(int x1, int y1) {
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Field) {
			String comment = "New birth: " + this.getName() + "(" + (this.xy.getX()+x1+1) + "," + (this.xy.getY()+y1+1) + ")";
			this.world.log(comment);
			this.world.addOrganism(new Animal(this.world, new Coords(this.xy.getX()+x1,this.xy.getY()+y1)));
		}
	}
	
	protected void reproduction() {
		Random rand = new Random();
		int lot;
		if (this.xy.getX() == 0) {
			if (this.xy.getY() == 0) {
				lot = rand.nextInt(3);
				if (lot == 0) birth(0, 1);
				else if (lot == 1) birth(1, 1);
				else if (lot == 2) birth(1, 0);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				lot = rand.nextInt(3);
				if (lot == 0) birth(0, -1);
				else if (lot == 1) birth(1, -1);
				else if (lot == 2) birth(1, 0);
			}
			else {
				lot = rand.nextInt(5);
				if (lot == 0) birth(0, -1);
				else if (lot == 1) birth(1, -1);
				else if (lot == 2) birth(1, 0);
				else if (lot == 3) birth(0, 1);
				else if (lot == 4) birth(1, 1);
			}
		}
		else if (this.xy.getX() == this.world.getHeight() - 1) {
			if (this.xy.getY() == 0) {
				lot = rand.nextInt(3);
				if (lot == 0) birth(0, 1);
				else if (lot == 1) birth(-1, 1);
				else if (lot == 2) birth(-1, 0);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				lot = rand.nextInt(3);
				if (lot == 0) birth(0, -1);
				else if (lot == 1) birth(-1, -1);
				else if (lot == 2) birth(-1, 0);
			}
			else {
				lot = rand.nextInt(5);
				if (lot == 0) birth(0, 1);
				else if (lot == 1) birth(-1, 1);
				else if (lot == 2) birth(-1, 0);
				else if (lot == 3) birth(0, -1);
				else if (lot == 4) birth(-1, -1);
			}
		}
		else if (this.xy.getY() == 0) {
			lot = rand.nextInt(5);
			if (lot == 0) birth(0, 1);
			else if (lot == 1) birth(-1, 1);
			else if (lot == 2) birth(-1, 0);
			else if (lot == 3) birth(1, 1);
			else if (lot == 4) birth(1, 0);
		}
		else if (this.xy.getY() == this.world.getWidth() - 1) {
			lot = rand.nextInt(5);
			if (lot == 0) birth(0, -1);
			else if (lot == 1) birth(-1, -1);
			else if (lot == 2) birth(-1, 0);
			else if (lot == 3) birth(1, -1);
			else if (lot == 4) birth(1, 0);
		}
		else {
			lot = rand.nextInt(8);
			if (lot == 0) birth(0, 1);
			else if (lot == 1) birth(1, 0);
			else if (lot == 2) birth(1, 1);
			else if (lot == 3) birth(-1, 0);
			else if (lot == 4) birth(-1, 1);
			else if (lot == 5) birth(-1, -1);
			else if (lot == 6) birth(0, -1);
			else if (lot == 7) birth(1, -1);
		}
	}
	
	@Override
	public void action() {
		int lot;
		Random rand = new Random();
		if (this.xy.getX() == 0) {
			if (this.xy.getY() == 0) {
				lot = rand.nextInt(3);
				if (lot == 0) movement(0, 1);
				else if (lot == 1) movement(1, 1);
				else if (lot == 2) movement(1, 0);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				lot = rand.nextInt(3);
				if (lot == 0) movement(0, -1);
				else if (lot == 1) movement(1, -1);
				else if (lot == 2) movement(1, 0);
			}
			else {
				lot = rand.nextInt(5);
				if (lot == 0) movement(0, -1);
				else if (lot == 1) movement(1, -1);
				else if (lot == 2) movement(1, 0);
				else if (lot == 3) movement(0, 1);
				else if (lot == 4) movement(1, 1);
			}
		}
		else if (this.xy.getX() == this.world.getHeight() - 1) {
			if (this.xy.getY() == 0) {
				lot = rand.nextInt(3);
				if (lot == 0) movement(0, 1);
				else if (lot == 1) movement(-1, 1);
				else if (lot == 2) movement(-1, 0);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				lot = rand.nextInt(3);
				if (lot == 0) movement(0, -1);
				else if (lot == 1) movement(-1, -1);
				else if (lot == 2) movement(-1, 0);
			}
			else {
				lot = rand.nextInt(5);
				if (lot == 0) movement(0, 1);
				else if (lot == 1) movement(-1, 1);
				else if (lot == 2) movement(-1, 0);
				else if (lot == 3) movement(0, -1);
				else if (lot == 4) movement(-1, -1);
			}
		}
		else if (this.xy.getY() == 0) {
			lot = rand.nextInt(5);
			if (lot == 0) movement(0, 1);
			else if (lot == 1) movement(-1, 1);
			else if (lot == 2) movement(-1, 0);
			else if (lot == 3) movement(1, 1);
			else if (lot == 4) movement(1, 0);
		}
		else if (this.xy.getY() == this.world.getWidth() - 1) {
			lot = rand.nextInt(5);
			if (lot == 0) movement(0, -1);
			else if (lot == 1) movement(-1, -1);
			else if (lot == 2) movement(-1, 0);
			else if (lot == 3) movement(1, -1);
			else if (lot == 4) movement(1, 0);
		}
		else {
			lot = rand.nextInt(8);
			if (lot == 0) movement(0, 1);
			else if (lot == 1) movement(1, 0);
			else if (lot == 2) movement(1, 1);
			else if (lot == 3) movement(-1, 0);
			else if (lot == 4) movement(-1, 1);
			else if (lot == 5) movement(-1, -1);
			else if (lot == 6) movement(0, -1);
			else if (lot == 7) movement(1, -1);
		}
	}

	@Override
	public void collision(Organism org) {
		if (org.getName() == this.getName()) {
			if (this.getAge() > 2) reproduction();
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
				if (this.world.organisms.get(i).getCoords().getX() == org.xy.getX() && this.world.organisms.get(i).getCoords().getY() == org.xy.getY()) {
					this.world.organisms.remove(i);
				}
			}
		}
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Animal";
	}

}
