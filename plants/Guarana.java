package plants;
import world.*;

public class Guarana extends Plant{

	public Guarana() {
		this.strength = 0;
		this.age = 0;
	}
	
	public Guarana(World world, Coords xy) {
		this.world = world;
		this.xy = xy;
		this.strength = 0;
		this.age = 0;
	}
	public Guarana(World world, Coords xy, int age) {
		this.world = world;
		this.xy = xy;
		this.strength = 0;
		this.age = age;
	}
	
	protected void checkAndSeed(int x1, int y1) {
		String comment;
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Field) {
			comment = "Seed: " + this.getName() + "(" + (this.xy.getX() + x1 + 1) + "," + (this.xy.getY()+y1+1) + ")";
			this.world.log(comment);
			this.world.addOrganism(new Guarana(this.world, new Coords(this.xy.getX()+x1, this.xy.getY()+y1)));
		}
	}
	
	@Override
	public void collision(Organism org) {
		String comment = org.getName() + org.getXY() + " - strength + 3 " + this.getName() + this.getXY();
		org.addStrength(3);
		world.log(comment);
		for (int i = 0; i < this.world.organisms.size(); i++) {
			if (this.world.organisms.get(i).getCoords().getX() == this.xy.getX() && this.world.organisms.get(i).getCoords().getY() == this.xy.getY()) {
				this.world.organisms.remove(i);
			}
		}
		world.board[this.xy.getX()][this.xy.getY()] = org;
		world.board[org.getCoords().getX()][org.getCoords().getY()] = new Field();
		org.setX(this.xy.getX());
		org.setY(this.xy.getY());
	}
	
	@Override
	public String getName() {
		return "Guarana";
	}
}

