package plants;
import world.*;

public class Hogweed extends Plant{

	public Hogweed() {
		this.strength = 10;
		this.age = 0;
	}
	
	public Hogweed(World world, Coords xy) {
		this.world = world;
		this.xy = xy;
		this.strength = 10;
		this.age = 0;
	}
	public Hogweed(World world, Coords xy, int age) {
		this.world = world;
		this.xy = xy;
		this.strength = 10;
		this.age = age;
	}
	
	protected void checkAndSeed(int x1, int y1) {
		String comment;
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Field) {
			comment = "Seed: " + this.getName() + "(" + (this.xy.getX() + x1 + 1) + "," + (this.xy.getY()+y1+1) + ")";
			this.world.log(comment);
			this.world.addOrganism(new Hogweed(this.world, new Coords(this.xy.getX()+x1, this.xy.getY()+y1)));
		}
	}
	
	private void chaeckAndKill(int x1, int y1) {
		String comment;
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Animal) {
			comment = this.world.board[this.xy.getX()+x1][this.xy.getY()+y1].getName() + this.world.board[this.xy.getX()+x1][this.xy.getY()+y1].getXY() + " killed by " + this.getName() + this.getXY();
			this.world.log(comment);
			this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] = new Field();
			for (int i = 0; i < this.world.organisms.size(); i++) {
				if (this.world.organisms.get(i).getCoords().getX() == this.xy.getX() + x1 && this.world.organisms.get(i).getCoords().getY() == this.xy.getY() + y1) {
					this.world.organisms.remove(i);
				}
			}
		}
	}
	
	private void killArea() {
		if (this.xy.getX() == 0) {
			if (this.xy.getY() == 0) {
				chaeckAndKill(0, 1);
				chaeckAndKill(1, 1);
				chaeckAndKill(1, 0);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				chaeckAndKill(0, -1);
				chaeckAndKill(1, -1);
				chaeckAndKill(1, 0);
			}
			else {
				chaeckAndKill(0, -1);
				chaeckAndKill(1, -1);
				chaeckAndKill(1, 0);
				chaeckAndKill(0, 1);
				chaeckAndKill(1, 1);
			}
		}
		else if (this.xy.getX() == this.world.getHeight() - 1) {
			if (this.xy.getY() == 0) {
				chaeckAndKill(0, 1);
				chaeckAndKill(-1, 1);
				chaeckAndKill(-1, 0);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				chaeckAndKill(0, -1);
				chaeckAndKill(-1, -1);
				chaeckAndKill(-1, 0);
			}
			else {
				chaeckAndKill(0, 1);
				chaeckAndKill(-1, 1);
				chaeckAndKill(-1, 0);
				chaeckAndKill(0, -1);
				chaeckAndKill(-1, -1);
			}
		}
		else if (this.xy.getY() == 0) {
			chaeckAndKill(0, 1);
			chaeckAndKill(-1, 1);
			chaeckAndKill(-1, 0);
			chaeckAndKill(1, 1);
			chaeckAndKill(1, 0);
		}
		else if (this.xy.getY() == this.world.getWidth() - 1) {
			chaeckAndKill(0, -1);
			chaeckAndKill(-1, -1);
			chaeckAndKill(-1, 0);
			chaeckAndKill(1, -1);
			chaeckAndKill(1, 0);
		}
		else {
			chaeckAndKill(0, 1);
			chaeckAndKill(1, 0);
			chaeckAndKill(1, 1);
			chaeckAndKill(-1, 0);
			chaeckAndKill(-1, 1);
			chaeckAndKill(-1, -1);
			chaeckAndKill(0, -1);
			chaeckAndKill(1, -1);
		}
	}
	
	@Override
	public void action() {
		killArea();
		super.action();
	}
	
	@Override
	public void collision(Organism org) {
		String comment = org.getName() + org.getXY() + " eats " + this.getName() + this.getXY() + " and dies.";
		this.world.log(comment);
		world.board[this.xy.getX()][this.xy.getY()] = new Field();
		world.board[org.getCoords().getX()][org.getCoords().getY()] = new Field();
		for (int i = 0; i < this.world.organisms.size(); i++) {
			if (this.world.organisms.get(i).getCoords().getX() == this.xy.getX() && this.world.organisms.get(i).getCoords().getY() == this.xy.getY()) {
				this.world.organisms.remove(i);
			}
			if (this.world.organisms.get(i).getCoords().getX() == org.getCoords().getX() && this.world.organisms.get(i).getCoords().getY() == org.getCoords().getY()) {
				this.world.organisms.remove(i);
			}
		}
	}
	
	@Override
	public String getName() {
		return "Hogweed";
	}
}
